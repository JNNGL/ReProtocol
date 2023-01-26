package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.data.Direction;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class DirectionMetadataItem extends EntityMetadataItem<Direction> {

  private Direction value;

  public DirectionMetadataItem(Direction value) {
    this.value = value;
  }

  public DirectionMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    ProtocolUtils.writeVarInt(buf, value.ordinal());
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = Direction.values()[ProtocolUtils.readVarInt(buf)];
  }

  @Override
  public Direction getValue() {
    return value;
  }

  @Override
  public void setValue(Direction value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "DirectionMetadataItem{" +
        "value=" + value +
        '}';
  }
}
