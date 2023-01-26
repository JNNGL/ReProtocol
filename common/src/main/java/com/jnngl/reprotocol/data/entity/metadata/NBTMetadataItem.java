package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.data.nbt.Nbt;
import com.jnngl.reprotocol.data.nbt.NbtTag;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class NBTMetadataItem extends EntityMetadataItem<NbtTag> {

  private NbtTag value;

  public NBTMetadataItem(NbtTag value) {
    this.value = value;
  }

  public NBTMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    Nbt.write(buf, value);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = Nbt.read(buf);
  }

  @Override
  public NbtTag getValue() {
    return value;
  }

  @Override
  public void setValue(NbtTag value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "NBTMetadataItem{" +
        "value=" + value +
        '}';
  }
}
