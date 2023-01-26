package com.jnngl.reprotocol.util;

public interface ThrowingSupplier<T, E extends Throwable> {

  T get() throws E;
}
