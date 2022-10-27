package com.jnngl.reprotocol.remapper.handler;

import com.jnngl.reprotocol.remapper.PacketRemapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class OutboundRemapHandler extends MessageToMessageEncoder<Object> {

  private final PacketRemapper packetRemapper;

  public OutboundRemapHandler(PacketRemapper packetRemapper) {
    this.packetRemapper = packetRemapper;
  }

  @Override
  protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) {
    out.add(packetRemapper.getOutboundRemapper().get(msg.getClass()).remap(msg));
  }

  public PacketRemapper getPacketRemapper() {
    return packetRemapper;
  }
}
