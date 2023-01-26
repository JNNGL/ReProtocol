package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.CatVariant;
import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.CatVariantMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Cat extends TameableAnimal {

  private CatVariant catVariant = CatVariant.BLACK;
  private boolean lying = false;
  private boolean relaxed = false;
  private int collarColor = 14;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(CatVariant.BLACK, new CatVariantMetadataItem(catVariant));
    metadata.write(false, new BooleanMetadataItem(lying));
    metadata.write(false, new BooleanMetadataItem(relaxed));
    metadata.write(14, new VarintMetadataItem(collarColor));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    catVariant = metadata.read(CatVariant.BLACK);
    lying = metadata.read(false);
    relaxed = metadata.read(false);
    collarColor = metadata.read(14);
  }

  public CatVariant getCatVariant() {
    return catVariant;
  }

  public void setCatVariant(CatVariant catVariant) {
    this.catVariant = catVariant;
  }

  public boolean isLying() {
    return lying;
  }

  public void setLying(boolean lying) {
    this.lying = lying;
  }

  public boolean isRelaxed() {
    return relaxed;
  }

  public void setRelaxed(boolean relaxed) {
    this.relaxed = relaxed;
  }

  public int getCollarColor() {
    return collarColor;
  }

  public void setCollarColor(int collarColor) {
    this.collarColor = collarColor;
  }
}
