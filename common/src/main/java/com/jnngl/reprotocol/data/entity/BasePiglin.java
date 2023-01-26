package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class BasePiglin extends Monster {

  private boolean immuneToZombification = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(immuneToZombification));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    immuneToZombification = metadata.read(false);
  }

  public boolean isImmuneToZombification() {
    return immuneToZombification;
  }

  public void setImmuneToZombification(boolean immuneToZombification) {
    this.immuneToZombification = immuneToZombification;
  }
}
