package com.jnngl.reprotocol.packet.login;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.data.SignatureData;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.UUID;

public class LoginStart implements Packet {

  private String name;
  private SignatureData signatureData;
  private UUID playerUUID;

  public LoginStart(String name, SignatureData signatureData, UUID playerUUID) {
    this.name = name;
    this.signatureData = signatureData;
    this.playerUUID = playerUUID;
  }

  public LoginStart() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeString(buf, name);

    if (signatureData != null) {
      buf.writeBoolean(true);
      SignatureData.write(buf, signatureData);
    } else {
      buf.writeBoolean(false);
    }

    if (playerUUID != null) {
      buf.writeBoolean(true);
      ProtocolUtils.writeUUID(buf, playerUUID);
    } else {
      buf.writeBoolean(false);
    }
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    name = ProtocolUtils.readString(buf);
    signatureData = buf.readBoolean() ? SignatureData.read(buf) : null;
    playerUUID = buf.readBoolean() ? ProtocolUtils.readUUID(buf) : null;
  }

  public String getName() {
    return name;
  }

  public SignatureData getSignatureData() {
    return signatureData;
  }

  public UUID getPlayerUUID() {
    return playerUUID;
  }
}
