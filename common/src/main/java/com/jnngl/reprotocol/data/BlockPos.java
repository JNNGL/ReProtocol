package com.jnngl.reprotocol.data;

import io.netty.buffer.ByteBuf;

public class BlockPos {

  private final int x;
  private final int y;
  private final int z;

  public BlockPos(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getZ() {
    return z;
  }

  public static void write(ByteBuf buf, BlockPos blockPos) {
    long position = 0L;
    position |= ((long) blockPos.getX() & 0x3FFFFFF) << 38;
    position |= ((long) blockPos.getZ() & 0x3FFFFFF) << 12;
    position |= ((long) blockPos.getY() & 0xFFF);
    buf.writeLong(position);
  }

  public static BlockPos read(ByteBuf buf) {
    long position = buf.readLong();
    int x = (int) (position >> 38);
    int y = (int) (position << 52 >> 52);
    int z = (int) (position << 26 >> 38);
    return new BlockPos(x, y, z);
  }
}
