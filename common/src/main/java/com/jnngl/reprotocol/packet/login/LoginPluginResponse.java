package com.jnngl.reprotocol.packet.login;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class LoginPluginResponse implements Packet {

  private int messageID;
  private ByteBuf data;

  public LoginPluginResponse(int messageID, ByteBuf data) {
    this.messageID = messageID;
    this.data = data;
  }

  public LoginPluginResponse() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeVarInt(buf, messageID);
    if (data != null) {
      buf.writeBoolean(true);
      buf.writeBytes(data);
    } else {
      buf.writeBoolean(false);
    }
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    messageID = ProtocolUtils.readVarInt(buf);
    if (buf.readBoolean()) {
      if (buf.readableBytes() > 0) {
        data = Unpooled.buffer().writeBytes(buf);
      } else {
        data = null;
      }
    } else {
      data = null;
    }
  }

  public int getMessageID() {
    return messageID;
  }

  public ByteBuf getData() {
    return data;
  }

  @Override
  public String toString() {
    return "LoginPluginResponse{" +
        "messageID=" + messageID +
        ", data=" + data +
        '}';
  }
}
