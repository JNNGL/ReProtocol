package com.jnngl.reprotocol.data.nbt;

import io.netty.buffer.ByteBuf;

public class TagInt extends NbtTag {

  private int value;

  public TagInt(String name, int value) {
    super(name);
    this.value = value;
  }

  public TagInt(String name) {
    super(name);
  }

  @Override
  public int getTypeID() {
    return 3;
  }

  @Override
  public void encode(ByteBuf buf) {
    buf.writeInt(value);
  }

  @Override
  public void decode(ByteBuf buf) {
    value = buf.readInt();
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Int('" + getName() + "') -> " + value;
  }
}
