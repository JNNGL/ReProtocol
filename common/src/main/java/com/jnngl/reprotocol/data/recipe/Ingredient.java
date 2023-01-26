package com.jnngl.reprotocol.data.recipe;

import com.jnngl.reprotocol.data.ItemStack;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {

  private final List<ItemStack> items;

  public Ingredient(List<ItemStack> items) {
    this.items = items;
  }

  public List<ItemStack> getItems() {
    return items;
  }

  @Override
  public String toString() {
    return "Ingredient{" +
        "items=" + items +
        '}';
  }

  public static void write(ByteBuf buf, Ingredient ingredient) {
    ProtocolUtils.writeVarInt(buf, ingredient.getItems().size());
    ingredient.getItems().forEach(item -> ItemStack.write(buf, item));
  }

  public static Ingredient read(ByteBuf buf) {
    List<ItemStack> items = new ArrayList<>();
    for (int i = 0; i < ProtocolUtils.readVarInt(buf); i++) {
      items.add(ItemStack.read(buf));
    }

    return new Ingredient(items);
  }
}
