package com.jnngl.reprotocol.remapper;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.registry.VersionPacketRegistry;

import java.util.Map;

public class PacketRemapper {

  private final Map<Class<? extends Packet>, InboundPacketRemapper> inboundRemapper;
  private final Map<Class<?>, OutboundPacketRemapper> outboundRemapper;
  private final VersionPacketRegistry inboundPacketRegistry;
  private final VersionPacketRegistry outboundPacketRegistry;

  public PacketRemapper(Map<Class<? extends Packet>, InboundPacketRemapper> inboundRemapper,
                        Map<Class<?>, OutboundPacketRemapper> outboundRemapper,
                        VersionPacketRegistry inboundPacketRegistry,
                        VersionPacketRegistry outboundPacketRegistry) {
    this.inboundRemapper = inboundRemapper;
    this.outboundRemapper = outboundRemapper;
    this.inboundPacketRegistry = inboundPacketRegistry;
    this.outboundPacketRegistry = outboundPacketRegistry;
  }

  public Map<Class<? extends Packet>, InboundPacketRemapper> getInboundRemapper() {
    return inboundRemapper;
  }

  public Map<Class<?>, OutboundPacketRemapper> getOutboundRemapper() {
    return outboundRemapper;
  }

  public VersionPacketRegistry getInboundPacketRegistry() {
    return inboundPacketRegistry;
  }

  public VersionPacketRegistry getOutboundPacketRegistry() {
    return outboundPacketRegistry;
  }
}
