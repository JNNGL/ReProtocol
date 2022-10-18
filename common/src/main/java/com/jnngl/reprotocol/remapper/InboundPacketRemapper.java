package com.jnngl.reprotocol.remapper;

public interface InboundPacketRemapper {

  Object remap(RemappedPacket packet);
}
