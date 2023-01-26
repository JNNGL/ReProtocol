package com.jnngl.reprotocol.remapper.handler;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.remapper.InboundPacketRemapper;
import com.jnngl.reprotocol.remapper.PacketRemapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class InboundRemapHandler extends MessageToMessageDecoder<Packet> {

  private final PacketRemapper packetRemapper;

  public InboundRemapHandler(PacketRemapper packetRemapper) {
    this.packetRemapper = packetRemapper;
  }

  @Override
  protected void decode(ChannelHandlerContext ctx, Packet msg, List<Object> out) {
    InboundPacketRemapper remapper = packetRemapper.getInboundRemapper().get(msg.getClass());
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
