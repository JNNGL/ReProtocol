package com.jnngl.reprotocol.data.registry;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class ValueRegistry<T> implements Registry<T> {

  private final Map<Integer, T> idToT;
  private final Map<T, Integer> tToID;

  public ValueRegistry(Map<Integer, T> idToT) {
    this.idToT = Collections.unmodifiableMap(idToT);
    this.tToID = Collections.unmodifiableMap(
        idToT.entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getValue,
                    Map.Entry::getKey
                )
            )
    );
  }

  @Override
  public T get(int id) {
    return idToT.get(id);
  }

  @Override
  public int getID(T t) {
    return tToID.getOrDefault(t, -1);
  }
}
