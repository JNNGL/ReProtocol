package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Witch extends Raider {

  private boolean drinkingPotion = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(drinkingPotion));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    drinkingPotion = metadata.read(false);
  }

  public boolean isDrinkingPotion() {
    return drinkingPotion;
  }

  public void setDrinkingPotion(boolean drinkingPotion) {
    this.drinkingPotion = drinkingPotion;
  }
}
