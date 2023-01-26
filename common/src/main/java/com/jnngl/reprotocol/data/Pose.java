package com.jnngl.reprotocol.data;

import com.jnngl.reprotocol.data.registry.ValueRegistry;
import com.jnngl.reprotocol.data.registry.VersionRegistry;
import com.jnngl.reprotocol.util.MapBuilder;
import com.jnngl.reprotocol.util.MinecraftVersion;

public enum Pose {
  STANDING,
  FALL_FLYING,
  SLEEPING,
  SPIN_ATTACK,
  SNEAKING,
  LONG_JUMPING,
  DYING,
  CROAKING,
  USING_TONGUE,
  ROARING,
  SNIFFING,
  EMERGING,
  DIGGING;

  public static final VersionRegistry<Pose> REGISTRY =
      new VersionRegistry<Pose>()
          .add(
              MinecraftVersion.MINECRAFT_1_19_1,
              new ValueRegistry<>(
                  new MapBuilder<Integer, Pose>()
                      .put(0, STANDING)
                      .put(1, FALL_FLYING)
                      .put(2, SLEEPING)
                      .put(3, SPIN_ATTACK)
                      .put(4, SNEAKING)
                      .put(5, LONG_JUMPING)
                      .put(6, DYING)
                      .put(7, CROAKING)
                      .put(8, USING_TONGUE)
                      .put(9, ROARING)
                      .put(10, SNIFFING)
                      .put(11, EMERGING)
                      .put(12, DIGGING)
                      .getUnmodifiable()
              )
          )
          .build();
}
