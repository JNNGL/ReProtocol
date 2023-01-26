package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.data.PaintingVariant;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class PaintingVariantMetadataItem extends EntityMetadataItem<PaintingVariant> {

  private PaintingVariant value;

  public PaintingVariantMetadataItem(PaintingVariant value) {
    this.value = value;
  }

  public PaintingVariantMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    ProtocolUtils.writeVarInt(buf, PaintingVariant.REGISTRY.getRegistry(version).getID(value));
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = PaintingVariant.REGISTRY.getRegistry(version).get(ProtocolUtils.readVarInt(buf));
  }

  @Override
  public PaintingVariant getValue() {
    return value;
  }

  @Override
  public void setValue(PaintingVariant value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "PaintingVariantMetadataItem{" +
        "value=" + value +
        '}';
  }
}
