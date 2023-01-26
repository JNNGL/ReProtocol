package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class ByteMetadataItem extends EntityMetadataItem<Byte> {

  private byte value;

  public ByteMetadataItem(byte value) {
    this.value = value;
  }

  public ByteMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    buf.writeByte(value);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = buf.readByte();
  }

  @Override
  public Byte getValue() {
    return value;
  }

  @Override
  public void setValue(Byte value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "ByteMetadataItem{" +
        "value=" + value +
        '}';
  }
}
