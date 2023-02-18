package com.jnngl.reprotocol.listener;

import com.jnngl.reprotocol.data.entity.AbstractEntity;
import com.jnngl.reprotocol.data.entity.EntityType;
import com.jnngl.reprotocol.packet.play.SetEntityMetadata;
import com.jnngl.reprotocol.packet.play.SpawnEntity;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import java.util.HashMap;
import java.util.Map;

public class EntityHandler extends ChannelOutboundHandlerAdapter {

  public static final AttributeKey<Map<Integer, AbstractEntity>> ATTRIBUTE_KEY = AttributeKey.newInstance("entities");

  private final MinecraftVersion version;

  public EntityHandler(MinecraftVersion version) {
    this.version = version;
  }

  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
    if (msg instanceof SpawnEntity) {
      Attribute<Map<Integer, AbstractEntity>> attribute = ctx.channel().attr(ATTRIBUTE_KEY);
      Map<Integer, AbstractEntity> entityMap = attribute.get();
      if (entityMap == null) {
        entityMap = new HashMap<>();
        attribute.set(entityMap);
      }

      SpawnEntity packet = (SpawnEntity) msg;
      AbstractEntity entity = EntityType.REGISTRY.getRegistry(version).get(packet.getType());
      entityMap.put(packet.getEntityID(), entity);
    } else if (msg instanceof SetEntityMetadata) {
      Attribute<Map<Integer, AbstractEntity>> attribute = ctx.channel().attr(ATTRIBUTE_KEY);
      Map<Integer, AbstractEntity> entityMap = attribute.get();
      if (entityMap != null) {
        SetEntityMetadata packet = (SetEntityMetadata) msg;
        AbstractEntity entity = entityMap.get(packet.getID());
        if (packet.getEntity() == null) {
          packet.setEntity(entity);
        }

        if (entity != null) {
          entity.loadMetadata(packet.getMetadata(), version);
        }
      }
    }

    super.write(ctx, msg, promise);
  }
}
