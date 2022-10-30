package com.jnngl.reprotocol.listener;

import com.jnngl.reprotocol.ConnectionData;
import com.jnngl.reprotocol.ConnectionState;
import com.jnngl.reprotocol.packet.login.LoginSuccess;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class LoginListener extends ChannelOutboundHandlerAdapter {

  private final ConnectionData connectionData;

  public LoginListener(ConnectionData connectionData) {
    this.connectionData = connectionData;
  }

  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
    super.write(ctx, msg, promise);

    if (msg instanceof LoginSuccess) {
      connectionData.setState(ConnectionState.PLAY);
      ctx.pipeline().remove(this);
    }
  }

  public ConnectionData getConnectionData() {
    return connectionData;
  }
}
