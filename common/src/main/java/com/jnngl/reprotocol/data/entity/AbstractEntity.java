package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public abstract class AbstractEntity {

  public abstract void createMetadata(EntityMetadata metadata, MinecraftVersion version);

  public abstract void loadMetadata(EntityMetadata metadata, MinecraftVersion version);
}
