package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.data.ItemStack;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class ItemStackMetadataItem extends EntityMetadataItem<ItemStack> {

  private ItemStack value;

  public ItemStackMetadataItem(ItemStack value) {
    this.value = value;
  }

  public ItemStackMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    ItemStack.write(buf, value);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = ItemStack.read(buf);
  }

  @Override
  public ItemStack getValue() {
    return value;
  }

  @Override
  public void setValue(ItemStack value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "ItemStackMetadataItem{" +
        "value=" + value +
        '}';
  }
}
