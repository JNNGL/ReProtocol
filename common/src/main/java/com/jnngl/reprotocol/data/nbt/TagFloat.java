package com.jnngl.reprotocol.data.nbt;

import io.netty.buffer.ByteBuf;

public class TagFloat extends NbtTag {

  private float value;

  public TagFloat(String name, float value) {
    super(name);
    this.value = value;
  }

  public TagFloat(String name) {
    super(name);
  }

  @Override
  public int getTypeID() {
    return 5;
  }

  @Override
  public void encode(ByteBuf buf) {
    buf.writeFloat(value);
  }

  @Override
  public void decode(ByteBuf buf) {
    value = buf.readFloat();
  }

  public float getValue() {
    return value;
  }

  public void setValue(float value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Float('" + getName() + "') -> " + value;
  }
}
