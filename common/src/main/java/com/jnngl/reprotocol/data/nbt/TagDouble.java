package com.jnngl.reprotocol.data.nbt;

import io.netty.buffer.ByteBuf;

public class TagDouble extends NbtTag {

  private double value;

  public TagDouble(String name, double value) {
    super(name);
    this.value = value;
  }

  public TagDouble(String name) {
    super(name);
  }

  @Override
  public int getTypeID() {
    return 6;
  }

  @Override
  public void encode(ByteBuf buf) {
    buf.writeDouble(value);
  }

  @Override
  public void decode(ByteBuf buf) {
    value = buf.readDouble();
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Double('" + getName() + "') -> " + value;
  }
}
