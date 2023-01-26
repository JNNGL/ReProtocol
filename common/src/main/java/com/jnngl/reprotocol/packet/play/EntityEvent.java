package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class EntityEvent implements Packet {

  private int entityID;
  private byte status;

  public EntityEvent(int entityID, byte status) {
    this.entityID = entityID;
    this.status = status;
  }

  public EntityEvent() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    buf.writeInt(entityID);
    buf.writeByte(status);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    entityID = buf.readInt();
    status = buf.readByte();
  }

  public int getEntityID() {
    return entityID;
  }

  public byte getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return "EntityEvent{" +
        "entityID=" + entityID +
        ", status=" + status +
        '}';
  }
}
