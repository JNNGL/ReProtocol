package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.data.VillagerData;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class VillagerMetadataItem extends EntityMetadataItem<VillagerData> {

  private VillagerData value;

  public VillagerMetadataItem(VillagerData value) {
    this.value = value;
  }

  public VillagerMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    VillagerData.write(buf, version, value);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = VillagerData.read(buf, version);
  }

  @Override
  public VillagerData getValue() {
    return value;
  }

  @Override
  public void setValue(VillagerData value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "VillagerMetadataItem{" +
        "value=" + value +
        '}';
  }
}
