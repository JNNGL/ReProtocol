package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.FrogVariant;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.FrogVariantMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Frog extends Animal {

  private FrogVariant frogVariant = FrogVariant.TEMPERATE;
  private int tongueTarget = 0;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(FrogVariant.TEMPERATE, new FrogVariantMetadataItem(frogVariant));
    metadata.write(0, new VarintMetadataItem(tongueTarget));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    frogVariant = metadata.read(FrogVariant.TEMPERATE);
    tongueTarget = metadata.read(0);
  }

  public FrogVariant getFrogVariant() {
    return frogVariant;
  }

  public void setFrogVariant(FrogVariant frogVariant) {
    this.frogVariant = frogVariant;
  }

  public int getTongueTarget() {
    return tongueTarget;
  }

  public void setTongueTarget(int tongueTarget) {
    this.tongueTarget = tongueTarget;
  }
}
