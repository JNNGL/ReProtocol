package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.ItemStack;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.ItemStackMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Item extends Entity {

  private ItemStack itemStack = ItemStack.EMPTY;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(ItemStack.EMPTY, new ItemStackMetadataItem(itemStack));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    itemStack = metadata.read(ItemStack.EMPTY);
  }

  public ItemStack getItemStack() {
    return itemStack;
  }

  public void setItemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
  }
}
