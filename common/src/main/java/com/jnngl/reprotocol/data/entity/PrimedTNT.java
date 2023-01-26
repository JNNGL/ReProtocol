package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class PrimedTNT extends Entity {

  private int fuseTime = 80;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(80, new VarintMetadataItem(fuseTime));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    fuseTime = metadata.read(80);
  }

  public int getFuseTime() {
    return fuseTime;
  }

  public void setFuseTime(int fuseTime) {
    this.fuseTime = fuseTime;
  }
}
