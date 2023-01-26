package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class MinecartFurnace extends AbstractMinecart {

  private boolean hasFuel = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(hasFuel));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    hasFuel = metadata.read(false);
  }

  public boolean isHasFuel() {
    return hasFuel;
  }

  public void setHasFuel(boolean hasFuel) {
    this.hasFuel = hasFuel;
  }
}
