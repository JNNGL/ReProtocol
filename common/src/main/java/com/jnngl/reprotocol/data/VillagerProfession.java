package com.jnngl.reprotocol.data;

import com.jnngl.reprotocol.data.registry.ValueRegistry;
import com.jnngl.reprotocol.data.registry.VersionRegistry;
import com.jnngl.reprotocol.util.MapBuilder;
import com.jnngl.reprotocol.util.MinecraftVersion;

public enum VillagerProfession {
  NONE,
  ARMORER,
  BUTCHER,
  CARTOGRAPHER,
  CLERIC,
  FARMER,
  FISHERMAN,
  FLETCHER,
  LEATHERWORKER,
  LIBRARIAN,
  MASON,
  NITWIT,
  SHEPHERD,
  TOOLSMITH,
  WEAPONSMITH;

  public static final VersionRegistry<VillagerProfession> REGISTRY =
      new VersionRegistry<VillagerProfession>()
          .add(
              MinecraftVersion.MINECRAFT_1_19_1,
              new ValueRegistry<>(
                  new MapBuilder<Integer, VillagerProfession>()
                      .put(0, NONE)
                      .put(1, ARMORER)
                      .put(2, BUTCHER)
                      .put(3, CARTOGRAPHER)
                      .put(4, CLERIC)
                      .put(5, FARMER)
                      .put(6, FISHERMAN)
                      .put(7, FLETCHER)
                      .put(8, LEATHERWORKER)
                      .put(9, LIBRARIAN)
                      .put(10, MASON)
                      .put(11, NITWIT)
                      .put(12, SHEPHERD)
                      .put(13, TOOLSMITH)
                      .put(14, WEAPONSMITH)
                      .getUnmodifiable()
              )
          )
          .build();
}
