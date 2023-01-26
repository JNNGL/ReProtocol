package com.jnngl.reprotocol.data.particle;

import com.jnngl.reprotocol.data.ItemStack;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;
import java.util.Objects;

public class ItemParticle extends AbstractParticle {

  private ItemStack item;

  public ItemParticle(ItemStack item) {
    this.item = item;
  }

  public ItemParticle() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    ItemStack.write(buf, item);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    item = ItemStack.read(buf);
  }

  public ItemStack getItem() {
    return item;
  }

  public void setItem(ItemStack item) {
    this.item = item;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ItemParticle that = (ItemParticle) o;

    return Objects.equals(item, that.item);
  }

  @Override
  public int hashCode() {
    return item != null ? item.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "ItemParticle{" +
        "item=" + item +
        '}';
  }
}
