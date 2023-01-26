package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Zombie extends Monster {

  private boolean isBaby = false;
  private int type = 0;
  private boolean becomingDrowned = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(isBaby));
    metadata.write(0, new VarintMetadataItem(type));
    metadata.write(false, new BooleanMetadataItem(becomingDrowned));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    isBaby = metadata.read(false);
    type = metadata.read(0);
    becomingDrowned = metadata.read(false);
  }

  public boolean isBaby() {
    return isBaby;
  }

  public void setBaby(boolean baby) {
    isBaby = baby;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public boolean isBecomingDrowned() {
    return becomingDrowned;
  }

  public void setBecomingDrowned(boolean becomingDrowned) {
    this.becomingDrowned = becomingDrowned;
  }
}
