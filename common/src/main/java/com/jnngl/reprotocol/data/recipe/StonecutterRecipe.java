package com.jnngl.reprotocol.data.recipe;

import com.jnngl.reprotocol.data.ItemStack;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class StonecutterRecipe extends Recipe {

  private String group;
  private Ingredient ingredient;
  private ItemStack result;

  public StonecutterRecipe(String type, String recipeID, String group, Ingredient ingredient, ItemStack result) {
    super(type, recipeID);
    this.group = group;
    this.ingredient = ingredient;
    this.result = result;
  }

  public StonecutterRecipe(String type) {
    super(type);
  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    super.encode(version, buf);
    ProtocolUtils.writeString(buf, group);
    Ingredient.write(buf, ingredient);
    ItemStack.write(buf, result);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    super.decode(version, buf);
    group = ProtocolUtils.readString(buf);
    ingredient = Ingredient.read(buf);
    result = ItemStack.read(buf);
  }

  public String getGroup() {
    return group;
  }

  public Ingredient getIngredient() {
    return ingredient;
  }

  public ItemStack getResult() {
    return result;
  }

  @Override
  public String toString() {
    return "StonecutterRecipe{" + super.toString() +
        ", group='" + group + '\'' +
        ", ingredient=" + ingredient +
        ", result=" + result +
        '}';
  }
}
