package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.BlockPos;
import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.PositionMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Dolphin extends WaterAnimal {

  private BlockPos treasurePosition = BlockPos.ZERO;
  private boolean hasFish = false;
  private int moistureLevel = 2400;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(BlockPos.ZERO, new PositionMetadataItem(treasurePosition));
    metadata.write(false, new BooleanMetadataItem(hasFish));
    metadata.write(2400, new VarintMetadataItem(moistureLevel));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    treasurePosition = metadata.read(BlockPos.ZERO);
    hasFish = metadata.read(false);
    moistureLevel = metadata.read(2400);
  }

  public BlockPos getTreasurePosition() {
    return treasurePosition;
  }

  public void setTreasurePosition(BlockPos treasurePosition) {
    this.treasurePosition = treasurePosition;
  }

  public boolean isHasFish() {
    return hasFish;
  }

  public void setHasFish(boolean hasFish) {
    this.hasFish = hasFish;
  }

  public int getMoistureLevel() {
    return moistureLevel;
  }

  public void setMoistureLevel(int moistureLevel) {
    this.moistureLevel = moistureLevel;
  }
}
