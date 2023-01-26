package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.data.GlobalBlockPos;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class GlobalPosMetadataItem extends EntityMetadataItem<GlobalBlockPos> {

  private GlobalBlockPos value;

  public GlobalPosMetadataItem(GlobalBlockPos value) {
    this.value = value;
  }

  public GlobalPosMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    GlobalBlockPos.write(buf, value);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = GlobalBlockPos.read(buf);
  }

  @Override
  public GlobalBlockPos getValue() {
    return value;
  }

  @Override
  public void setValue(GlobalBlockPos value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "GlobalPosMetadataItem{" +
        "value=" + value +
        '}';
  }
}
