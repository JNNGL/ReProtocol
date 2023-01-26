package com.jnngl.reprotocol.data.particle;

import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

class SimpleParticle extends AbstractParticle {

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {

  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {

  }

  @Override
  public boolean equals(Object obj) {
    return obj != null && getClass().equals(obj.getClass());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
