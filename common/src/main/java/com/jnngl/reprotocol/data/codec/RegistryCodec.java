package com.jnngl.reprotocol.data.codec;

import com.jnngl.reprotocol.data.nbt.NbtTag;
import com.jnngl.reprotocol.data.nbt.TagCompound;
import com.jnngl.reprotocol.util.MinecraftVersion;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RegistryCodec {

  private final Map<String, DimensionType> dimensionRegistry;
  private final Map<String, BiomeType> biomeRegistry;
  private final Map<String, ChatType> chatRegistry;

  public RegistryCodec(Set<DimensionType> dimensions, Set<BiomeType> biomes, Set<ChatType> chatType) {
    dimensionRegistry = dimensions.stream().collect(Collectors.toMap(DimensionType::getKey, Function.identity()));
    biomeRegistry = biomes.stream().collect(Collectors.toMap(BiomeType::getKey, Function.identity()));
    chatRegistry = chatType.stream().collect(Collectors.toMap(ChatType::getKey, Function.identity()));
  }

  public Map<String, DimensionType> getDimensionRegistry() {
    return dimensionRegistry;
  }

  public Map<String, BiomeType> getBiomeRegistry() {
    return biomeRegistry;
  }

  public Map<String, ChatType> getChatRegistry() {
    return chatRegistry;
  }

  @Override
  public String toString() {
    return "RegistryCodec{" +
        "dimensionRegistry=" + dimensionRegistry +
        ", biomeRegistry=" + biomeRegistry +
        ", chatRegistry=" + chatRegistry +
        '}';
  }

  public NbtTag toNBT(MinecraftVersion version) {
    TagCompound dimensionTypeTag = NbtTag.emptyCompound("minecraft:dimension_type");
    dimensionTypeTag.set(NbtTag.of("type", "minecraft:dimension_type"));
    dimensionTypeTag.set(NbtTag.of("value",
        dimensionRegistry.values().stream()
            .map(dimension -> dimension.toNBT(version))
            .collect(Collectors.toList())
    ));

    TagCompound biomeTypeTag = NbtTag.emptyCompound("minecraft:worldgen/biome");
    biomeTypeTag.set(NbtTag.of("type", "minecraft:worldgen/biome"));
    biomeTypeTag.set(NbtTag.of("value",
        biomeRegistry.values().stream()
            .map(biome -> biome.toNBT(version))
            .collect(Collectors.toList())
    ));

    TagCompound chatTypeTag = NbtTag.emptyCompound("minecraft:chat_type");
    chatTypeTag.set(NbtTag.of("type", "minecraft:chat_type"));
    chatTypeTag.set(NbtTag.of("value",
        chatRegistry.values().stream()
            .map(chatType -> chatType.toNBT(version))
            .collect(Collectors.toList())
    ));

    TagCompound rootTag = NbtTag.emptyCompound("");
    rootTag.set(dimensionTypeTag);
    rootTag.set(biomeTypeTag);
    rootTag.set(chatTypeTag);
    return rootTag;
  }

  public static RegistryCodec fromNBT(NbtTag nbtTag, MinecraftVersion version) {
    // TODO: RegistryCodec#fromNBT
    return null;
  }
}
