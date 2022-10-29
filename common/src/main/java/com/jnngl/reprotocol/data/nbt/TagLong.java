package com.jnngl.reprotocol.data.nbt;

import io.netty.buffer.ByteBuf;

public class TagLong extends NbtTag {

  private long value;

  public TagLong(String name, long value) {
    super(name);
    this.value = value;
  }

  public TagLong(String name) {
    super(name);
  }

  @Override
  public int getTypeID() {
    return 4;
  }

  @Override
  public void encode(ByteBuf buf) {
    buf.writeLong(value);
  }

  @Override
  public void decode(ByteBuf buf) {
    value = buf.readLong();
  }

  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Long('" + getName() + "') -> " + value;
  }
}
