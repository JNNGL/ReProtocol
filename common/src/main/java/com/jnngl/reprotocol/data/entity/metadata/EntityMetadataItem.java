package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public abstract class EntityMetadataItem<T> {

  public abstract void encode(ByteBuf buf, MinecraftVersion version);

  public abstract void decode(ByteBuf buf, MinecraftVersion version);

  public abstract T getValue();

  public abstract void setValue(T value);
}
