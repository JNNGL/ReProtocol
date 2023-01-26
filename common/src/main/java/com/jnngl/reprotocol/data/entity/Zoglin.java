package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Zoglin extends Monster {

  private boolean isBaby = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(isBaby));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    isBaby = metadata.read(false);
  }

  public boolean isBaby() {
    return isBaby;
  }

  public void setBaby(boolean baby) {
    isBaby = baby;
  }
}
