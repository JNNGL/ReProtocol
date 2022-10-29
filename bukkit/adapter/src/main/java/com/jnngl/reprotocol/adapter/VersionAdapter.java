package com.jnngl.reprotocol.adapter;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.packet.registry.VersionPacketRegistry;
import com.jnngl.reprotocol.remapper.InboundPacketRemapper;
import com.jnngl.reprotocol.remapper.OutboundPacketRemapper;
import com.jnngl.reprotocol.remapper.PacketRemapper;

import java.util.function.Function;

public interface VersionAdapter {

  @SuppressWarnings("unchecked")
  static <T extends Packet> InboundPacketRemapper wrapInbound(Function<T, ?> remapper) {
    return packet -> remapper.apply((T) packet);
  }

  @SuppressWarnings("unchecked")
  static <T> OutboundPacketRemapper wrapOutbound(Function<T, Packet> remapper) {
    return packet -> remapper.apply((T) packet);
  }

  PacketRemapper createRemapper(VersionPacketRegistry packetRegistry, VersionPacketRegistry outboundRegistry);
}
