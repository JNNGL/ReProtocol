package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class StringMetadataItem extends EntityMetadataItem<String> {

  private String value;

  public StringMetadataItem(String value) {
    this.value = value;
  }

  public StringMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    ProtocolUtils.writeString(buf, value);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = ProtocolUtils.readString(buf);
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "StringMetadataItem{" +
        "value='" + value + '\'' +
        '}';
  }
}
