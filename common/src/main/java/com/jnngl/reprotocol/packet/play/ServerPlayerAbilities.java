package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class ServerPlayerAbilities implements Packet {

  private byte flags;
  private float flyingSpeed;
  private float fovModifier;

  public ServerPlayerAbilities(byte flags, float flyingSpeed, float fovModifier) {
    this.flags = flags;
    this.flyingSpeed = flyingSpeed;
    this.fovModifier = fovModifier;
  }

  public ServerPlayerAbilities() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    buf.writeByte(flags);
    buf.writeFloat(flyingSpeed);
    buf.writeFloat(fovModifier);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    flags = buf.readByte();
    flyingSpeed = buf.readFloat();
    fovModifier = buf.readFloat();
  }

  public byte getFlags() {
    return flags;
  }

  public float getFlyingSpeed() {
    return flyingSpeed;
  }

  public float getFovModifier() {
    return fovModifier;
  }

  @Override
  public String toString() {
    return "ServerPlayerAbilities{" +
        "flags=" + flags +
        ", flyingSpeed=" + flyingSpeed +
        ", fovModifier=" + fovModifier +
        '}';
  }
}
