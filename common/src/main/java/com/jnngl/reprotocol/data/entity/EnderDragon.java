package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class EnderDragon extends Mob {

  private int dragonPhase = 10; // TODO: EnderDragonPhase enum

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(10, new VarintMetadataItem(dragonPhase));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    dragonPhase = metadata.read(10);
  }

  public int getDragonPhase() {
    return dragonPhase;
  }

  public void setDragonPhase(int dragonPhase) {
    this.dragonPhase = dragonPhase;
  }
}
