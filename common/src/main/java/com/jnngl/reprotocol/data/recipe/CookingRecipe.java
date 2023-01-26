package com.jnngl.reprotocol.data.recipe;

import com.jnngl.reprotocol.data.ItemStack;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class CookingRecipe extends Recipe {

  private String group;
  private Ingredient ingredient;
  private ItemStack result;
  private float experience;
  private int cookingTime;

  public CookingRecipe(String type, String recipeID, String group, Ingredient ingredient, ItemStack result, float experience, int cookingTime) {
    super(type, recipeID);
    this.group = group;
    this.ingredient = ingredient;
    this.result = result;
    this.experience = experience;
    this.cookingTime = cookingTime;
  }

  public CookingRecipe(String type) {
    super(type);
  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    super.encode(version, buf);
    ProtocolUtils.writeString(buf, group);
    Ingredient.write(buf, ingredient);
    ItemStack.write(buf, result);
    buf.writeFloat(experience);
    ProtocolUtils.writeVarInt(buf, cookingTime);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    super.decode(version, buf);
    group = ProtocolUtils.readString(buf);
    ingredient = Ingredient.read(buf);
    result = ItemStack.read(buf);
    experience = buf.readFloat();
    cookingTime = ProtocolUtils.readVarInt(buf);
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

  public float getExperience() {
    return experience;
  }

  public int getCookingTime() {
    return cookingTime;
  }

  @Override
  public String toString() {
    return "CookingRecipe{" + super.toString() +
        ", group='" + group + '\'' +
        ", ingredient=" + ingredient +
        ", result=" + result +
        ", experience=" + experience +
        ", cookingTime=" + cookingTime +
        '}';
  }
}
