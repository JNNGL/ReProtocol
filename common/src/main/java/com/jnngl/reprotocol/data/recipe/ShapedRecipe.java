package com.jnngl.reprotocol.data.recipe;

import com.jnngl.reprotocol.data.ItemStack;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

public class ShapedRecipe extends Recipe {

  private int width;
  private int height;
  private String group;
  private List<Ingredient> ingredients;
  private ItemStack result;

  public ShapedRecipe(String type, String recipeID, int width, int height, String group, List<Ingredient> ingredients, ItemStack result) {
    super(type, recipeID);
    this.width = width;
    this.height = height;
    this.group = group;
    this.ingredients = ingredients;
    this.result = result;
  }

  public ShapedRecipe(String type) {
    super(type);
  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    super.encode(version, buf);
    ProtocolUtils.writeVarInt(buf, width);
    ProtocolUtils.writeVarInt(buf, height);
    ProtocolUtils.writeString(buf, group);
    ingredients.forEach(ingredient -> Ingredient.write(buf, ingredient));
    ItemStack.write(buf, result);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    super.decode(version, buf);
    width = ProtocolUtils.readVarInt(buf);
    height = ProtocolUtils.readVarInt(buf);
    group = ProtocolUtils.readString(buf);

    ingredients = new ArrayList<>();
    for (int i = 0; i < ProtocolUtils.readVarInt(buf); i++) {
      ingredients.add(Ingredient.read(buf));
    }

    result = ItemStack.read(buf);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public String getGroup() {
    return group;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public ItemStack getResult() {
    return result;
  }

  @Override
  public String toString() {
    return "ShapedRecipe{" + super.toString() +
        ", width=" + width +
        ", height=" + height +
        ", group='" + group + '\'' +
        ", ingredients=" + ingredients +
        ", result=" + result +
        '}';
  }
}
