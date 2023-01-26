package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.data.BlockPos;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class OptPositionMetadataItem extends EntityMetadataItem<BlockPos> {

  private BlockPos value;

  public OptPositionMetadataItem(BlockPos value) {
    this.value = value;
  }

  public OptPositionMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    if (value != null) {
      buf.writeBoolean(true);
      BlockPos.write(buf, value);
    } else {
      buf.writeBoolean(false);
    }
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = buf.readBoolean() ? BlockPos.read(buf) : null;
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
    return "OptPositionMetadataItem{" +
        "value=" + value +
        '}';
  }
}
