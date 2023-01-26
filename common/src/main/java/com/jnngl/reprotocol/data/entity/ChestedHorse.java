package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class ChestedHorse extends AbstractHorse {

  private boolean hasChest = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(hasChest));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    hasChest = metadata.read(false);
  }

  public boolean isHasChest() {
    return hasChest;
  }

  public void setHasChest(boolean hasChest) {
    this.hasChest = hasChest;
  }
}
