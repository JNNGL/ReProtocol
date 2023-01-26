package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Spider extends Monster {

  private boolean climbing = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write((byte) 0, new ByteMetadataItem((byte) (climbing ? 0x01 : 0x00)));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    climbing = metadata.read((byte) 0) != 0;
  }

  public boolean isClimbing() {
    return climbing;
  }

  public void setClimbing(boolean climbing) {
    this.climbing = climbing;
  }
}
