package com.jnngl.reprotocol.data.registry;

public interface Registry<T> {

  T get(int id);

  int getID(T t);
}
