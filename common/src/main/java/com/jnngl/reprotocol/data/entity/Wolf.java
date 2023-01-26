package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Wolf extends TameableAnimal {

  private boolean begging = false;
  private int collarColor = 14;
  private int angerTimer = 0;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(begging));
    metadata.write(14, new VarintMetadataItem(collarColor));
    metadata.write(0, new VarintMetadataItem(angerTimer));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    begging = metadata.read(false);
    collarColor = metadata.read(14);
    angerTimer = metadata.read(0);
  }

  public boolean isBegging() {
    return begging;
  }

  public void setBegging(boolean begging) {
    this.begging = begging;
  }

  public int getCollarColor() {
    return collarColor;
  }

  public void setCollarColor(int collarColor) {
    this.collarColor = collarColor;
  }

  public int getAngerTimer() {
    return angerTimer;
  }

  public void setAngerTimer(int angerTimer) {
    this.angerTimer = angerTimer;
  }
}
