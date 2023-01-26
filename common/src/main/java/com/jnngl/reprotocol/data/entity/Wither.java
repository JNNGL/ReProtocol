package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Wither extends Monster {

  private int centerHeadTarget = 0;
  private int leftHeadTarget = 0;
  private int rightHeadTarget = 0;
  private int invulnerableTime = 0;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(0, new VarintMetadataItem(centerHeadTarget));
    metadata.write(0, new VarintMetadataItem(leftHeadTarget));
    metadata.write(0, new VarintMetadataItem(rightHeadTarget));
    metadata.write(0, new VarintMetadataItem(invulnerableTime));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    centerHeadTarget = metadata.read(0);
    leftHeadTarget = metadata.read(0);
    rightHeadTarget = metadata.read(0);
    invulnerableTime = metadata.read(0);
  }

  public int getCenterHeadTarget() {
    return centerHeadTarget;
  }

  public void setCenterHeadTarget(int centerHeadTarget) {
    this.centerHeadTarget = centerHeadTarget;
  }

  public int getLeftHeadTarget() {
    return leftHeadTarget;
  }

  public void setLeftHeadTarget(int leftHeadTarget) {
    this.leftHeadTarget = leftHeadTarget;
  }

  public int getRightHeadTarget() {
    return rightHeadTarget;
  }

  public void setRightHeadTarget(int rightHeadTarget) {
    this.rightHeadTarget = rightHeadTarget;
  }

  public int getInvulnerableTime() {
    return invulnerableTime;
  }

  public void setInvulnerableTime(int invulnerableTime) {
    this.invulnerableTime = invulnerableTime;
  }
}
