package com.jnngl.reprotocol.data.nbt;

import io.netty.buffer.ByteBuf;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TagIntArray extends NbtTag {

  private int[] value;

  public TagIntArray(String name, int[] value) {
    super(name);
    this.value = value;
  }

  public TagIntArray(String name) {
    super(name);
  }

  @Override
  public int getTypeID() {
    return 11;
  }

  @Override
  public void encode(ByteBuf buf) {
    buf.writeInt(value.length);
    IntStream.of(value).forEach(buf::writeInt);
  }

  @Override
  public void decode(ByteBuf buf) {
    value = new int[buf.readInt()];
    for (int i = 0; i < value.length; i++) {
      value[i] = buf.readInt();
    }
  }

  public int[] getValue() {
    return value;
  }

  public void setValue(int[] value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "IntArray('" + getName() + "') -> " + Arrays.toString(value);
  }
}
