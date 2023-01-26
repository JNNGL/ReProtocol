package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class SnowGolem extends AbstractGolem {

  private boolean hasPumpkinHat = true;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write((byte) 0, new ByteMetadataItem((byte) (hasPumpkinHat ? 0x10 : 0x00)));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    hasPumpkinHat = (metadata.read((byte) 0) & 0x10) != 0;
  }

  public boolean isHasPumpkinHat() {
    return hasPumpkinHat;
  }

  public void setHasPumpkinHat(boolean hasPumpkinHat) {
    this.hasPumpkinHat = hasPumpkinHat;
  }
}
