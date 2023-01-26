package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.BlockPos;
import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.FloatMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.OptPositionMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class LivingEntity extends Entity {

  private boolean handActive = false;
  private boolean offhand = false;
  private boolean inRiptideSpinAttack = false;
  private float health = 1.0F;
  private int potionEffectColor = 0;
  private boolean potionEffectAmbient = false;
  private int arrowsInEntity = 0;
  private int beeStingers = 0;
  private BlockPos bedLocation = null;

  private byte getHandStatus(MinecraftVersion version) {
    return (byte) ((handActive ? 0x01 : 0x00) |
        (offhand ? 0x02 : 0x00) |
        (inRiptideSpinAttack ? 0x04 : 0x00));
  }

  private void setHandStatus(MinecraftVersion version, byte handStatus) {
    handActive = (handStatus & 0x01) != 0;
    offhand = (handStatus & 0x02) != 0;
    inRiptideSpinAttack = (handStatus & 0x04) != 0;
  }

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write((byte) 0, new ByteMetadataItem(getHandStatus(version)));
    metadata.write(1.0F, new FloatMetadataItem(health));
    metadata.write(0, new VarintMetadataItem(potionEffectColor));
    metadata.write(false, new BooleanMetadataItem(potionEffectAmbient));
    metadata.write(0, new VarintMetadataItem(arrowsInEntity));
    metadata.write(0, new VarintMetadataItem(beeStingers));
    metadata.write(null, new OptPositionMetadataItem(bedLocation));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    setHandStatus(version, metadata.read((byte) 0));
    health = metadata.read(1.0F);
    potionEffectColor = metadata.read(0);
    potionEffectAmbient = metadata.read(false);
    arrowsInEntity = metadata.read(0);
    beeStingers = metadata.read(0);
    bedLocation = metadata.read(null);
  }

  public boolean isHandActive() {
    return handActive;
  }

  public void setHandActive(boolean handActive) {
    this.handActive = handActive;
  }

  public boolean isOffhand() {
    return offhand;
  }

  public void setOffhand(boolean offhand) {
    this.offhand = offhand;
  }

  public boolean isInRiptideSpinAttack() {
    return inRiptideSpinAttack;
  }

  public void setInRiptideSpinAttack(boolean inRiptideSpinAttack) {
    this.inRiptideSpinAttack = inRiptideSpinAttack;
  }

  public float getHealth() {
    return health;
  }

  public void setHealth(float health) {
    this.health = health;
  }

  public int getPotionEffectColor() {
    return potionEffectColor;
  }

  public void setPotionEffectColor(int potionEffectColor) {
    this.potionEffectColor = potionEffectColor;
  }

  public boolean isPotionEffectAmbient() {
    return potionEffectAmbient;
  }

  public void setPotionEffectAmbient(boolean potionEffectAmbient) {
    this.potionEffectAmbient = potionEffectAmbient;
  }

  public int getArrowsInEntity() {
    return arrowsInEntity;
  }

  public void setArrowsInEntity(int arrowsInEntity) {
    this.arrowsInEntity = arrowsInEntity;
  }

  public int getBeeStingers() {
    return beeStingers;
  }

  public void setBeeStingers(int beeStingers) {
    this.beeStingers = beeStingers;
  }

  public BlockPos getBedLocation() {
    return bedLocation;
  }

  public void setBedLocation(BlockPos bedLocation) {
    this.bedLocation = bedLocation;
  }
}
