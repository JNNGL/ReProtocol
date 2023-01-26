package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Sheep extends Animal {

  private int color = 0;
  private boolean sheared = false;

  private byte getByte(MinecraftVersion version) {
    return (byte) ((color & 0x0F) | (sheared ? 0x10 : 0x00));
  }

  private void setByte(byte value) {
    color = value & 0x0F;
    sheared = (value & 0x10) != 0;
  }

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write((byte) 0, new ByteMetadataItem(getByte(version)));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    setByte(metadata.read((byte) 0));
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public boolean isSheared() {
    return sheared;
  }

  public void setSheared(boolean sheared) {
    this.sheared = sheared;
  }
}
