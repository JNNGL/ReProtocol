package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class WitherSkull extends Entity {

  private boolean invulnerable = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(invulnerable));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    invulnerable = metadata.read(false);
  }

  public boolean isInvulnerable() {
    return invulnerable;
  }

  public void setInvulnerable(boolean invulnerable) {
    this.invulnerable = invulnerable;
  }
}
