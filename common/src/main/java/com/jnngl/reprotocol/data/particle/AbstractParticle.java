package com.jnngl.reprotocol.data.particle;

import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public abstract class AbstractParticle {

  public abstract void encode(ByteBuf buf, MinecraftVersion version);

  public abstract void decode(ByteBuf buf, MinecraftVersion version);
}
