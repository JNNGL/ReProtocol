package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class Disconnect implements Packet {

  private String reason;

  public Disconnect(String reason) {
    this.reason = reason;
  }

  public Disconnect() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeString(buf, reason);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    reason = ProtocolUtils.readString(buf);
  }

  public String getReason() {
    return reason;
  }

  @Override
  public String toString() {
    return "Disconnect{" +
        "reason='" + reason + '\'' +
        '}';
  }
}
