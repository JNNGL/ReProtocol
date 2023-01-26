package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.FloatMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Boat extends Entity {

  private int lastHitTime = 0;
  private int forwardDirection = 1;
  private float damageTaken = 0.0F;
  private int type = 0;
  private boolean leftPaddleTurning = false;
  private boolean rightPaddleTurning = false;
  private int splashTimer = 0;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(0, new VarintMetadataItem(lastHitTime));
    metadata.write(1, new VarintMetadataItem(forwardDirection));
    metadata.write(0.0F, new FloatMetadataItem(damageTaken));
    metadata.write(0, new VarintMetadataItem(type));
    metadata.write(false, new BooleanMetadataItem(leftPaddleTurning));
    metadata.write(false, new BooleanMetadataItem(rightPaddleTurning));
    metadata.write(0, new VarintMetadataItem(splashTimer));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    lastHitTime = metadata.read(0);
    forwardDirection = metadata.read(1);
    damageTaken = metadata.read(0.0F);
    type = metadata.read(0);
    leftPaddleTurning = metadata.read(false);
    rightPaddleTurning = metadata.read(false);
    splashTimer = metadata.read(0);
  }

  public int getLastHitTime() {
    return lastHitTime;
  }

  public void setLastHitTime(int lastHitTime) {
    this.lastHitTime = lastHitTime;
  }

  public int getForwardDirection() {
    return forwardDirection;
  }

  public void setForwardDirection(int forwardDirection) {
    this.forwardDirection = forwardDirection;
  }

  public float getDamageTaken() {
    return damageTaken;
  }

  public void setDamageTaken(float damageTaken) {
    this.damageTaken = damageTaken;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public boolean isLeftPaddleTurning() {
    return leftPaddleTurning;
  }

  public void setLeftPaddleTurning(boolean leftPaddleTurning) {
    this.leftPaddleTurning = leftPaddleTurning;
  }

  public boolean isRightPaddleTurning() {
    return rightPaddleTurning;
  }

  public void setRightPaddleTurning(boolean rightPaddleTurning) {
    this.rightPaddleTurning = rightPaddleTurning;
  }

  public int getSplashTimer() {
    return splashTimer;
  }

  public void setSplashTimer(int splashTimer) {
    this.splashTimer = splashTimer;
  }
}
