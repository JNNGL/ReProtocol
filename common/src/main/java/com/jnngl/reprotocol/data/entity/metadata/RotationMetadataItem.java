package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.data.Rotation;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class RotationMetadataItem extends EntityMetadataItem<Rotation> {

  private Rotation value;

  public RotationMetadataItem(Rotation value) {
    this.value = value;
  }

  public RotationMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    buf.writeFloat(value.getX());
    buf.writeFloat(value.getY());
    buf.writeFloat(value.getZ());
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = new Rotation(
        buf.readFloat(),
        buf.readFloat(),
        buf.readFloat()
    );
  }

  @Override
  public Rotation getValue() {
    return value;
  }

  @Override
  public void setValue(Rotation value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "RotationMetadataItem{" +
        "value=" + value +
        '}';
  }
}
