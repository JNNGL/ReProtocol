package com.jnngl.reprotocol.packet.login;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class LoginDisconnect implements Packet {

  private String reason;

  public LoginDisconnect(String reason) {
    this.reason = reason;
  }

  public LoginDisconnect() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeString(buf, reason);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    reason = ProtocolUtils.readString(buf);
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  @Override
  public String toString() {
    return "LoginDisconnect{" +
        "reason='" + reason + '\'' +
        '}';
  }
}
