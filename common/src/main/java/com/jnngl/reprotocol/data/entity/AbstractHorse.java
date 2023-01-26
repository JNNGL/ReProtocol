package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.OptUUIDMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;
import java.util.UUID;

public class AbstractHorse extends Animal {

  private boolean tame = false;
  private boolean saddled = false;
  private boolean bred = false;
  private boolean eating = false;
  private boolean rearing = false;
  private boolean mouthOpen = false;
  private UUID owner = null;

  private byte getFlags(MinecraftVersion version) {
    return (byte) ((tame ? 0x02 : 0x00) |
        (saddled ? 0x04 : 0x00) |
        (bred ? 0x08 : 0x00) |
        (eating ? 0x10 : 0x00) |
        (rearing ? 0x20 : 0x00) |
        (mouthOpen ? 0x40 : 0x00));
  }

  private void setFlags(MinecraftVersion version, byte flags) {
    tame = (flags & 0x02) != 0;
    saddled = (flags & 0x04) != 0;
    bred = (flags & 0x08) != 0;
    eating = (flags & 0x10) != 0;
    rearing = (flags & 0x20) != 0;
    mouthOpen = (flags & 0x40) != 0;
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

  public boolean isTame() {
    return tame;
  }

  public void setTame(boolean tame) {
    this.tame = tame;
  }

  public boolean isSaddled() {
    return saddled;
  }

  public void setSaddled(boolean saddled) {
    this.saddled = saddled;
  }

  public boolean isBred() {
    return bred;
  }

  public void setBred(boolean bred) {
    this.bred = bred;
  }

  public boolean isEating() {
    return eating;
  }

  public void setEating(boolean eating) {
    this.eating = eating;
  }

  public boolean isRearing() {
    return rearing;
  }

  public void setRearing(boolean rearing) {
    this.rearing = rearing;
  }

  public boolean isMouthOpen() {
    return mouthOpen;
  }

  public void setMouthOpen(boolean mouthOpen) {
    this.mouthOpen = mouthOpen;
  }

  public UUID getOwner() {
    return owner;
  }

  public void setOwner(UUID owner) {
    this.owner = owner;
  }
}
