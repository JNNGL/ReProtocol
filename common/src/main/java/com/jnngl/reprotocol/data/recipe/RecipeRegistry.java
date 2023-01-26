package com.jnngl.reprotocol.data.recipe;

import com.jnngl.reprotocol.util.MapBuilder;

import java.util.Map;
import java.util.function.Function;

public class RecipeRegistry {

  public static Map<String, Function<String, Recipe>> CONSTRUCTORS =
      new MapBuilder<String, Function<String, Recipe>>()
          .put("minecraft:crafting_shapeless", ShapelessRecipe::new)
          .put("minecraft:crafting_shaped", ShapedRecipe::new)
          .getUnmodifiable();

}
