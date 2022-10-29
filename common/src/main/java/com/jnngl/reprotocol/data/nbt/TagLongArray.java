package com.jnngl.reprotocol.data.nbt;

import io.netty.buffer.ByteBuf;

import java.util.Arrays;
import java.util.stream.LongStream;

public class TagLongArray extends NbtTag {

  private long[] value;

  public TagLongArray(String name, long[] value) {
    super(name);
    this.value = value;
  }

  public TagLongArray(String name) {
    super(name);
  }

  @Override
  public int getTypeID() {
    return 12;
  }

  @Override
  public void encode(ByteBuf buf) {
    buf.writeInt(value.length);
    LongStream.of(value).forEach(buf::writeLong);
  }

  @Override
  public void decode(ByteBuf buf) {
    value = new long[buf.readInt()];
    for (int i = 0; i < value.length; i++) {
      value[i] = buf.readLong();
    }
  }

  public long[] getValue() {
    return value;
  }

  public void setValue(long[] value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "LongArray('" + getName() + "') -> " + Arrays.toString(value);
  }
}
