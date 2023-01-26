package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class OptChatMetadataItem extends EntityMetadataItem<String> {

  private String value;

  public OptChatMetadataItem(String value) {
    this.value = value;
  }

  public OptChatMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
      if (value != null) {
        buf.writeBoolean(true);
        ProtocolUtils.writeString(buf, value);
      } else {
        buf.writeBoolean(false);
      }
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = buf.readBoolean() ? ProtocolUtils.readString(buf) : null;
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
    return "OptChatMetadataItem{" +
        "value='" + value + '\'' +
        '}';
  }
}
