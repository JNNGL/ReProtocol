package com.jnngl.reprotocol.remapper;

import com.jnngl.reprotocol.Packet;

public interface OutboundPacketRemapper {

  Packet remap(Object packet);
}
