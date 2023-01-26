package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Pig extends Animal {

  private boolean hasSaddle = false;
  private int boostTime = 0;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(hasSaddle));
    metadata.write(0, new VarintMetadataItem(boostTime));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    hasSaddle = metadata.read(false);
    boostTime = metadata.read(0);
  }

  public boolean isHasSaddle() {
    return hasSaddle;
  }

  public void setHasSaddle(boolean hasSaddle) {
    this.hasSaddle = hasSaddle;
  }

  public int getBoostTime() {
    return boostTime;
  }

  public void setBoostTime(int boostTime) {
    this.boostTime = boostTime;
  }
}
