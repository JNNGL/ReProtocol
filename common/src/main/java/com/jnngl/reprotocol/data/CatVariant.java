package com.jnngl.reprotocol.data;

import com.jnngl.reprotocol.data.registry.ValueRegistry;
import com.jnngl.reprotocol.data.registry.VersionRegistry;
import com.jnngl.reprotocol.util.MapBuilder;
import com.jnngl.reprotocol.util.MinecraftVersion;

public enum CatVariant {
  TABBY,
  BLACK,
  RED,
  SIAMESE,
  BRITISH_SHORTHAIR,
  CALICO,
  PERSIAN,
  RAGDOLL,
  WHITE,
  JELLIE,
  ALL_BLACK;

  public static final VersionRegistry<CatVariant> REGISTRY =
      new VersionRegistry<CatVariant>()
          .add(
              MinecraftVersion.MINECRAFT_1_19_1,
              new ValueRegistry<>(
                  new MapBuilder<Integer, CatVariant>()
                      .put(0, TABBY)
                      .put(1, BLACK)
                      .put(2, RED)
                      .put(3, SIAMESE)
                      .put(4, BRITISH_SHORTHAIR)
                      .put(5, CALICO)
                      .put(6, PERSIAN)
                      .put(7, RAGDOLL)
                      .put(8, WHITE)
                      .put(9, JELLIE)
                      .put(10, ALL_BLACK)
                      .getUnmodifiable()
              )
          )
          .build();
}
