package com.jnngl.reprotocol.data;

import com.jnngl.reprotocol.data.registry.ValueRegistry;
import com.jnngl.reprotocol.data.registry.VersionRegistry;
import com.jnngl.reprotocol.util.MapBuilder;
import com.jnngl.reprotocol.util.MinecraftVersion;

public enum VillagerType {
  DESERT,
  JUNGLE,
  PLAINS,
  SAVANNA,
  SNOW,
  SWAMP,
  TAIGA;

  public static final VersionRegistry<VillagerType> REGISTRY =
      new VersionRegistry<VillagerType>()
          .add(
              MinecraftVersion.MINECRAFT_1_19_1,
              new ValueRegistry<>(
                  new MapBuilder<Integer, VillagerType>()
                      .put(0, DESERT)
                      .put(1, JUNGLE)
                      .put(2, PLAINS)
                      .put(3, SAVANNA)
                      .put(4, SNOW)
                      .put(5, SWAMP)
                      .put(6, TAIGA)
                      .getUnmodifiable()
              )
          )
          .build();
}
