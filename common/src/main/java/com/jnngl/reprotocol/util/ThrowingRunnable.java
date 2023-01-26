package com.jnngl.reprotocol.util;

public interface ThrowingRunnable<E extends Throwable> {

  void run() throws E;
}
