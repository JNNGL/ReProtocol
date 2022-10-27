package com.jnngl.reprotocol.packet;

import com.jnngl.reprotocol.ConnectionData;
import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.remapper.PacketRemapper;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class GenericPacketEncoder extends MessageToByteEncoder<Packet> {

  private final PacketRemapper packetRemapper;
  private final ConnectionData connectionData;

  public GenericPacketEncoder(PacketRemapper packetRemapper, ConnectionData connectionData) {
    this.packetRemapper = packetRemapper;
    this.connectionData = connectionData;
  }

  @Override
  protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) {
    ProtocolUtils.writeVarInt(out, packetRemapper
        .getOutboundPacketRegistry()
        .getRegistry(connectionData.getVersion())
        .getStateRegistry(connectionData.getState())
        .getID(msg));
    msg.encode(connectionData.getVersion(), out);
  }

  public PacketRemapper getPacketRemapper() {
    return packetRemapper;
  }
}
