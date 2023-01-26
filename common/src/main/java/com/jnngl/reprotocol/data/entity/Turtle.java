package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.BlockPos;
import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.PositionMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Turtle extends Animal {

  private BlockPos homePosition = BlockPos.ZERO;
  private boolean hasEgg = false;
  private boolean layingEgg = false;
  private BlockPos travelPosition = BlockPos.ZERO;
  private boolean goingHome = false;
  private boolean traveling = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(BlockPos.ZERO, new PositionMetadataItem(homePosition));
    metadata.write(false, new BooleanMetadataItem(hasEgg));
    metadata.write(false, new BooleanMetadataItem(layingEgg));
    metadata.write(BlockPos.ZERO, new PositionMetadataItem(travelPosition));
    metadata.write(false, new BooleanMetadataItem(goingHome));
    metadata.write(false, new BooleanMetadataItem(traveling));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    homePosition = metadata.read(BlockPos.ZERO);
    hasEgg = metadata.read(false);
    layingEgg = metadata.read(false);
    travelPosition = metadata.read(BlockPos.ZERO);
    goingHome = metadata.read(false);
    traveling = metadata.read(false);
  }

  public BlockPos getHomePosition() {
    return homePosition;
  }

  public void setHomePosition(BlockPos homePosition) {
    this.homePosition = homePosition;
  }

  public boolean isHasEgg() {
    return hasEgg;
  }

  public void setHasEgg(boolean hasEgg) {
    this.hasEgg = hasEgg;
  }

  public boolean isLayingEgg() {
    return layingEgg;
  }

  public void setLayingEgg(boolean layingEgg) {
    this.layingEgg = layingEgg;
  }

  public BlockPos getTravelPosition() {
    return travelPosition;
  }

  public void setTravelPosition(BlockPos travelPosition) {
    this.travelPosition = travelPosition;
  }

  public boolean isGoingHome() {
    return goingHome;
  }

  public void setGoingHome(boolean goingHome) {
    this.goingHome = goingHome;
  }

  public boolean isTraveling() {
    return traveling;
  }

  public void setTraveling(boolean traveling) {
    this.traveling = traveling;
  }
}
