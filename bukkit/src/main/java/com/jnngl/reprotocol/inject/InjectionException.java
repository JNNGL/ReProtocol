package com.jnngl.reprotocol.inject;

public class InjectionException extends RuntimeException {

  public InjectionException(String message) {
    super(message);
  }

  public InjectionException(Throwable cause) {
    super(cause);
  }
}
