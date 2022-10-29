package com.jnngl.reprotocol.data.nbt;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class TagString extends NbtTag {

  private String value;

  public TagString(String name, String value) {
    super(name);
    this.value = value;
  }

  public TagString(String name) {
    super(name);
  }

  @Override
  public int getTypeID() {
    return 8;
  }

  @Override
  public void encode(ByteBuf buf) {
    byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
    buf.writeShort(bytes.length);
    buf.writeBytes(bytes);
  }

  @Override
  public void decode(ByteBuf buf) {
    byte[] bytes = new byte[buf.readShort()];
    buf.readBytes(bytes);
    value = new String(bytes, StandardCharsets.UTF_8);
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "String('" + getName() + "') -> '" + value + "'";
  }
}
