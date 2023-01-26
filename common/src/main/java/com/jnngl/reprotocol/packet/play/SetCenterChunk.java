package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class SetCenterChunk implements Packet {

  private int chunkX;
  private int chunkZ;

  public SetCenterChunk(int chunkX, int chunkZ) {
    this.chunkX = chunkX;
    this.chunkZ = chunkZ;
  }

  public SetCenterChunk() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeVarInt(buf, chunkX);
    ProtocolUtils.writeVarInt(buf, chunkZ);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    chunkX = ProtocolUtils.readVarInt(buf);
    chunkZ = ProtocolUtils.readVarInt(buf);
  }

  public int getChunkX() {
    return chunkX;
  }

  public int getChunkZ() {
    return chunkZ;
  }

  @Override
  public String toString() {
    return "SetCenterChunk{" +
        "chunkX=" + chunkX +
        ", chunkZ=" + chunkZ +
        '}';
  }
}
