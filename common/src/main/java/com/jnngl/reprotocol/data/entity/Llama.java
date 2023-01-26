package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Llama extends ChestedHorse {

  private int strength = 0;
  private int carpetColor = -1;
  private int variant = 0;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(0, new VarintMetadataItem(strength));
    metadata.write(-1, new VarintMetadataItem(carpetColor));
    metadata.write(0, new VarintMetadataItem(variant));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    strength = metadata.read(0);
    carpetColor = metadata.read(-1);
    variant = metadata.read(0);
  }

  public int getStrength() {
    return strength;
  }

  public void setStrength(int strength) {
    this.strength = strength;
  }

  public int getCarpetColor() {
    return carpetColor;
  }

  public void setCarpetColor(int carpetColor) {
    this.carpetColor = carpetColor;
  }

  public int getVariant() {
    return variant;
  }

  public void setVariant(int variant) {
    this.variant = variant;
  }
}
