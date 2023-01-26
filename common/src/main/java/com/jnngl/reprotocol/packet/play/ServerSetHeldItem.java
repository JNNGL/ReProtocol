package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class ServerSetHeldItem implements Packet {

  private byte slot;

  public ServerSetHeldItem(byte slot) {
    this.slot = slot;
  }

  public ServerSetHeldItem() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    buf.writeByte(slot);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    slot = buf.readByte();
  }

  public byte getSlot() {
    return slot;
  }

  @Override
  public String toString() {
    return "ServerSetHeldItem{" +
        "slot=" + slot +
        '}';
  }
}
