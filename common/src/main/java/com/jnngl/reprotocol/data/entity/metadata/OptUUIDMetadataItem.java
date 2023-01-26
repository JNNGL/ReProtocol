package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.UUID;

public class OptUUIDMetadataItem extends EntityMetadataItem<UUID> {

  private UUID value;

  public OptUUIDMetadataItem(UUID value) {
    this.value = value;
  }

  public OptUUIDMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    if (value != null) {
      buf.writeBoolean(true);
      ProtocolUtils.writeUUID(buf, value);
    } else {
      buf.writeBoolean(false);
    }
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = buf.readBoolean() ? ProtocolUtils.readUUID(buf) : null;
  }

  @Override
  public UUID getValue() {
    return value;
  }

  @Override
  public void setValue(UUID value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "OptUUIDMetadataItem{" +
        "value=" + value +
        '}';
  }
}
