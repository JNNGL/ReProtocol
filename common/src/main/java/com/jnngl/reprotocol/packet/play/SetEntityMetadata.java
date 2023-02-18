package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.data.entity.AbstractEntity;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class SetEntityMetadata implements Packet {

  private int id;
  private EntityMetadata metadata;
  private AbstractEntity entity;

  public SetEntityMetadata() {
  }

  public SetEntityMetadata(int id, EntityMetadata metadata) {
    this.id = id;
    this.metadata = metadata;
  }

  public SetEntityMetadata(AbstractEntity entity) {
    this.entity = entity;
    this.id = entity.getID();
  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeVarInt(buf, entity.getID());
    EntityMetadata metadata = new EntityMetadata();
    entity.createMetadata(metadata, version);
    metadata.encode(buf, version);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    id = ProtocolUtils.readVarInt(buf);
    metadata = new EntityMetadata();
    metadata.decode(version, buf);
  }

  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public AbstractEntity getEntity() {
    return entity;
  }

  public void setEntity(AbstractEntity entity) {
    this.entity = entity;
  }

  public EntityMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(EntityMetadata metadata) {
    this.metadata = metadata;
  }

  @Override
  public String toString() {
    return "SetEntityMetadata{" +
        "id=" + id +
        ", metadata=" + metadata +
        ", entity=" + entity +
        '}';
  }
}
