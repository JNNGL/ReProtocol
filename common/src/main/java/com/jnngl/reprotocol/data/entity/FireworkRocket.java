package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.ItemStack;
import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.ItemStackMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.OptVarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class FireworkRocket extends Entity {

  private ItemStack firework = null;
  private Integer elytraBoostingEntity = null;
  private boolean shotAtAngle = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(null, new ItemStackMetadataItem(firework));
    metadata.write(null, new OptVarintMetadataItem(elytraBoostingEntity));
    metadata.write(false, new BooleanMetadataItem(shotAtAngle));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    firework = metadata.read(null);
    elytraBoostingEntity = metadata.read(null);
    shotAtAngle = metadata.read(false);
  }

  public ItemStack getFirework() {
    return firework;
  }

  public void setFirework(ItemStack firework) {
    this.firework = firework;
  }

  public Integer getElytraBoostingEntity() {
    return elytraBoostingEntity;
  }

  public void setElytraBoostingEntity(Integer elytraBoostingEntity) {
    this.elytraBoostingEntity = elytraBoostingEntity;
  }

  public boolean isShotAtAngle() {
    return shotAtAngle;
  }

  public void setShotAtAngle(boolean shotAtAngle) {
    this.shotAtAngle = shotAtAngle;
  }
}
