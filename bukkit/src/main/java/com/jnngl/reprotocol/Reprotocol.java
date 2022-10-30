package com.jnngl.reprotocol;

import com.jnngl.reprotocol.adapter.VersionAdapter;
import com.jnngl.reprotocol.adapter.v1_19_2R1.Adapter1_19_2R1;
import com.jnngl.reprotocol.inject.Injector;
import com.jnngl.reprotocol.listener.HandshakeListener;
import com.jnngl.reprotocol.packet.GenericPacketDecoder;
import com.jnngl.reprotocol.packet.GenericPacketEncoder;
import com.jnngl.reprotocol.packet.GenericPacketRegistry;
import com.jnngl.reprotocol.packet.handshake.Handshake;
import com.jnngl.reprotocol.packet.login.LoginSuccess;
import com.jnngl.reprotocol.remapper.PacketRemapper;
import com.jnngl.reprotocol.remapper.handler.InboundRemapHandler;
import com.jnngl.reprotocol.remapper.handler.OutboundRemapHandler;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.bukkit.plugin.java.JavaPlugin;

public class Reprotocol extends JavaPlugin {

  private final Injector injector = new Injector();

  @Override
  public void onEnable() {
    VersionAdapter versionAdapter = new Adapter1_19_2R1();
    PacketRemapper packetRemapper = versionAdapter.createRemapper(GenericPacketRegistry.getServerboundRegistry(), GenericPacketRegistry.getClientboundRegistry());

    injector.addInjector(channel -> {
      ConnectionData connectionData = new ConnectionData();

      channel.pipeline().replace("decoder", "decoder", new GenericPacketDecoder(packetRemapper, connectionData));
      channel.pipeline().addAfter("decoder", "inbound_remapper", new InboundRemapHandler(packetRemapper));
      channel.pipeline().replace("encoder", "encoder", new GenericPacketEncoder(packetRemapper, connectionData));
      channel.pipeline().addAfter("encoder", "outbound_remapper", new OutboundRemapHandler(packetRemapper));

      channel.pipeline().addBefore("inbound_remapper", "handshake_listener", new HandshakeListener(connectionData));

      channel.pipeline().addBefore("packet_handler", "test", new ChannelDuplexHandler() {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
          getLogger().info("[IN] " + msg);
          super.channelRead(ctx, msg);
        }

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
          getLogger().info("[OUT] " + msg);
          super.write(ctx, msg, promise);
        }
      });
    });

    injector.inject();
  }
}
