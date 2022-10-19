package com.jnngl.reprotocol.remapper;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.registry.PacketRegistry;

import java.util.Map;

public class PacketRemapper {

  private final Map<Class<? extends Packet>, InboundPacketRemapper> inboundRemapper;
  private final Map<Class<?>, OutboundPacketRemapper> outboundRemapper;
  private final PacketRegistry packetRegistry;

  public PacketRemapper(Map<Class<? extends Packet>, InboundPacketRemapper> inboundRemapper,
                        Map<Class<?>, OutboundPacketRemapper> outboundRemapper,
                        PacketRegistry packetRegistry) {
    this.inboundRemapper = inboundRemapper;
    this.outboundRemapper = outboundRemapper;
    this.packetRegistry = packetRegistry;
  }

  public Map<Class<? extends Packet>, InboundPacketRemapper> getInboundRemapper() {
    return inboundRemapper;
  }

  public Map<Class<?>, OutboundPacketRemapper> getOutboundRemapper() {
    return outboundRemapper;
  }

  public PacketRegistry getPacketRegistry() {
    return packetRegistry;
  }
}
