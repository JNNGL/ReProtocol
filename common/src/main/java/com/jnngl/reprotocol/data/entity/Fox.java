package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.OptUUIDMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;
import java.util.UUID;

public class Fox extends Animal {

  private int type = 0;
  private boolean sitting = false;
  private boolean crouching = false;
  private boolean interested = false;
  private boolean pouncing = false;
  private boolean sleeping = false;
  private boolean faceplanted = false;
  private boolean defending = false;
  private UUID firstUUID = null;
  private UUID secondUUID = null;

  private byte getFlags(MinecraftVersion version) {
    return (byte) ((sitting ? 0x01 : 0x00) |
        (crouching ? 0x04 : 0x00) |
        (interested ? 0x08 : 0x00) |
        (pouncing ? 0x10 : 0x00) |
        (sleeping ? 0x20 : 0x00) |
        (faceplanted ? 0x40 : 0x00) |
        (defending ? 0x80 : 0x00));
  }

  private void setFlags(MinecraftVersion version, byte flags) {
    sitting = (flags & 0x01) != 0;
    crouching = (flags & 0x04) != 0;
    interested = (flags & 0x08) != 0;
    pouncing = (flags & 0x10) != 0;
    sleeping = (flags & 0x20) != 0;
    faceplanted = (flags & 0x40) != 0;
    defending = (flags & 0x80) != 0;
  }

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(0, new VarintMetadataItem(type));
    metadata.write((byte) 0, new ByteMetadataItem(getFlags(version)));
    metadata.write(null, new OptUUIDMetadataItem(firstUUID));
    metadata.write(null, new OptUUIDMetadataItem(secondUUID));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    type = metadata.read(0);
    setFlags(version, metadata.read((byte) 0));
    firstUUID = metadata.read(null);
    secondUUID = metadata.read(null);
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public boolean isSitting() {
    return sitting;
  }

  public void setSitting(boolean sitting) {
    this.sitting = sitting;
  }

  @Override
  public boolean isCrouching() {
    return crouching;
  }

  @Override
  public void setCrouching(boolean crouching) {
    this.crouching = crouching;
  }

  public boolean isInterested() {
    return interested;
  }

  public void setInterested(boolean interested) {
    this.interested = interested;
  }

  public boolean isPouncing() {
    return pouncing;
  }

  public void setPouncing(boolean pouncing) {
    this.pouncing = pouncing;
  }

  public boolean isSleeping() {
    return sleeping;
  }

  public void setSleeping(boolean sleeping) {
    this.sleeping = sleeping;
  }

  public boolean isFaceplanted() {
    return faceplanted;
  }

  public void setFaceplanted(boolean faceplanted) {
    this.faceplanted = faceplanted;
  }

  public boolean isDefending() {
    return defending;
  }

  public void setDefending(boolean defending) {
    this.defending = defending;
  }

  public UUID getFirstUUID() {
    return firstUUID;
  }

  public void setFirstUUID(UUID firstUUID) {
    this.firstUUID = firstUUID;
  }

  public UUID getSecondUUID() {
    return secondUUID;
  }

  public void setSecondUUID(UUID secondUUID) {
    this.secondUUID = secondUUID;
  }
}
