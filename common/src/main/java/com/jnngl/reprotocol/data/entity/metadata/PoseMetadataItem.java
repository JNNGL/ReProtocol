package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.data.Pose;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class PoseMetadataItem extends EntityMetadataItem<Pose> {

  private Pose value;

  public PoseMetadataItem(Pose value) {
    this.value = value;
  }

  public PoseMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    ProtocolUtils.writeVarInt(buf, Pose.REGISTRY.getRegistry(version).getID(value));
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = Pose.REGISTRY.getRegistry(version).get(ProtocolUtils.readVarInt(buf));
  }

  @Override
  public Pose getValue() {
    return value;
  }

  @Override
  public void setValue(Pose value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "PoseMetadataItem{" +
        "value=" + value +
        '}';
  }
}
