package com.jnngl.reprotocol.data.particle;

import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class BlockParticle extends AbstractParticle {

  private int block; // TODO: Block class

  public BlockParticle(int block) {
    this.block = block;
  }

  public BlockParticle() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    ProtocolUtils.writeVarInt(buf, block);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    block = ProtocolUtils.readVarInt(buf);
  }

  public int getBlock() {
    return block;
  }

  public void setBlock(int block) {
    this.block = block;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BlockParticle that = (BlockParticle) o;

    return block == that.block;
  }

  @Override
  public int hashCode() {
    return block;
  }

  @Override
  public String toString() {
    return "BlockParticle{" +
        "block=" + block +
        '}';
  }
}
