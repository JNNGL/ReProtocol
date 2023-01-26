package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.data.BlockPos;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class PositionMetadataItem extends EntityMetadataItem<BlockPos> {

  private BlockPos value;

  public PositionMetadataItem(BlockPos value) {
    this.value = value;
  }

  public PositionMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    BlockPos.write(buf, value);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = BlockPos.read(buf);
  }

  @Override
  public BlockPos getValue() {
    return value;
  }

  @Override
  public void setValue(BlockPos value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "PositionMetadataItem{" +
        "value=" + value +
        '}';
  }
}
