package com.jnngl.reprotocol.data.recipe;

import com.jnngl.reprotocol.data.ItemStack;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class SmithingRecipe extends Recipe {

  private Ingredient base;
  private Ingredient addition;
  private ItemStack result;

  public SmithingRecipe(String type, String recipeID, Ingredient base, Ingredient addition, ItemStack result) {
    super(type, recipeID);
    this.base = base;
    this.addition = addition;
    this.result = result;
  }

  public SmithingRecipe(String type) {
    super(type);
  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    super.encode(version, buf);
    Ingredient.write(buf, base);
    Ingredient.write(buf, addition);
    ItemStack.write(buf, result);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    super.decode(version, buf);
    base = Ingredient.read(buf);
    addition = Ingredient.read(buf);
    result = ItemStack.read(buf);
  }

  public Ingredient getBase() {
    return base;
  }

  public Ingredient getAddition() {
    return addition;
  }

  public ItemStack getResult() {
    return result;
  }

  @Override
  public String toString() {
    return "SmithingRecipe{" + super.toString() +
        ", base=" + base +
        ", addition=" + addition +
        ", result=" + result +
        '}';
  }
}
