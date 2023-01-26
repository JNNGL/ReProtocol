package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class BlockMetadataItem extends EntityMetadataItem<Integer> {

  private int value; // TODO: Block mappings

  public BlockMetadataItem(int value) {
    this.value = value;
  }

  public BlockMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    ProtocolUtils.writeVarInt(buf, value);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = ProtocolUtils.readVarInt(buf);
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
    return "BlockMetadataItem{" +
        "value=" + value +
        '}';
  }
}
