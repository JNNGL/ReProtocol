package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.data.CatVariant;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class CatVariantMetadataItem extends EntityMetadataItem<CatVariant> {

  private CatVariant value;

  public CatVariantMetadataItem(CatVariant value) {
    this.value = value;
  }

  public CatVariantMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    ProtocolUtils.writeVarInt(buf, CatVariant.REGISTRY.getRegistry(version).getID(value));
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = CatVariant.REGISTRY.getRegistry(version).get(ProtocolUtils.readVarInt(buf));
  }

  @Override
  public CatVariant getValue() {
    return value;
  }

  @Override
  public void setValue(CatVariant value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "CatVariantMetadataItem{" +
        "value=" + value +
        '}';
  }
}
