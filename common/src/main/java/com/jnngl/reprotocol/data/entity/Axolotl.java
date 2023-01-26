package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Axolotl extends Animal {

  private int variant = 0;
  private boolean playingDead = false;
  private boolean fromBucket = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(0, new VarintMetadataItem(variant));
    metadata.write(false, new BooleanMetadataItem(playingDead));
    metadata.write(false, new BooleanMetadataItem(fromBucket));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    variant = metadata.read(0);
    playingDead = metadata.read(false);
    fromBucket = metadata.read(false);
  }

  public int getVariant() {
    return variant;
  }

  public void setVariant(int variant) {
    this.variant = variant;
  }

  public boolean isPlayingDead() {
    return playingDead;
  }

  public void setPlayingDead(boolean playingDead) {
    this.playingDead = playingDead;
  }

  public boolean isFromBucket() {
    return fromBucket;
  }

  public void setFromBucket(boolean fromBucket) {
    this.fromBucket = fromBucket;
  }
}
