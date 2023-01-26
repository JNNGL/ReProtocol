package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class SynchronizePlayerPosition implements Packet {

  private double x;
  private double y;
  private double z;
  private float yaw;
  private float pitch;
  private int flags;
  private int teleportID;
  private boolean dismountVehicle;

  public SynchronizePlayerPosition(double x, double y, double z, float yaw, float pitch, int flags, int teleportID, boolean dismountVehicle) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.yaw = yaw;
    this.pitch = pitch;
    this.flags = flags;
    this.teleportID = teleportID;
    this.dismountVehicle = dismountVehicle;
  }

  public SynchronizePlayerPosition() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    buf.writeDouble(x);
    buf.writeDouble(y);
    buf.writeDouble(z);
    buf.writeFloat(yaw);
    buf.writeFloat(pitch);
    buf.writeByte(flags);
    ProtocolUtils.writeVarInt(buf, teleportID);
    buf.writeBoolean(dismountVehicle);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    x = buf.readDouble();
    y = buf.readDouble();
    z = buf.readDouble();
    yaw = buf.readFloat();
    pitch = buf.readFloat();
    flags = buf.readByte();
    teleportID = ProtocolUtils.readVarInt(buf);
    dismountVehicle = buf.readBoolean();
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getZ() {
    return z;
  }

  public float getYaw() {
    return yaw;
  }

  public float getPitch() {
    return pitch;
  }

  public int getFlags() {
    return flags;
  }

  public int getTeleportID() {
    return teleportID;
  }

  public boolean isDismountVehicle() {
    return dismountVehicle;
  }

  @Override
  public String toString() {
    return "SynchronizePlayerPosition{" +
        "x=" + x +
        ", y=" + y +
        ", z=" + z +
        ", yaw=" + yaw +
        ", pitch=" + pitch +
        ", flags=" + flags +
        ", teleportID=" + teleportID +
        ", dismountVehicle=" + dismountVehicle +
        '}';
  }
}
