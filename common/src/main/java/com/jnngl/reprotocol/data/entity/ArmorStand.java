package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.Rotation;
import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.RotationMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class ArmorStand extends LivingEntity {

  private static final Rotation LEFT_ARM_ROTATION = new Rotation(-10.0F, 0.0F, -10.0F);
  private static final Rotation RIGHT_ARM_ROTATION = new Rotation(-15.0F, 0.0F, 10.0F);
  private static final Rotation LEFT_LEG_ROTATION = new Rotation(-1.0F, 0.0F, -1.0F);
  private static final Rotation RIGHT_LEG_ROTATION = new Rotation(1.0F, 0.0F, 1.0F);

  private boolean small = false;
  private boolean hasArms = false;
  private boolean noBasePlate = false;
  private boolean marker = false;
  private Rotation headRotation = Rotation.ZERO;
  private Rotation bodyRotation = Rotation.ZERO;
  private Rotation leftArmRotation = LEFT_ARM_ROTATION;
  private Rotation rightArmRotation = RIGHT_ARM_ROTATION;
  private Rotation leftLegRotation = LEFT_LEG_ROTATION;
  private Rotation rightLegRotation = RIGHT_LEG_ROTATION;

  private byte getFlags(MinecraftVersion version) {
    return  (byte) ((small ? 0x01 : 0x00) |
        (hasArms ? 0x04 : 0x00) |
        (noBasePlate ? 0x08 : 0x00) |
        (marker ? 0x10 : 0x00));
  }

  private void setFlags(MinecraftVersion version, byte flags) {
    small = (flags & 0x01) != 0;
    hasArms = (flags & 0x04) != 0;
    noBasePlate = (flags & 0x08) != 0;
    marker = (flags & 0x10) != 0;
  }

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write((byte) 0, new ByteMetadataItem(getFlags(version)));
    metadata.write(Rotation.ZERO, new RotationMetadataItem(headRotation));
    metadata.write(Rotation.ZERO, new RotationMetadataItem(bodyRotation));
    metadata.write(LEFT_ARM_ROTATION, new RotationMetadataItem(leftArmRotation));
    metadata.write(RIGHT_ARM_ROTATION, new RotationMetadataItem(rightArmRotation));
    metadata.write(LEFT_LEG_ROTATION, new RotationMetadataItem(leftLegRotation));
    metadata.write(RIGHT_LEG_ROTATION, new RotationMetadataItem(rightLegRotation));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    setFlags(version, metadata.read((byte) 0));
    headRotation = metadata.read(Rotation.ZERO);
    bodyRotation = metadata.read(Rotation.ZERO);
    leftArmRotation = metadata.read(LEFT_ARM_ROTATION);
    rightArmRotation = metadata.read(RIGHT_ARM_ROTATION);
    leftLegRotation = metadata.read(LEFT_LEG_ROTATION);
    rightLegRotation = metadata.read(RIGHT_LEG_ROTATION);
  }

  public boolean isSmall() {
    return small;
  }

  public void setSmall(boolean small) {
    this.small = small;
  }

  public boolean isHasArms() {
    return hasArms;
  }

  public void setHasArms(boolean hasArms) {
    this.hasArms = hasArms;
  }

  public boolean isNoBasePlate() {
    return noBasePlate;
  }

  public void setNoBasePlate(boolean noBasePlate) {
    this.noBasePlate = noBasePlate;
  }

  public boolean isMarker() {
    return marker;
  }

  public void setMarker(boolean marker) {
    this.marker = marker;
  }

  public Rotation getHeadRotation() {
    return headRotation;
  }

  public void setHeadRotation(Rotation headRotation) {
    this.headRotation = headRotation;
  }

  public Rotation getBodyRotation() {
    return bodyRotation;
  }

  public void setBodyRotation(Rotation bodyRotation) {
    this.bodyRotation = bodyRotation;
  }

  public Rotation getLeftArmRotation() {
    return leftArmRotation;
  }

  public void setLeftArmRotation(Rotation leftArmRotation) {
    this.leftArmRotation = leftArmRotation;
  }

  public Rotation getRightArmRotation() {
    return rightArmRotation;
  }

  public void setRightArmRotation(Rotation rightArmRotation) {
    this.rightArmRotation = rightArmRotation;
  }

  public Rotation getLeftLegRotation() {
    return leftLegRotation;
  }

  public void setLeftLegRotation(Rotation leftLegRotation) {
    this.leftLegRotation = leftLegRotation;
  }

  public Rotation getRightLegRotation() {
    return rightLegRotation;
  }

  public void setRightLegRotation(Rotation rightLegRotation) {
    this.rightLegRotation = rightLegRotation;
  }
}
