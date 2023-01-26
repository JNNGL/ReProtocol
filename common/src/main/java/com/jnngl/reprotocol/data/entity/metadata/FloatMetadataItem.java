package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class FloatMetadataItem extends EntityMetadataItem<Float> {

  private float value;

  public FloatMetadataItem(float value) {
    this.value = value;
  }

  public FloatMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    buf.writeFloat(value);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = buf.readFloat();
  }

  @Override
  public Float getValue() {
    return value;
  }

  @Override
  public void setValue(Float value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "FloatMetadataItem{" +
        "value=" + value +
        '}';
  }
}
