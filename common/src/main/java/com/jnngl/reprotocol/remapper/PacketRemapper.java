package com.jnngl.reprotocol.remapper;

import com.jnngl.reprotocol.Packet;

import java.util.Map;

public class PacketRemapper {

  private final Map<Class<? extends Packet>, InboundPacketRemapper> inboundRemapper;
  private final Map<Class<?>, OutboundPacketRemapper> outboundRemapper;

  public PacketRemapper(Map<Class<? extends Packet>, InboundPacketRemapper> inboundRemapper, Map<Class<?>, OutboundPacketRemapper> outboundRemapper) {
    this.inboundRemapper = inboundRemapper;
    this.outboundRemapper = outboundRemapper;
  }

  public Map<Class<? extends Packet>, InboundPacketRemapper> getInboundRemapper() {
    return inboundRemapper;
  }

  public Map<Class<?>, OutboundPacketRemapper> getOutboundRemapper() {
    return outboundRemapper;
  }
}
