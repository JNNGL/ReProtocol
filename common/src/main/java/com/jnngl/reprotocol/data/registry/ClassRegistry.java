package com.jnngl.reprotocol.data.registry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ClassRegistry<T> implements Registry<T> {

  private final Map<Integer, Supplier<T>> suppliers;
  private final Map<Class<?>, Integer> classToID;

  public ClassRegistry(Map<Integer, Supplier<T>> suppliers) {
    this.suppliers = Collections.unmodifiableMap(suppliers);
    this.classToID = Collections.unmodifiableMap(
        suppliers.entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    entry -> entry.getValue().get().getClass(),
                    Map.Entry::getKey
                )
            )
    );
  }

  public ClassRegistry() {
    this(new HashMap<>());
  }

  @Override
  public T get(int id) {
    Supplier<T> supplier = suppliers.get(id);
    if (supplier != null) {
      return supplier.get();
    } else {
      return null;
    }
  }

  public int getID(Class<?> cls) {
    return classToID.getOrDefault(cls, 0);
  }

  @Override
  public int getID(T t) {
    return getID(t.getClass());
  }

  public Map<Integer, Supplier<T>> getSuppliers() {
    return suppliers;
  }
}
