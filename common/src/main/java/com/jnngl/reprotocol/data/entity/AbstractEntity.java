package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public abstract class AbstractEntity {

  private int id;

  public abstract void createMetadata(EntityMetadata metadata, MinecraftVersion version);

  public abstract void loadMetadata(EntityMetadata metadata, MinecraftVersion version);

  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }
}
