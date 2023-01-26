package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.FloatMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.NBTMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.data.nbt.NbtTag;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Player extends LivingEntity {

  private float additionalHearts = 0.0F;
  private int score = 0;
  private boolean capeEnabled = false;
  private boolean jackedEnabled = false;
  private boolean leftSleeveEnabled = false;
  private boolean rightSleeveEnabled = false;
  private boolean leftPantsEnabled = false;
  private boolean rightPantsEnabled = false;
  private boolean hatEnabled = false;
  private byte mainHand = 1;
  private NbtTag leftShoulder = null;
  private NbtTag rightShoulder = null;

  public byte getSkinParts() {
    return (byte) ((capeEnabled ? 0x01 : 0x00) |
        (jackedEnabled ? 0x02 : 0x00) |
        (leftSleeveEnabled ? 0x04 : 0x00) |
        (rightSleeveEnabled ? 0x08 : 0x00) |
        (leftPantsEnabled ? 0x10 : 0x00) |
        (rightPantsEnabled ? 0x20 : 0x00) |
        (hatEnabled ? 0x40 : 0x00));
  }

  public void setSkinParts(byte skinParts) {
    capeEnabled = (skinParts & 0x01) != 0;
    jackedEnabled = (skinParts & 0x02) != 0;
    leftSleeveEnabled = (skinParts & 0x04) != 0;
    rightSleeveEnabled = (skinParts & 0x08) != 0;
    leftPantsEnabled = (skinParts & 0x10) != 0;
    rightPantsEnabled = (skinParts & 0x20) != 0;
    hatEnabled = (skinParts & 0x40) != 0;
  }

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(0.0F, new FloatMetadataItem(additionalHearts));
    metadata.write(0, new VarintMetadataItem(score));
    metadata.write((byte) 0, new ByteMetadataItem(getSkinParts()));
    metadata.write((byte) 1, new ByteMetadataItem(mainHand));
    metadata.write(null, new NBTMetadataItem(leftShoulder));
    metadata.write(null, new NBTMetadataItem(rightShoulder));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    additionalHearts = metadata.read(0.0F);
    score = metadata.read(0);
    setSkinParts(metadata.read((byte) 0));
    mainHand = metadata.read((byte) 1);
    leftShoulder = metadata.read(null);
    rightShoulder = metadata.read(null);
  }

  public float getAdditionalHearts() {
    return additionalHearts;
  }

  public void setAdditionalHearts(float additionalHearts) {
    this.additionalHearts = additionalHearts;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public boolean isCapeEnabled() {
    return capeEnabled;
  }

  public void setCapeEnabled(boolean capeEnabled) {
    this.capeEnabled = capeEnabled;
  }

  public boolean isJackedEnabled() {
    return jackedEnabled;
  }

  public void setJackedEnabled(boolean jackedEnabled) {
    this.jackedEnabled = jackedEnabled;
  }

  public boolean isLeftSleeveEnabled() {
    return leftSleeveEnabled;
  }

  public void setLeftSleeveEnabled(boolean leftSleeveEnabled) {
    this.leftSleeveEnabled = leftSleeveEnabled;
  }

  public boolean isRightSleeveEnabled() {
    return rightSleeveEnabled;
  }

  public void setRightSleeveEnabled(boolean rightSleeveEnabled) {
    this.rightSleeveEnabled = rightSleeveEnabled;
  }

  public boolean isLeftPantsEnabled() {
    return leftPantsEnabled;
  }

  public void setLeftPantsEnabled(boolean leftPantsEnabled) {
    this.leftPantsEnabled = leftPantsEnabled;
  }

  public boolean isRightPantsEnabled() {
    return rightPantsEnabled;
  }

  public void setRightPantsEnabled(boolean rightPantsEnabled) {
    this.rightPantsEnabled = rightPantsEnabled;
  }

  public boolean isHatEnabled() {
    return hatEnabled;
  }

  public void setHatEnabled(boolean hatEnabled) {
    this.hatEnabled = hatEnabled;
  }

  public byte getMainHand() {
    return mainHand;
  }

  public void setMainHand(byte mainHand) {
    this.mainHand = mainHand;
  }

  public NbtTag getLeftShoulder() {
    return leftShoulder;
  }

  public void setLeftShoulder(NbtTag leftShoulder) {
    this.leftShoulder = leftShoulder;
  }

  public NbtTag getRightShoulder() {
    return rightShoulder;
  }

  public void setRightShoulder(NbtTag rightShoulder) {
    this.rightShoulder = rightShoulder;
  }
}
