package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BlockMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Enderman extends Monster {

  private int block; // TODO: Block class
  private boolean screaming = false;
  private boolean staring = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(0, new BlockMetadataItem(block));
    metadata.write(false, new BooleanMetadataItem(screaming));
    metadata.write(false, new BooleanMetadataItem(staring));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    block = metadata.read(0);
    screaming = metadata.read(false);
    staring = metadata.read(false);
  }

  public int getBlock() {
    return block;
  }

  public void setBlock(int block) {
    this.block = block;
  }

  public boolean isScreaming() {
    return screaming;
  }

  public void setScreaming(boolean screaming) {
    this.screaming = screaming;
  }

  public boolean isStaring() {
    return staring;
  }

  public void setStaring(boolean staring) {
    this.staring = staring;
  }
}
