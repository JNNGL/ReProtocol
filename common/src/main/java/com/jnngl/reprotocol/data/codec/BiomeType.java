package com.jnngl.reprotocol.data.codec;

import com.jnngl.reprotocol.data.nbt.NbtTag;
import com.jnngl.reprotocol.data.nbt.TagCompound;
import com.jnngl.reprotocol.util.MinecraftVersion;

import java.util.Collections;

public class BiomeType {

  public static class Music {

    private final boolean replaceCurrentMusic;
    private final String sound;
    private final int maxDelay;
    private final int minDelay;

    public Music(boolean replaceCurrentMusic, String sound, int maxDelay, int minDelay) {
      this.replaceCurrentMusic = replaceCurrentMusic;
      this.sound = sound;
      this.maxDelay = maxDelay;
      this.minDelay = minDelay;
    }

    public boolean isReplaceCurrentMusic() {
      return replaceCurrentMusic;
    }

    public String getSound() {
      return sound;
    }

    public int getMaxDelay() {
      return maxDelay;
    }

    public int getMinDelay() {
      return minDelay;
    }

    @Override
    public String toString() {
      return "Music{" +
          "replaceCurrentMusic=" + replaceCurrentMusic +
          ", sound='" + sound + '\'' +
          ", maxDelay=" + maxDelay +
          ", minDelay=" + minDelay +
          '}';
    }
  }

  public static class AdditionsSound {

    private final String sound;
    private final double tickChance;

    public AdditionsSound(String sound, double tickChance) {
      this.sound = sound;
      this.tickChance = tickChance;
    }

    public String getSound() {
      return sound;
    }

    public double getTickChance() {
      return tickChance;
    }

    @Override
    public String toString() {
      return "AdditionsSound{" +
          "sound='" + sound + '\'' +
          ", tickChance=" + tickChance +
          '}';
    }
  }

  public static class MoodSound {

    private final String sound;
    private final int tickDelay;
    private final double offset;
    private final int blockSearchExtent;

    public MoodSound(String sound, int tickDelay, double offset, int blockSearchExtent) {
      this.sound = sound;
      this.tickDelay = tickDelay;
      this.offset = offset;
      this.blockSearchExtent = blockSearchExtent;
    }

    public String getSound() {
      return sound;
    }

    public int getTickDelay() {
      return tickDelay;
    }

    public double getOffset() {
      return offset;
    }

    public int getBlockSearchExtent() {
      return blockSearchExtent;
    }

    @Override
    public String toString() {
      return "MoodSound{" +
          "sound='" + sound + '\'' +
          ", tickDelay=" + tickDelay +
          ", offset=" + offset +
          ", blockSearchExtent=" + blockSearchExtent +
          '}';
    }
  }

  public static class Particle {

    private final float probability;
    private final String type;

    public Particle(float probability, String type) {
      this.probability = probability;
      this.type = type;
    }

    public float getProbability() {
      return probability;
    }

    public String getType() {
      return type;
    }

    @Override
    public String toString() {
      return "Particle{" +
          "probability=" + probability +
          ", type='" + type + '\'' +
          '}';
    }
  }

  private final String key;
  private final int id;
  private final String precipitation;
  private final Float depth;
  private final float temperature;
  private final Float scale;
  private final float downfall;
  private final String category;
  private final String temperatureModifier;
  private final int skyColor;
  private final int waterFogColor;
  private final int fogColor;
  private final int waterColor;
  private final Integer foliageColor;
  private final Integer grassColor;
  private final String grassColorModifier;
  private final Music music;
  private final String ambientSound;
  private final AdditionsSound additionsSound;
  private final MoodSound moodSound;
  private final Particle particle;

  public BiomeType(String key, int id, String precipitation, Float depth, float temperature, Float scale, float downfall,
                   String category, String temperatureModifier, int skyColor, int waterFogColor, int fogColor,
                   int waterColor, Integer foliageColor, Integer grassColor, String grassColorModifier, Music music,
                   String ambientSound, AdditionsSound additionsSound, MoodSound moodSound, Particle particle) {
    this.key = key;
    this.id = id;
    this.precipitation = precipitation;
    this.depth = depth;
    this.temperature = temperature;
    this.scale = scale;
    this.downfall = downfall;
    this.category = category;
    this.temperatureModifier = temperatureModifier;
    this.skyColor = skyColor;
    this.waterFogColor = waterFogColor;
    this.fogColor = fogColor;
    this.waterColor = waterColor;
    this.foliageColor = foliageColor;
    this.grassColor = grassColor;
    this.grassColorModifier = grassColorModifier;
    this.music = music;
    this.ambientSound = ambientSound;
    this.additionsSound = additionsSound;
    this.moodSound = moodSound;
    this.particle = particle;
  }

  public String getKey() {
    return key;
  }

  public int getId() {
    return id;
  }

  public String getPrecipitation() {
    return precipitation;
  }

  public Float getDepth() {
    return depth;
  }

  public float getTemperature() {
    return temperature;
  }

  public Float getScale() {
    return scale;
  }

  public float getDownfall() {
    return downfall;
  }

  public String getCategory() {
    return category;
  }

  public String getTemperatureModifier() {
    return temperatureModifier;
  }

  public int getSkyColor() {
    return skyColor;
  }

  public int getWaterFogColor() {
    return waterFogColor;
  }

  public int getFogColor() {
    return fogColor;
  }

  public int getWaterColor() {
    return waterColor;
  }

  public Integer getFoliageColor() {
    return foliageColor;
  }

  public Integer getGrassColor() {
    return grassColor;
  }

  public String getGrassColorModifier() {
    return grassColorModifier;
  }

  public Music getMusic() {
    return music;
  }

  public String getAmbientSound() {
    return ambientSound;
  }

  public AdditionsSound getAdditionsSound() {
    return additionsSound;
  }

  public MoodSound getMoodSound() {
    return moodSound;
  }

  public Particle getParticle() {
    return particle;
  }

  @Override
  public String toString() {
    return "BiomeType{" +
        "key='" + key + '\'' +
        ", id=" + id +
        ", precipitation='" + precipitation + '\'' +
        ", depth=" + depth +
        ", temperature=" + temperature +
        ", scale=" + scale +
        ", downfall=" + downfall +
        ", category='" + category + '\'' +
        ", temperatureModifier='" + temperatureModifier + '\'' +
        ", skyColor=" + skyColor +
        ", waterFogColor=" + waterFogColor +
        ", fogColor=" + fogColor +
        ", waterColor=" + waterColor +
        ", foliageColor=" + foliageColor +
        ", grassColor=" + grassColor +
        ", grassColorModifier='" + grassColorModifier + '\'' +
        ", music=" + music +
        ", ambientSound='" + ambientSound + '\'' +
        ", additionsSound=" + additionsSound +
        ", moodSound=" + moodSound +
        ", particle=" + particle +
        '}';
  }

  public NbtTag toNBT(MinecraftVersion version) {
    TagCompound effectsTag = NbtTag.emptyCompound("effects");
    effectsTag.set(NbtTag.of("sky_color", skyColor));
    effectsTag.set(NbtTag.of("water_fog_color", waterFogColor));
    effectsTag.set(NbtTag.of("fog_color", fogColor));
    effectsTag.set(NbtTag.of("water_color", waterColor));

    if (foliageColor != null) {
      effectsTag.set(NbtTag.of("foliage_color", foliageColor));
    }

    if (grassColor != null) {
      effectsTag.set(NbtTag.of("grass_color", grassColor));
    }

    if (grassColorModifier != null) {
      effectsTag.set(NbtTag.of("grass_color_modifier", grassColorModifier));
    }

    if (music != null) {
      TagCompound musicTag = NbtTag.emptyCompound("music");
      musicTag.set(NbtTag.of("replace_current_music", music.isReplaceCurrentMusic()));
      musicTag.set(NbtTag.of("sound", music.getSound()));
      musicTag.set(NbtTag.of("max_delay", music.getMaxDelay()));
      musicTag.set(NbtTag.of("min_delay", music.getMinDelay()));
      effectsTag.set(musicTag);
    }

    if (ambientSound != null) {
      effectsTag.set(NbtTag.of("ambient_sound", ambientSound));
    }

    if (additionsSound != null) {
      TagCompound additionsSoundTag = NbtTag.emptyCompound("additions_sound");
      additionsSoundTag.set(NbtTag.of("sound", additionsSound.getSound()));
      additionsSoundTag.set(NbtTag.of("tick_chance", additionsSound.getTickChance()));
      effectsTag.set(additionsSoundTag);
    }

    if (moodSound != null) {
      TagCompound moodSoundTag = NbtTag.emptyCompound("mood_sound");
      moodSoundTag.set(NbtTag.of("sound", moodSound.getSound()));
      moodSoundTag.set(NbtTag.of("tick_delay", moodSound.getTickDelay()));
      moodSoundTag.set(NbtTag.of("offset", moodSound.getOffset()));
      moodSoundTag.set(NbtTag.of("block_search_extent", moodSound.getBlockSearchExtent()));
      effectsTag.set(moodSoundTag);
    }

    if (particle != null) {
      TagCompound particleTag = NbtTag.emptyCompound("particle");
      particleTag.set(NbtTag.of("probability", particle.getProbability()));
      particleTag.set(NbtTag.of("options", Collections.singleton(NbtTag.of("type", particle.getType()))));
      effectsTag.set(particleTag);
    }

    TagCompound elementTag = NbtTag.emptyCompound("element");
    elementTag.set(NbtTag.of("precipitation", precipitation));
    if (depth != null) {
      elementTag.set(NbtTag.of("depth", depth));
    }

    elementTag.set(NbtTag.of("temperature", temperature));
    if (scale != null) {
      elementTag.set(NbtTag.of("scale", scale));
    }

    elementTag.set(NbtTag.of("downfall", downfall));
    if (category != null) {
      elementTag.set(NbtTag.of("category", category));
    }

    if (temperatureModifier != null) {
      elementTag.set(NbtTag.of("temperature_modifier", temperatureModifier));
    }

    elementTag.set(effectsTag);

    TagCompound rootTag = NbtTag.emptyCompound("");
    rootTag.set(NbtTag.of("name", key));
    rootTag.set(NbtTag.of("id", id));
    rootTag.set(elementTag);
    return rootTag;
  }

  public static BiomeType fromNBT(NbtTag nbtTag, MinecraftVersion version) {
    // TODO: BiomeType#fromNBT
    return null;
  }
}
