package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Bee extends Animal {

  private boolean angry = false;
  private boolean hasStrung = false;
  private boolean hasNectar = false;
  private int angerTime = 0;

  private byte getFlags(MinecraftVersion version) {
    return (byte) ((angry ? 0x02 : 0x00) |
        (hasStrung ? 0x04 : 0x00) |
        (hasNectar ? 0x08 : 0x00));
  }

  private void setFlags(MinecraftVersion version, byte flags) {
    angry = (flags & 0x02) != 0;
    hasStrung = (flags & 0x04) != 0;
    hasNectar = (flags & 0x08) != 0;
  }

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write((byte) 0, new ByteMetadataItem(getFlags(version)));
    metadata.write(0, new VarintMetadataItem(angerTime));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    setFlags(version, metadata.read((byte) 0));
    angerTime = metadata.read(0);
  }

  public boolean isAngry() {
    return angry;
  }

  public void setAngry(boolean angry) {
    this.angry = angry;
  }

  public boolean isHasStrung() {
    return hasStrung;
  }

  public void setHasStrung(boolean hasStrung) {
    this.hasStrung = hasStrung;
  }

  public boolean isHasNectar() {
    return hasNectar;
  }

  public void setHasNectar(boolean hasNectar) {
    this.hasNectar = hasNectar;
  }

  public int getAngerTime() {
    return angerTime;
  }

  public void setAngerTime(int angerTime) {
    this.angerTime = angerTime;
  }
}
