package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.OptUUIDMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;
import java.util.UUID;

public class TameableAnimal extends Animal {

  private boolean sitting = false;
  private boolean tamed = false;
  private UUID owner = null;

  private byte getFlags(MinecraftVersion version) {
    return (byte) ((sitting ? 0x01 : 0x00) |
        (tamed ? 0x04 : 0x00));
  }

  private void setFlags(MinecraftVersion version, byte flags) {
    sitting = (flags & 0x01) != 0;
    tamed = (flags & 0x04) != 0;
  }

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write((byte) 0, new ByteMetadataItem(getFlags(version)));
    metadata.write(null, new OptUUIDMetadataItem(owner));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    setFlags(version, metadata.read((byte) 0));
    owner = metadata.read(null);
  }

  public boolean isSitting() {
    return sitting;
  }

  public void setSitting(boolean sitting) {
    this.sitting = sitting;
  }

  public boolean isTamed() {
    return tamed;
  }

  public void setTamed(boolean tamed) {
    this.tamed = tamed;
  }

  public UUID getOwner() {
    return owner;
  }

  public void setOwner(UUID owner) {
    this.owner = owner;
  }
}
