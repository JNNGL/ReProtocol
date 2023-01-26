package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class OptVarintMetadataItem extends EntityMetadataItem<Integer> {

  private Integer value;

  public OptVarintMetadataItem(Integer value) {
    this.value = value;
  }

  public OptVarintMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    if (value == null) {
      buf.writeByte(0);
    } else {
      ProtocolUtils.writeVarInt(buf, value + 1);
    }
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    int varint = ProtocolUtils.readVarInt(buf);
    if (varint == 0) {
      value = null;
    } else {
      value = varint - 1;
    }
  }

  @Override
  public Integer getValue() {
    return value;
  }

  @Override
  public void setValue(Integer value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "OptVarintMetadataItem{" +
        "value=" + value +
        '}';
  }
}
