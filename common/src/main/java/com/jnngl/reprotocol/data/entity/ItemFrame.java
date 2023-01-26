package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.ItemStack;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.ItemStackMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class ItemFrame extends Entity {

  private ItemStack item = null;
  private int rotation = 0;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(null, new ItemStackMetadataItem(item));
    metadata.write(0, new VarintMetadataItem(rotation));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    item = metadata.read(null);
    rotation = metadata.read(0);
  }

  public ItemStack getItem() {
    return item;
  }

  public void setItem(ItemStack item) {
    this.item = item;
  }

  public int getRotation() {
    return rotation;
  }

  public void setRotation(int rotation) {
    this.rotation = rotation;
  }
}
