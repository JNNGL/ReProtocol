package com.jnngl.reprotocol.remapper.handler;

import com.jnngl.reprotocol.remapper.OutboundPacketRemapper;
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
    OutboundPacketRemapper remapper = packetRemapper.getOutboundRemapper().get(msg.getClass());
    if (remapper != null) {
      out.add(remapper.remap(msg));
    } else {
      throw new IllegalStateException("Couldn't find remapper for " + msg.getClass().getName());
    }
  }

  public PacketRemapper getPacketRemapper() {
    return packetRemapper;
  }
}
