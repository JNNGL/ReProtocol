package com.jnngl.reprotocol;

import io.netty.buffer.ByteBuf;

public interface Packet {

  void encode(MinecraftVersion version, ByteBuf buf);
  void decode(MinecraftVersion version, ByteBuf buf);
}
