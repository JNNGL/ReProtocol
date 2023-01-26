package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.OptVarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class FishingHook extends Entity {

  private Integer entity = null;
  private boolean catchable = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(null, new OptVarintMetadataItem(entity));
    metadata.write(false, new BooleanMetadataItem(false));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    entity = metadata.read(null);
    catchable = metadata.read(false);
  }

  public Integer getEntity() {
    return entity;
  }

  public void setEntity(Integer entity) {
    this.entity = entity;
  }

  public boolean isCatchable() {
    return catchable;
  }

  public void setCatchable(boolean catchable) {
    this.catchable = catchable;
  }
}
