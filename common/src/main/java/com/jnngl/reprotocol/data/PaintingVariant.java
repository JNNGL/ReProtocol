package com.jnngl.reprotocol.data;

import com.jnngl.reprotocol.data.registry.ValueRegistry;
import com.jnngl.reprotocol.data.registry.VersionRegistry;
import com.jnngl.reprotocol.util.MapBuilder;
import com.jnngl.reprotocol.util.MinecraftVersion;

public enum PaintingVariant {
  KEBAB,
  AZTEC,
  ALBAN,
  AZTEC2,
  BOMB,
  PLANT,
  WASTELAND,
  POOL,
  COURBET,
  SEA,
  SUNSET,
  CREEBET,
  WANDERER,
  GRAHAM,
  MATCH,
  BUST,
  STAGE,
  VOID,
  SKULL_AND_ROSES,
  WITHER,
  FIGHTERS,
  POINTER,
  PIGSCENE,
  BURNING_SKULL,
  SKELETON,
  EARTH,
  WIND,
  WATER,
  FIRE,
  DONKEY_KONG;

  public static final VersionRegistry<PaintingVariant> REGISTRY =
      new VersionRegistry<PaintingVariant>()
          .add(
              MinecraftVersion.MINECRAFT_1_19_1,
              new ValueRegistry<>(
                  new MapBuilder<Integer, PaintingVariant>()
                      .put(0, KEBAB)
                      .put(1, AZTEC)
                      .put(2, ALBAN)
                      .put(3, AZTEC2)
                      .put(4, BOMB)
                      .put(5, PLANT)
                      .put(6, WASTELAND)
                      .put(7, POOL)
                      .put(8, COURBET)
                      .put(9, SEA)
                      .put(10, SUNSET)
                      .put(11, CREEBET)
                      .put(12, WANDERER)
                      .put(13, GRAHAM)
                      .put(14, MATCH)
                      .put(15, BUST)
                      .put(16, STAGE)
                      .put(17, VOID)
                      .put(18, SKULL_AND_ROSES)
                      .put(19, WITHER)
                      .put(20, FIGHTERS)
                      .put(21, POINTER)
                      .put(22, PIGSCENE)
                      .put(23, BURNING_SKULL)
                      .put(24, SKELETON)
                      .put(25, EARTH)
                      .put(26, WIND)
                      .put(27, WATER)
                      .put(28, FIRE)
                      .put(29, DONKEY_KONG)
                      .getUnmodifiable()
              )
          )
          .build();
}
