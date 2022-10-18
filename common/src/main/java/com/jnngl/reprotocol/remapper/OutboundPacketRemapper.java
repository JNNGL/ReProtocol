package com.jnngl.reprotocol.remapper;

public interface OutboundPacketRemapper {

  RemappedPacket remap(Object packet);
}
