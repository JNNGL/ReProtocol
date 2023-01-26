package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class AbstractFish extends WaterAnimal {

  private boolean fromBucket = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(fromBucket));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    fromBucket = metadata.read(false);
  }

  public boolean isFromBucket() {
    return fromBucket;
  }

  public void setFromBucket(boolean fromBucket) {
    this.fromBucket = fromBucket;
  }
}
