package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class BooleanMetadataItem extends EntityMetadataItem<Boolean> {

  private boolean value;

  public BooleanMetadataItem(boolean value) {
    this.value = value;
  }

  public BooleanMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    buf.writeBoolean(value);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = buf.readBoolean();
  }

  @Override
  public Boolean getValue() {
    return value;
  }

  @Override
  public void setValue(Boolean value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "BooleanMetadataItem{" +
        "value=" + value +
        '}';
  }
}
