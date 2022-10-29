package com.jnngl.reprotocol.data.nbt;

import io.netty.buffer.ByteBuf;

public class TagByte extends NbtTag {

  private byte value;

  public TagByte(String name, byte value) {
    super(name);
    this.value = value;
  }

  public TagByte(String name) {
    super(name);
  }

  @Override
  public int getTypeID() {
    return 1;
  }

  @Override
  public void encode(ByteBuf buf) {
    buf.writeByte(value);
  }

  @Override
  public void decode(ByteBuf buf) {
    value = buf.readByte();
  }

  public byte getValue() {
    return value;
  }

  public void setValue(byte value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Byte('" + getName() + "') -> " + value;
  }
}
