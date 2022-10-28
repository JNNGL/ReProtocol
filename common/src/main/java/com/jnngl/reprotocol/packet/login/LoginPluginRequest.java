package com.jnngl.reprotocol.packet.login;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class LoginPluginRequest implements Packet {

  private int messageID;
  private String channel;
  private ByteBuf data;

  public LoginPluginRequest(int messageID, String channel, ByteBuf data) {
    this.messageID = messageID;
    this.channel = channel;
    this.data = data;
  }

  public LoginPluginRequest() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeVarInt(buf, messageID);
    ProtocolUtils.writeString(buf, channel);
    if (data != null) {
      buf.writeBytes(data);
    }
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    messageID = ProtocolUtils.readVarInt(buf);
    channel = ProtocolUtils.readString(buf);
    if (buf.readableBytes() > 0) {
      data = Unpooled.buffer().writeBytes(buf);
    } else {
      data = null;
    }
  }

  public int getMessageID() {
    return messageID;
  }

  public String getChannel() {
    return channel;
  }

  public ByteBuf getData() {
    return data;
  }
}
