package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Allay extends AmbientCreature {

  private boolean dancing = false;
  private boolean canDuplicate = true;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(dancing));
    metadata.write(true, new BooleanMetadataItem(canDuplicate));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    dancing = metadata.read(false);
    canDuplicate = metadata.read(true);
  }

  public boolean isDancing() {
    return dancing;
  }

  public void setDancing(boolean dancing) {
    this.dancing = dancing;
  }

  public boolean isCanDuplicate() {
    return canDuplicate;
  }

  public void setCanDuplicate(boolean canDuplicate) {
    this.canDuplicate = canDuplicate;
  }
}
