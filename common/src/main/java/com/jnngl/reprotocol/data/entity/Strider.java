package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Strider extends Animal {

  private int boostTime = 0;
  private boolean shaking = false;
  private boolean hasSaddle = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(0, new VarintMetadataItem(boostTime));
    metadata.write(false, new BooleanMetadataItem(shaking));
    metadata.write(false, new BooleanMetadataItem(hasSaddle));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    boostTime = metadata.read(0);
    shaking = metadata.read(false);
    hasSaddle = metadata.read(false);
  }

  public int getBoostTime() {
    return boostTime;
  }

  public void setBoostTime(int boostTime) {
    this.boostTime = boostTime;
  }

  public boolean isShaking() {
    return shaking;
  }

  public void setShaking(boolean shaking) {
    this.shaking = shaking;
  }

  public boolean isHasSaddle() {
    return hasSaddle;
  }

  public void setHasSaddle(boolean hasSaddle) {
    this.hasSaddle = hasSaddle;
  }
}
