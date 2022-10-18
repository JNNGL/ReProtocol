package com.jnngl.reprotocol.inject;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPromise;

import java.util.List;

class ChannelInjectionHandler extends ChannelInboundHandlerAdapter {

  private final List<ChannelInjector> injectors;

  ChannelInjectionHandler(List<ChannelInjector> injectors) {
    this.injectors = injectors;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    Channel channel = (Channel) msg;

    channel.pipeline().addLast(new ChannelInitializer<Channel>() {

      @Override
      protected void initChannel(Channel channel) {
        channel.pipeline().addLast(new ChannelDuplexHandler() {

          @Override
          public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ctx.pipeline().remove(this);
            inject(ctx.channel());

            super.channelRead(ctx, msg);
          }

          @Override
          public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            ctx.pipeline().remove(this);
            inject(ctx.channel());

            super.write(ctx, msg, promise);
          }
        });
      }
    });

    super.channelRead(ctx, msg);
  }

  private void inject(Channel channel) {
    for (ChannelInjector injector : injectors) {
      injector.inject(channel);
    }
  }
}
