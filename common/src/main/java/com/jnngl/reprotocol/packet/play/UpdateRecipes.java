package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.data.recipe.Recipe;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

public class UpdateRecipes implements Packet {

  private List<Recipe> recipes;

  public UpdateRecipes(List<Recipe> recipes) {
    this.recipes = recipes;
  }

  public UpdateRecipes() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeVarInt(buf, recipes.size());
    recipes.forEach(recipe -> Recipe.write(buf, version, recipe));
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    recipes = new ArrayList<>();
    for (int i = 0; i < ProtocolUtils.readVarInt(buf); i++) {
      recipes.add(Recipe.read(buf, version));
    }
  }

  public List<Recipe> getRecipes() {
    return recipes;
  }

  @Override
  public String toString() {
    return "UpdateRecipes{" +
        "recipes=" + recipes +
        '}';
  }
}
