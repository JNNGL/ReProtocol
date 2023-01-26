package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class SystemChat implements Packet {

  private String content;
  private boolean overlay;

  public SystemChat(String content, boolean overlay) {
    this.content = content;
    this.overlay = overlay;
  }

  public SystemChat() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeString(buf, content);
    buf.writeBoolean(overlay);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    content = ProtocolUtils.readString(buf);
    overlay = buf.readBoolean();
  }

  public String getContent() {
    return content;
  }

  public boolean isOverlay() {
    return overlay;
  }

  @Override
  public String toString() {
    return "SystemChat{" +
        "content='" + content + '\'' +
        ", overlay=" + overlay +
        '}';
  }
}
