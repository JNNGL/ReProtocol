package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.ItemStack;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.ItemStackMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class ThrownEnderPearl extends Entity {

  private ItemStack item = null;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(null, new ItemStackMetadataItem(item));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    item = metadata.read(null);
  }

  public ItemStack getItem() {
    return item;
  }

  public void setItem(ItemStack item) {
    this.item = item;
  }
}
