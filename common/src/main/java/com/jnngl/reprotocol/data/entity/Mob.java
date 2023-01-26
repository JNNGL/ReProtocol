package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Mob extends LivingEntity {

  private boolean noAI = false;
  private boolean leftHanded = false;
  private boolean aggressive = false;

  private byte getFlags(MinecraftVersion version) {
    return (byte) ((noAI ? 0x01 : 0x00) |
        (leftHanded ? 0x02 : 0x00) |
        (aggressive ? 0x04 : 0x00));
  }

  private void setFlags(MinecraftVersion version, byte flags) {
    noAI = (flags & 0x01) != 0;
    leftHanded = (flags & 0x02) != 0;
    aggressive = (flags & 0x04) != 0;
  }

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write((byte) 0, new ByteMetadataItem(getFlags(version)));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    setFlags(version, metadata.read((byte) 0));
  }

  public boolean isNoAI() {
    return noAI;
  }

  public void setNoAI(boolean noAI) {
    this.noAI = noAI;
  }

  public boolean isLeftHanded() {
    return leftHanded;
  }

  public void setLeftHanded(boolean leftHanded) {
    this.leftHanded = leftHanded;
  }

  public boolean isAggressive() {
    return aggressive;
  }

  public void setAggressive(boolean aggressive) {
    this.aggressive = aggressive;
  }
}
