package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class ThrownTrident extends AbstractArrow {

  private int loyaltyLevel = 0;
  private boolean hasEnchantmentGlint = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(0, new VarintMetadataItem(loyaltyLevel));
    metadata.write(false, new BooleanMetadataItem(hasEnchantmentGlint));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    loyaltyLevel = metadata.read(0);
    hasEnchantmentGlint = metadata.read(false);
  }

  public int getLoyaltyLevel() {
    return loyaltyLevel;
  }

  public void setLoyaltyLevel(int loyaltyLevel) {
    this.loyaltyLevel = loyaltyLevel;
  }

  public boolean isHasEnchantmentGlint() {
    return hasEnchantmentGlint;
  }

  public void setHasEnchantmentGlint(boolean hasEnchantmentGlint) {
    this.hasEnchantmentGlint = hasEnchantmentGlint;
  }
}
