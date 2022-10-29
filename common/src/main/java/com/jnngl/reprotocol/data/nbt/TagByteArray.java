package com.jnngl.reprotocol.data.nbt;

import io.netty.buffer.ByteBuf;

import java.util.Arrays;

public class TagByteArray extends NbtTag {

  private byte[] value;

  public TagByteArray(String name, byte[] value) {
    super(name);
    this.value = value;
  }

  public TagByteArray(String name) {
    super(name);
  }

  @Override
  public int getTypeID() {
    return 7;
  }

  @Override
  public void encode(ByteBuf buf) {
    buf.writeInt(value.length);
    buf.writeBytes(value);
  }

  @Override
  public void decode(ByteBuf buf) {
    value = new byte[buf.readInt()];
    buf.readBytes(value);
  }

  public byte[] getValue() {
    return value;
  }

  public void setValue(byte[] value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "ByteArray('" + getName() + "') -> " + Arrays.toString(value);
  }
}
