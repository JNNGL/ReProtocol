package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class SetEntityVelocity implements Packet {

  private int entityID;
  private short velocityX;
  private short velocityY;
  private short velocityZ;

  public SetEntityVelocity(int entityID, short velocityX, short velocityY, short velocityZ) {
    this.entityID = entityID;
    this.velocityX = velocityX;
    this.velocityY = velocityY;
    this.velocityZ = velocityZ;
  }

  public SetEntityVelocity() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeVarInt(buf, entityID);
    buf.writeShort(velocityX);
    buf.writeShort(velocityY);
    buf.writeShort(velocityZ);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    entityID = ProtocolUtils.readVarInt(buf);
    velocityX = buf.readShort();
    velocityY = buf.readShort();
    velocityZ = buf.readShort();
  }

  public int getEntityID() {
    return entityID;
  }

  public void setEntityID(int entityID) {
    this.entityID = entityID;
  }

  public short getVelocityX() {
    return velocityX;
  }

  public void setVelocityX(short velocityX) {
    this.velocityX = velocityX;
  }

  public short getVelocityY() {
    return velocityY;
  }

  public void setVelocityY(short velocityY) {
    this.velocityY = velocityY;
  }

  public short getVelocityZ() {
    return velocityZ;
  }

  public void setVelocityZ(short velocityZ) {
    this.velocityZ = velocityZ;
  }

  @Override
  public String toString() {
    return "SetEntityVelocity{" +
        "entityID=" + entityID +
        ", velocityX=" + velocityX +
        ", velocityY=" + velocityY +
        ", velocityZ=" + velocityZ +
        '}';
  }
}
