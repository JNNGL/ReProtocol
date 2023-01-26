package com.jnngl.reprotocol.data.codec;

import com.jnngl.reprotocol.data.nbt.NbtTag;
import com.jnngl.reprotocol.data.nbt.TagCompound;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class DimensionType {

  private final String key;
  private final int id;
  private final boolean piglinSafe;
  private final boolean hasRaids;
  private final Object monsterSpawnLightLevel; // Integer or TagCompound
  private final int monsterSpawnBlockLightLimit;
  private final boolean natural;
  private final float ambientLight;
  private final Long fixedTime;
  private final String infiniburn;
  private final boolean respawnAnchorWorks;
  private final boolean hasSkylight;
  private final boolean bedWorks;
  private final String effects;
  private final int minY;
  private final int height;
  private final int logicalHeight;
  private final double coordinateScale;
  private final boolean ultrawarm;
  private final boolean hasCeiling;

  public DimensionType(String key, int id, boolean piglinSafe, boolean hasRaids, Object monsterSpawnLightLevel,
                       int monsterSpawnBlockLightLimit, boolean natural, float ambientLight, Long fixedTime,
                       String infiniburn, boolean respawnAnchorWorks, boolean hasSkylight, boolean bedWorks,
                       String effects, int minY, int height, int logicalHeight, double coordinateScale,
                       boolean ultrawarm, boolean hasCeiling) {
    this.key = key;
    this.id = id;
    this.piglinSafe = piglinSafe;
    this.hasRaids = hasRaids;
    this.monsterSpawnLightLevel = monsterSpawnLightLevel;
    this.monsterSpawnBlockLightLimit = monsterSpawnBlockLightLimit;
    this.natural = natural;
    this.ambientLight = ambientLight;
    this.fixedTime = fixedTime;
    this.infiniburn = infiniburn.startsWith("#") ? infiniburn.substring(1) : infiniburn;
    this.respawnAnchorWorks = respawnAnchorWorks;
    this.hasSkylight = hasSkylight;
    this.bedWorks = bedWorks;
    this.effects = effects;
    this.minY = minY;
    this.height = height;
    this.logicalHeight = logicalHeight;
    this.coordinateScale = coordinateScale;
    this.ultrawarm = ultrawarm;
    this.hasCeiling = hasCeiling;
  }

  public String getKey() {
    return key;
  }

  public int getId() {
    return id;
  }

  public boolean isPiglinSafe() {
    return piglinSafe;
  }

  public boolean isHasRaids() {
    return hasRaids;
  }

  public Object getMonsterSpawnLightLevel() {
    return monsterSpawnLightLevel;
  }

  public int getMonsterSpawnBlockLightLimit() {
    return monsterSpawnBlockLightLimit;
  }

  public boolean isNatural() {
    return natural;
  }

  public float getAmbientLight() {
    return ambientLight;
  }

  public Long getFixedTime() {
    return fixedTime;
  }

  public String getInfiniburn() {
    return infiniburn;
  }

  public boolean isRespawnAnchorWorks() {
    return respawnAnchorWorks;
  }

  public boolean isHasSkylight() {
    return hasSkylight;
  }

  public boolean isBedWorks() {
    return bedWorks;
  }

  public String getEffects() {
    return effects;
  }

  public int getMinY() {
    return minY;
  }

  public int getHeight() {
    return height;
  }

  public int getLogicalHeight() {
    return logicalHeight;
  }

  public double getCoordinateScale() {
    return coordinateScale;
  }

  public boolean isUltrawarm() {
    return ultrawarm;
  }

  public boolean isHasCeiling() {
    return hasCeiling;
  }

  @Override
  public String toString() {
    return "DimensionType{" +
        "key='" + key + '\'' +
        ", id=" + id +
        ", piglinSafe=" + piglinSafe +
        ", hasRaids=" + hasRaids +
        ", monsterSpawnLightLevel=" + monsterSpawnLightLevel +
        ", monsterSpawnBlockLightLimit=" + monsterSpawnBlockLightLimit +
        ", natural=" + natural +
        ", ambientLight=" + ambientLight +
        ", fixedTime=" + fixedTime +
        ", infiniburn='" + infiniburn + '\'' +
        ", respawnAnchorWorks=" + respawnAnchorWorks +
        ", hasSkylight=" + hasSkylight +
        ", bedWorks=" + bedWorks +
        ", effects='" + effects + '\'' +
        ", minY=" + minY +
        ", height=" + height +
        ", logicalHeight=" + logicalHeight +
        ", coordinateScale=" + coordinateScale +
        ", ultrawarm=" + ultrawarm +
        ", hasCeiling=" + hasCeiling +
        '}';
  }

  public NbtTag toNBT(MinecraftVersion version) {
    TagCompound elementTag = NbtTag.emptyCompound("element");
    elementTag.set(NbtTag.of("piglin_safe", piglinSafe));
    elementTag.set(NbtTag.of("has_raids", hasRaids));

    if (monsterSpawnLightLevel instanceof Integer) {
      elementTag.set(NbtTag.of("monster_spawn_light_level", (int) monsterSpawnLightLevel));
    } else if (monsterSpawnLightLevel instanceof TagCompound) {
      elementTag.set((TagCompound) monsterSpawnLightLevel);
    } else {
      throw new IllegalStateException("monster_spawn_light_level should be either Integer or TagCompound.");
    }

    elementTag.set(NbtTag.of("monster_spawn_block_light_limit", monsterSpawnBlockLightLimit));
    elementTag.set(NbtTag.of("natural", natural));
    elementTag.set(NbtTag.of("ambient_light", ambientLight));

    if (fixedTime != null) {
      elementTag.set(NbtTag.of("fixed_time", fixedTime));
    }

    elementTag.set(NbtTag.of("infiniburn", "#" + infiniburn));
    elementTag.set(NbtTag.of("respawn_anchor_works", respawnAnchorWorks));
    elementTag.set(NbtTag.of("has_skylight", hasSkylight));
    elementTag.set(NbtTag.of("bed_works", bedWorks));
    elementTag.set(NbtTag.of("effects", effects));
    elementTag.set(NbtTag.of("min_y", minY));
    elementTag.set(NbtTag.of("height", height));
    elementTag.set(NbtTag.of("logical_height", logicalHeight));
    elementTag.set(NbtTag.of("coordinate_scale", coordinateScale));
    elementTag.set(NbtTag.of("ultrawarm", ultrawarm));
    elementTag.set(NbtTag.of("has_ceiling", hasCeiling));

    TagCompound rootTag = NbtTag.emptyCompound("");
    rootTag.set(NbtTag.of("name", key));
    rootTag.set(NbtTag.of("id", id));
    rootTag.set(elementTag);
    return rootTag;
  }

  public static DimensionType fromNBT(NbtTag nbtTag, MinecraftVersion version) {
    // TODO: DimensionType#fromNBT
    return null;
  }
}
