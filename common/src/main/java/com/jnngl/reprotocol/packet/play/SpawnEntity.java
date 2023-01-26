package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class SpawnEntity implements Packet {

  private int entityID;
  private UUID entityUUID;
  private int type; // TODO: EntityType Registry
  private double x;
  private double y;
  private double z;
  private float pitch;
  private float yaw;
  private float headYaw;
  private int data;
  private double velocityX;
  private double velocityY;
  private double velocityZ;

  public SpawnEntity(int entityID, UUID entityUUID, int type, double x, double y, double z, float pitch, float yaw, float headYaw, int data,
      double velocityX, double velocityY, double velocityZ) {
    this.entityID = entityID;
    this.entityUUID = entityUUID;
    this.type = type;
    this.x = x;
    this.y = y;
    this.z = z;
    this.pitch = pitch;
    this.yaw = yaw;
    this.headYaw = headYaw;
    this.data = data;
    this.velocityX = velocityX;
    this.velocityY = velocityY;
    this.velocityZ = velocityZ;
  }

  public SpawnEntity() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeVarInt(buf, entityID);
    ProtocolUtils.writeUUID(buf, entityUUID);
    ProtocolUtils.writeVarInt(buf, type);
    buf.writeDouble(x);
    buf.writeDouble(y);
    buf.writeDouble(z);
    buf.writeByte((int) (pitch * 256.0F / 360.0F));
    buf.writeByte((int) (yaw * 256.0F / 360.0F));
    buf.writeByte((int) (headYaw * 256.0F / 360.0F));
    ProtocolUtils.writeVarInt(buf, data);
    buf.writeShort((int) (velocityX * 8000.0D));
    buf.writeShort((int) (velocityY * 8000.0D));
    buf.writeShort((int) (velocityZ * 8000.0D));
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    entityID = ProtocolUtils.readVarInt(buf);
    entityUUID = ProtocolUtils.readUUID(buf);
    type = ProtocolUtils.readVarInt(buf);
    x = buf.readDouble();
    y = buf.readDouble();
    z = buf.readDouble();
    pitch = buf.readByte();
    yaw = buf.readByte();
    headYaw = buf.readByte();
    data = ProtocolUtils.readVarInt(buf);
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

  public UUID getEntityUUID() {
    return entityUUID;
  }

  public void setEntityUUID(UUID entityUUID) {
    this.entityUUID = entityUUID;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getZ() {
    return z;
  }

  public void setZ(double z) {
    this.z = z;
  }

  public float getPitch() {
    return pitch;
  }

  public void setPitch(float pitch) {
    this.pitch = pitch;
  }

  public float getYaw() {
    return yaw;
  }

  public void setYaw(float yaw) {
    this.yaw = yaw;
  }

  public float getHeadYaw() {
    return headYaw;
  }

  public void setHeadYaw(float headYaw) {
    this.headYaw = headYaw;
  }

  public int getData() {
    return data;
  }

  public void setData(int data) {
    this.data = data;
  }

  public double getVelocityX() {
    return velocityX;
  }

  public void setVelocityX(double velocityX) {
    this.velocityX = velocityX;
  }

  public double getVelocityY() {
    return velocityY;
  }

  public void setVelocityY(double velocityY) {
    this.velocityY = velocityY;
  }

  public double getVelocityZ() {
    return velocityZ;
  }

  public void setVelocityZ(double velocityZ) {
    this.velocityZ = velocityZ;
  }

  @Override
  public String toString() {
    return "SpawnEntity{" +
        "entityID=" + entityID +
        ", entityUUID=" + entityUUID +
        ", type=" + type +
        ", x=" + x +
        ", y=" + y +
        ", z=" + z +
        ", pitch=" + pitch +
        ", yaw=" + yaw +
        ", headYaw=" + headYaw +
        ", data=" + data +
        ", velocityX=" + velocityX +
        ", velocityY=" + velocityY +
        ", velocityZ=" + velocityZ +
        '}';
  }
}
