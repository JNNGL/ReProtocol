package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Goat extends Animal {

  private boolean screaming = false;
  private boolean hasLeftHorn = true;
  private boolean hasRightHorn = true;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(screaming));
    metadata.write(true, new BooleanMetadataItem(hasLeftHorn));
    metadata.write(true, new BooleanMetadataItem(hasRightHorn));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    screaming = metadata.read(false);
    hasLeftHorn = metadata.read(true);
    hasRightHorn = metadata.read(true);
  }

  public boolean isScreaming() {
    return screaming;
  }

  public void setScreaming(boolean screaming) {
    this.screaming = screaming;
  }

  public boolean isHasLeftHorn() {
    return hasLeftHorn;
  }

  public void setHasLeftHorn(boolean hasLeftHorn) {
    this.hasLeftHorn = hasLeftHorn;
  }

  public boolean isHasRightHorn() {
    return hasRightHorn;
  }

  public void setHasRightHorn(boolean hasRightHorn) {
    this.hasRightHorn = hasRightHorn;
  }
}
