package com.jnngl.reprotocol;

import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public interface Packet {

  void encode(MinecraftVersion version, ByteBuf buf);
  void decode(MinecraftVersion version, ByteBuf buf);
}
