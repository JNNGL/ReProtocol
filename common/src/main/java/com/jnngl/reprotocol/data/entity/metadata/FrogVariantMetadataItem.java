package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.data.FrogVariant;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class FrogVariantMetadataItem extends EntityMetadataItem<FrogVariant> {

  private FrogVariant value;

  public FrogVariantMetadataItem(FrogVariant value) {
    this.value = value;
  }

  public FrogVariantMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    ProtocolUtils.writeVarInt(buf, FrogVariant.REGISTRY.getRegistry(version).getID(value));
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = FrogVariant.REGISTRY.getRegistry(version).get(ProtocolUtils.readVarInt(buf));
  }

  @Override
  public FrogVariant getValue() {
    return value;
  }

  @Override
  public void setValue(FrogVariant value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "FrogVariantMetadataItem{" +
        "value=" + value +
        '}';
  }
}
