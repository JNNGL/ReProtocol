package com.jnngl.reprotocol.data;

import com.jnngl.reprotocol.data.registry.ValueRegistry;
import com.jnngl.reprotocol.data.registry.VersionRegistry;
import com.jnngl.reprotocol.util.MapBuilder;
import com.jnngl.reprotocol.util.MinecraftVersion;

public enum FrogVariant {
  TEMPERATE,
  WARM,
  COLD;

  public static final VersionRegistry<FrogVariant> REGISTRY =
      new VersionRegistry<FrogVariant>()
          .add(
              MinecraftVersion.MINECRAFT_1_19_1,
              new ValueRegistry<>(
                  new MapBuilder<Integer, FrogVariant>()
                      .put(0, TEMPERATE)
                      .put(1, WARM)
                      .put(2, COLD)
                      .getUnmodifiable()
              )
          )
          .build();

}
