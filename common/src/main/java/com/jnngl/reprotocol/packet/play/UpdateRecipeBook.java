package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.data.recipe.RecipeBookSettings;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateRecipeBook implements Packet {

  private int action;
  private RecipeBookSettings[] settings;
  private List<String> recipes;
  private List<String> initRecipes;

  public UpdateRecipeBook(int action, RecipeBookSettings[] settings, List<String> recipes, List<String> initRecipes) {
    this.action = action;
    this.settings = settings;
    this.recipes = recipes;
    this.initRecipes = initRecipes;
  }

  public UpdateRecipeBook() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeVarInt(buf, action);
    for (int i = 0; i < 4; i++) {
      if (i >= settings.length) {
        buf.writeBoolean(false);
        buf.writeBoolean(false);
      } else {
        buf.writeBoolean(settings[i].isOpen());
        buf.writeBoolean(settings[i].isFilterActive());
      }
    }

    ProtocolUtils.writeVarInt(buf, recipes.size());
    recipes.forEach(recipe -> ProtocolUtils.writeString(buf, recipe));

    if (action == 0) {
      ProtocolUtils.writeVarInt(buf, initRecipes.size());
      initRecipes.forEach(recipe -> ProtocolUtils.writeString(buf, recipe));
    }
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    action = ProtocolUtils.readVarInt(buf);
    settings = new RecipeBookSettings[4];
    for (int i = 0; i < 4; i++) {
      settings[i] = new RecipeBookSettings(
          buf.readBoolean(),
          buf.readBoolean()
      );
    }

    recipes = new ArrayList<>();
    for (int i = 0; i < ProtocolUtils.readVarInt(buf); i++) {
      recipes.add(ProtocolUtils.readString(buf));
    }

    if (action == 0) {
      initRecipes = new ArrayList<>();
      for (int i = 0; i < ProtocolUtils.readVarInt(buf); i++) {
        initRecipes.add(ProtocolUtils.readString(buf));
      }
    } else {
      initRecipes = null;
    }
  }

  public int getAction() {
    return action;
  }

  public RecipeBookSettings[] getSettings() {
    return settings;
  }

  public List<String> getRecipes() {
    return recipes;
  }

  public List<String> getInitRecipes() {
    return initRecipes;
  }

  @Override
  public String toString() {
    return "UpdateRecipeBook{" +
        "action=" + action +
        ", settings=" + Arrays.toString(settings) +
        ", recipes=" + recipes +
        ", initRecipes=" + initRecipes +
        '}';
  }
}
