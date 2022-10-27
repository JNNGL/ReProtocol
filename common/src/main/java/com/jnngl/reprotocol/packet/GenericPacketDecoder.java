package com.jnngl.reprotocol.packet;

import com.jnngl.reprotocol.ConnectionData;
import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.remapper.PacketRemapper;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.IOException;
import java.util.List;

public class GenericPacketDecoder extends ByteToMessageDecoder {

  private final PacketRemapper packetRemapper;
  private final ConnectionData connectionData;

  public GenericPacketDecoder(PacketRemapper packetRemapper, ConnectionData connectionData) {
    this.packetRemapper = packetRemapper;
    this.connectionData = connectionData;
  }

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    int packetID = ProtocolUtils.readVarInt(in);
    Packet packet = packetRemapper
        .getInboundPacketRegistry()
        .getRegistry(connectionData.getVersion())
        .getStateRegistry(connectionData.getState())
        .getPacket(packetID);
    if (packet == null) {
      throw new IOException("Unknown packet: " + packetID +
          " (State: " + connectionData.getState() + ", version: " + connectionData.getVersion() + ")");
    }

    packet.decode(connectionData.getVersion(), in);
    out.add(packet);
  }

  public PacketRemapper getPacketRemapper() {
    return packetRemapper;
  }
}
