package com.jnngl.reprotocol.packet.status;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class StatusRequest implements Packet {

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {

  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {

  }

  @Override
  public String toString() {
    return "StatusRequest{}";
  }
}
