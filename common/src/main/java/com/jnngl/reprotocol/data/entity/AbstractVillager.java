package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class AbstractVillager extends AgeableMob {

  private int headShakeTimer = 0;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(0, new VarintMetadataItem(headShakeTimer));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    headShakeTimer = metadata.read(0);
  }

  public int getHeadShakeTimer() {
    return headShakeTimer;
  }

  public void setHeadShakeTimer(int headShakeTimer) {
    this.headShakeTimer = headShakeTimer;
  }
}
