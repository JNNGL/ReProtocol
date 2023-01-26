package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class SetRenderDistance implements Packet {

  private int viewDistance;

  public SetRenderDistance(int viewDistance) {
    this.viewDistance = viewDistance;
  }

  public SetRenderDistance() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeVarInt(buf, viewDistance);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    viewDistance = ProtocolUtils.readVarInt(buf);
  }

  public int getViewDistance() {
    return viewDistance;
  }

  @Override
  public String toString() {
    return "SetRenderDistance{" +
        "viewDistance=" + viewDistance +
        '}';
  }
}
