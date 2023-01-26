package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.Pose;
import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.OptChatMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.PoseMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Entity extends AbstractEntity {

  private boolean onFire = false;
  private boolean crouching = false;
  private boolean riding = false;
  private boolean sprinting = false;
  private boolean swimming = false;
  private boolean invisible = false;
  private boolean glowing = false;
  private boolean flying = false;
  private int airTicks = 300;
  private String customName = null;
  private boolean customNameVisible = false;
  private boolean silent = false;
  private boolean noGravity = false;
  private Pose pose = Pose.STANDING;
  private int frozenTicks = 0;

  private byte getFlags(MinecraftVersion version) {
    return (byte) ((onFire ? 0x01 : 0x00)
            | (crouching ? 0x02 : 0x00)
            | (riding ? 0x04 : 0x00)
            | (sprinting ? 0x08 : 0x00)
            | (swimming ? 0x10 : 0x00)
            | (invisible ? 0x20 : 0x00)
            | (glowing ? 0x40 : 0x00)
            | (flying ? 0x80 : 0x00));
  }

  private void setFlags(MinecraftVersion version, byte flags) {
    onFire = (flags & 0x01) != 0;
    crouching = (flags & 0x02) != 0;
    riding = (flags & 0x04) != 0;
    sprinting = (flags & 0x08) != 0;
    swimming = (flags & 0x10) != 0;
    invisible = (flags & 0x20) != 0;
    glowing = (flags & 0x40) != 0;
    flying = (flags & 0x80) != 0;
  }

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    metadata.write((byte) 0, new ByteMetadataItem(getFlags(version)));
    metadata.write(300, new VarintMetadataItem(airTicks));
    metadata.write(null, new OptChatMetadataItem(customName));
    metadata.write(false, new BooleanMetadataItem(customNameVisible));
    metadata.write(false, new BooleanMetadataItem(customNameVisible));
    metadata.write(false, new BooleanMetadataItem(silent));
    metadata.write(Pose.STANDING, new PoseMetadataItem(pose));
    metadata.write(0, new VarintMetadataItem(frozenTicks));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    setFlags(version, metadata.read((byte) 0));
    airTicks = metadata.read(300);
    customName = metadata.read(null);
    customNameVisible = metadata.read(false);
    silent = metadata.read(false);
    noGravity = metadata.read(false);
    pose = metadata.read(Pose.STANDING);
    frozenTicks = metadata.read(0);
  }

  public boolean isOnFire() {
    return onFire;
  }

  public void setOnFire(boolean onFire) {
    this.onFire = onFire;
  }

  public boolean isCrouching() {
    return crouching;
  }

  public void setCrouching(boolean crouching) {
    this.crouching = crouching;
  }

  public boolean isRiding() {
    return riding;
  }

  public void setRiding(boolean riding) {
    this.riding = riding;
  }

  public boolean isSprinting() {
    return sprinting;
  }

  public void setSprinting(boolean sprinting) {
    this.sprinting = sprinting;
  }

  public boolean isSwimming() {
    return swimming;
  }

  public void setSwimming(boolean swimming) {
    this.swimming = swimming;
  }

  public boolean isInvisible() {
    return invisible;
  }

  public void setInvisible(boolean invisible) {
    this.invisible = invisible;
  }

  public boolean isGlowing() {
    return glowing;
  }

  public void setGlowing(boolean glowing) {
    this.glowing = glowing;
  }

  public boolean isFlying() {
    return flying;
  }

  public void setFlying(boolean flying) {
    this.flying = flying;
  }

  public int getAirTicks() {
    return airTicks;
  }

  public void setAirTicks(int airTicks) {
    this.airTicks = airTicks;
  }

  public String getCustomName() {
    return customName;
  }

  public void setCustomName(String customName) {
    this.customName = customName;
  }

  public boolean isCustomNameVisible() {
    return customNameVisible;
  }

  public void setCustomNameVisible(boolean customNameVisible) {
    this.customNameVisible = customNameVisible;
  }

  public boolean isSilent() {
    return silent;
  }

  public void setSilent(boolean silent) {
    this.silent = silent;
  }

  public boolean isNoGravity() {
    return noGravity;
  }

  public void setNoGravity(boolean noGravity) {
    this.noGravity = noGravity;
  }

  public Pose getPose() {
    return pose;
  }

  public void setPose(Pose pose) {
    this.pose = pose;
  }

  public int getFrozenTicks() {
    return frozenTicks;
  }

  public void setFrozenTicks(int frozenTicks) {
    this.frozenTicks = frozenTicks;
  }
}
