package com.jnngl.reprotocol.util;

import java.util.Optional;

public class ExceptionUtil {

  public static <T> Optional<T> doQuiet(ThrowingSupplier<T, ?> supplier) {
    try {
      return Optional.of(supplier.get());
    } catch (Throwable e) {
      return Optional.empty();
    }
  }

  public static boolean doQuiet(ThrowingRunnable<?> runnable) {
    try {
      runnable.run();
      return true;
    } catch (Throwable e) {
      return false;
    }
  }
}
