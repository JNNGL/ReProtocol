package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Piglin extends BasePiglin {

  private boolean baby = false;
  private boolean chargingCrossbow = false;
  private boolean dancing = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(baby));
    metadata.write(false, new BooleanMetadataItem(chargingCrossbow));
    metadata.write(false, new BooleanMetadataItem(dancing));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    baby = metadata.read(false);
    chargingCrossbow = metadata.read(false);
    dancing = metadata.read(false);
  }

  public boolean isBaby() {
    return baby;
  }

  public void setBaby(boolean baby) {
    this.baby = baby;
  }

  public boolean isChargingCrossbow() {
    return chargingCrossbow;
  }

  public void setChargingCrossbow(boolean chargingCrossbow) {
    this.chargingCrossbow = chargingCrossbow;
  }

  public boolean isDancing() {
    return dancing;
  }

  public void setDancing(boolean dancing) {
    this.dancing = dancing;
  }
}
