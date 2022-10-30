package com.jnngl.reprotocol.packet.login;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class SetCompression implements Packet {

  private int threshold;

  public SetCompression(int threshold) {
    this.threshold = threshold;
  }

  public SetCompression() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeVarInt(buf, threshold);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    threshold = ProtocolUtils.readVarInt(buf);
  }

  public int getThreshold() {
    return threshold;
  }

  @Override
  public String toString() {
    return "SetCompression{" +
        "threshold=" + threshold +
        '}';
  }
}
