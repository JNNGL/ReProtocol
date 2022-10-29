package com.jnngl.reprotocol.data.nbt;

import io.netty.buffer.ByteBuf;

public class TagShort extends NbtTag {

  private short value;

  public TagShort(String name, short value) {
    super(name);
    this.value = value;
  }

  public TagShort(String name) {
    super(name);
  }

  @Override
  public int getTypeID() {
    return 2;
  }

  @Override
  public void encode(ByteBuf buf) {
    buf.writeShort(value);
  }

  @Override
  public void decode(ByteBuf buf) {
    value = buf.readShort();
  }

  public short getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Short('" + getName() + "') -> " + value;
  }
}
