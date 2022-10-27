package com.jnngl.reprotocol.util;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class ProtocolUtils {

  private static final int SEGMENT_BITS = 0x7F;
  private static final int CONTINUE_BIT = 0x80;

  public static void writeString(ByteBuf buf, String string) {
    byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
    writeVarInt(buf, bytes.length);
    buf.writeBytes(bytes);
  }

  public static String readString(ByteBuf buf) {
    byte[] bytes = new byte[readVarInt(buf)];
    buf.readBytes(bytes);
    return new String(bytes, StandardCharsets.UTF_8);
  }

  public static int readVarInt(ByteBuf buf) {
    int value = 0;
    int position = 0;
    byte currentByte;

    while (true) {
      currentByte = buf.readByte();
      value |= (currentByte & SEGMENT_BITS) << position;

      if ((currentByte & CONTINUE_BIT) == 0) break;

      position += 7;

      if (position >= 32) throw new RuntimeException("VarInt is too big");
    }

    return value;
  }

  public static long readVarLong(ByteBuf buf) {
    long value = 0;
    int position = 0;
    byte currentByte;

    while (true) {
      currentByte = buf.readByte();
      value |= (long) (currentByte & SEGMENT_BITS) << position;

      if ((currentByte & CONTINUE_BIT) == 0) break;

      position += 7;

      if (position >= 64) throw new RuntimeException("VarLong is too big");
    }

    return value;
  }

  public static void writeVarInt(ByteBuf buf, int value) {
    while (true) {
      if ((value & ~SEGMENT_BITS) == 0) {
        buf.writeByte(value);
        return;
      }

      buf.writeByte((value & SEGMENT_BITS) | CONTINUE_BIT);
      value >>>= 7;
    }
  }

  public void writeVarLong(ByteBuf buf, long value) {
    while (true) {
      if ((value & ~((long) SEGMENT_BITS)) == 0) {
        buf.writeByte((int) value);
        return;
      }

      buf.writeByte((int) ((value & SEGMENT_BITS) | CONTINUE_BIT));
      value >>>= 7;
    }
  }
}
