package com.jnngl.reprotocol.data.registry;

import com.jnngl.reprotocol.util.MinecraftVersion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VersionRegistry<T> {

  private final List<Registry<T>> registries = new ArrayList<>();
  private Map<MinecraftVersion, Registry<T>> tempRegistries = new HashMap<>();

  public VersionRegistry<T> add(MinecraftVersion version, Registry<T> registry) {
    tempRegistries.put(version, registry);
    return this;
  }

  public VersionRegistry<T> build() {
    Registry<T> last = null;
    for (MinecraftVersion version : MinecraftVersion.values()) {
      registries.add(last = tempRegistries.getOrDefault(version, last));
    }

    tempRegistries = null;
    return this;
  }

  public Registry<T> getRegistry(MinecraftVersion version) {
    return registries.get(version.ordinal());
  }
}
