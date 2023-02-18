package com.jnngl.reprotocol.listener;

import com.jnngl.reprotocol.ConnectionData;
import com.jnngl.reprotocol.ConnectionState;
import com.jnngl.reprotocol.packet.handshake.Handshake;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HandshakeListener extends ChannelInboundHandlerAdapter {

  private final ConnectionData connectionData;

  public HandshakeListener(ConnectionData connectionData) {
    this.connectionData = connectionData;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (msg instanceof Handshake handshake) {
      connectionData.setVersion(handshake.getVersion());
      connectionData.setState(handshake.getNextState());
      ctx.pipeline().remove(this);

      if (connectionData.getState() == ConnectionState.LOGIN) {
        // TODO: Use server version instead
        ctx.pipeline().addBefore("outbound_remapper", "entity_handler", new EntityHandler(MinecraftVersion.MINECRAFT_1_19_1));
        ctx.pipeline().addBefore("outbound_remapper", "login_listener", new LoginListener(connectionData));
      }
    }

    super.channelRead(ctx, msg);
  }

  public ConnectionData getConnectionData() {
    return connectionData;
  }
}
