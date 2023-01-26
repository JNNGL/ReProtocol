package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class PluginMessage implements Packet {

  private String channel;
  private ByteBuf data;

  public PluginMessage(String channel, ByteBuf data) {
    this.channel = channel;
    this.data = data;
  }

  public PluginMessage() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeString(buf, channel);
    buf.writeBytes(data);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    channel = ProtocolUtils.readString(buf);
    data = Unpooled.buffer().writeBytes(buf);
  }

  public String getChannel() {
    return channel;
  }

  public ByteBuf getData() {
    return data;
  }

  @Override
  public String toString() {
    return "PluginMessage{" +
        "channel='" + channel + '\'' +
        ", data=" + data +
        '}';
  }
}
