package com.jnngl.reprotocol.data.recipe;

import com.jnngl.reprotocol.data.ItemStack;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

public class ShapelessRecipe extends Recipe {

  private String group;
  private List<Ingredient> ingredients;
  private ItemStack result;

  public ShapelessRecipe(String type, String recipeID, String group, List<Ingredient> ingredients, ItemStack result) {
    super(type, recipeID);
    this.group = group;
    this.ingredients = ingredients;
    this.result = result;
  }

  public ShapelessRecipe(String type) {
    super(type);
  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    super.encode(version, buf);
    ProtocolUtils.writeString(buf, group);
    ProtocolUtils.writeVarInt(buf, ingredients.size());
    ingredients.forEach(ingredient -> Ingredient.write(buf, ingredient));
    ItemStack.write(buf, result);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    super.decode(version, buf);
    group = ProtocolUtils.readString(buf);

    ingredients = new ArrayList<>();
    for (int i = 0; i < ProtocolUtils.readVarInt(buf); i++) {
      ingredients.add(Ingredient.read(buf));
    }

    result = ItemStack.read(buf);
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
    return "ShapelessRecipe{" + super.toString() +
        ", group='" + group + '\'' +
        ", ingredients=" + ingredients +
        ", result=" + result +
        '}';
  }
}
