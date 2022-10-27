package com.jnngl.reprotocol.packet.handshake;

import com.jnngl.reprotocol.ConnectionState;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class Handshake implements Packet {

  private MinecraftVersion version;
  private String ip;
  private int port;
  private ConnectionState nextState;

  public Handshake(MinecraftVersion version, String ip, int port, ConnectionState nextState) {
    this.version = version;
    this.ip = ip;
    this.port = port;
    this.nextState = nextState;
  }

  public Handshake() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeVarInt(buf, this.version.getProtocolVersion());
    ProtocolUtils.writeString(buf, ip);
    buf.writeShort(port);
    ProtocolUtils.writeVarInt(buf, nextState.ordinal());
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    this.version = MinecraftVersion.fromPVN(ProtocolUtils.readVarInt(buf));
    this.ip = ProtocolUtils.readString(buf);
    this.port = buf.readShort();
    this.nextState = ConnectionState.values()[ProtocolUtils.readVarInt(buf)];
  }

  public MinecraftVersion getVersion() {
    return version;
  }

  public String getIP() {
    return ip;
  }

  public int getPort() {
    return port;
  }

  public ConnectionState getNextState() {
    return nextState;
  }

  @Override
  public String toString() {
    return "Handshake{" +
        "version=" + version +
        ", ip='" + ip + '\'' +
        ", port=" + port +
        ", nextState=" + nextState +
        '}';
  }
}
