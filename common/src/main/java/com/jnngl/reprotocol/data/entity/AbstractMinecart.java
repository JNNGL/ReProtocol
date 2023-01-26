package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.FloatMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class AbstractMinecart extends Entity {

  private int shakingPower = 0;
  private int shakingDirection = 1;
  private float shakingMultiplier = 0;
  private int blockData = 0;
  private int blockY = 6;
  private boolean showBlock;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(0, new VarintMetadataItem(shakingPower));
    metadata.write(1, new VarintMetadataItem(shakingDirection));
    metadata.write(0.0F, new FloatMetadataItem(shakingMultiplier));
    metadata.write(0, new VarintMetadataItem(blockData));
    metadata.write(6, new VarintMetadataItem(blockY));
    metadata.write(false, new BooleanMetadataItem(showBlock));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    shakingPower = metadata.read(0);
    shakingDirection = metadata.read(0);
    shakingMultiplier = metadata.read(0.0F);
    blockData = metadata.read(0);
    blockY = metadata.read(6);
    showBlock = metadata.read(false);
  }

  public int getShakingPower() {
    return shakingPower;
  }

  public void setShakingPower(int shakingPower) {
    this.shakingPower = shakingPower;
  }

  public int getShakingDirection() {
    return shakingDirection;
  }

  public void setShakingDirection(int shakingDirection) {
    this.shakingDirection = shakingDirection;
  }

  public float getShakingMultiplier() {
    return shakingMultiplier;
  }

  public void setShakingMultiplier(float shakingMultiplier) {
    this.shakingMultiplier = shakingMultiplier;
  }

  public int getBlockData() {
    return blockData;
  }

  public void setBlockData(int blockData) {
    this.blockData = blockData;
  }

  public int getBlockY() {
    return blockY;
  }

  public void setBlockY(int blockY) {
    this.blockY = blockY;
  }

  public boolean isShowBlock() {
    return showBlock;
  }

  public void setShowBlock(boolean showBlock) {
    this.showBlock = showBlock;
  }
}
