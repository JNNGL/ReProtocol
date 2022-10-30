package com.jnngl.reprotocol.data;

import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class GlobalBlockPos {

  private final String identifier;
  private final BlockPos blockPos;

  public GlobalBlockPos(String identifier, BlockPos blockPos) {
    this.identifier = identifier;
    this.blockPos = blockPos;
  }

  public String getIdentifier() {
    return identifier;
  }

  public BlockPos getBlockPos() {
    return blockPos;
  }

  public static void write(ByteBuf buf, GlobalBlockPos pos) {
    ProtocolUtils.writeString(buf, pos.getIdentifier());
    BlockPos.write(buf, pos.getBlockPos());
  }

  public static GlobalBlockPos read(ByteBuf buf) {
    return new GlobalBlockPos(
        ProtocolUtils.readString(buf),
        BlockPos.read(buf)
    );
  }
}
