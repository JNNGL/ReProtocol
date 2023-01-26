package com.jnngl.reprotocol.data.recipe;

import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.function.Function;

public class Recipe {

  private final String type;
  private String recipeID;

  public Recipe(String type, String recipeID) {
    this.type = type;
    this.recipeID = recipeID;
  }

  public Recipe(String type) {
    this.type = type;
  }

  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeString(buf, recipeID);
  }

  public void decode(MinecraftVersion version, ByteBuf buf) {
    recipeID = ProtocolUtils.readString(buf);
  }

  public String getType() {
    return type;
  }

  public String getRecipeID() {
    return recipeID;
  }

  @Override
  public String toString() {
    return "Recipe{" +
        "type='" + type + '\'' +
        ", recipeID='" + recipeID + '\'' +
        '}';
  }

  public static void write(ByteBuf buf, MinecraftVersion version, Recipe recipe) {
    ProtocolUtils.writeString(buf, recipe.getType());
    recipe.encode(version, buf);
  }

  public static Recipe read(ByteBuf buf, MinecraftVersion version) {
    String type = ProtocolUtils.readString(buf);
    Function<String, Recipe> ctor = RecipeRegistry.CONSTRUCTORS.getOrDefault(type, Recipe::new);
    Recipe recipe = ctor.apply(type);
    recipe.decode(version, buf);
    return recipe;
  }
}
