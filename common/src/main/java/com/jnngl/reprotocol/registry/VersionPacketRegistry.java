package com.jnngl.reprotocol.registry;

import com.jnngl.reprotocol.util.MinecraftVersion;

import java.util.Map;

public class VersionPacketRegistry {

  private final Map<MinecraftVersion, PacketRegistry> registries;

  public VersionPacketRegistry(Map<MinecraftVersion, PacketRegistry> registries) {
    this.registries = registries;
  }

  public PacketRegistry getRegistry(MinecraftVersion version) {
    return registries.get(version);
  }

  public Map<MinecraftVersion, PacketRegistry> getRegistries() {
    return registries;
  }
}
