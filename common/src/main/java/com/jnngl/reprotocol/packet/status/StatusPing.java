package com.jnngl.reprotocol.packet.status;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class StatusPing implements Packet {

  private long payload;

  public StatusPing(long payload) {
    this.payload = payload;
  }

  public StatusPing() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    buf.writeLong(payload);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    payload = buf.readLong();
  }

  public long getPayload() {
    return payload;
  }

  @Override
  public String toString() {
    return "StatusPing{" +
        "payload=" + payload +
        '}';
  }
}
