package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Bat extends AmbientCreature {

  private boolean hanging = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write((byte) 0, new ByteMetadataItem((byte) (hanging ? 0x01 : 0x00)));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    hanging = metadata.read((byte) 0) != 0;
  }

  public boolean isHanging() {
    return hanging;
  }

  public void setHanging(boolean hanging) {
    this.hanging = hanging;
  }
}
