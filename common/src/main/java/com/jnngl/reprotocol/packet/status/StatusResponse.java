package com.jnngl.reprotocol.packet.status;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class StatusResponse implements Packet {

  private String response;

  public StatusResponse(String response) {
    this.response = response;
  }

  public StatusResponse() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeString(buf, response);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    response = ProtocolUtils.readString(buf);
  }

  @Override
  public String toString() {
    return "StatusResponse{" +
        "response='" + response + '\'' +
        '}';
  }
}
