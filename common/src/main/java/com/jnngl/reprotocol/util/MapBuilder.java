package com.jnngl.reprotocol.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K, V> {

  private final Map<K, V> map = new HashMap<>();

  public MapBuilder<K, V> put(K key, V value) {
    map.put(key, value);
    return this;
  }

  public Map<K, V> get() {
    return map;
  }

  public Map<K, V> getUnmodifiable() {
    return Collections.unmodifiableMap(map);
  }
}
