package com.jnngl.reprotocol.remapper;

import com.jnngl.reprotocol.Packet;

public interface InboundPacketRemapper {

  Object remap(Packet packet);
}
