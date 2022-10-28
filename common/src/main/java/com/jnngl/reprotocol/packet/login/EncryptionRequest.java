package com.jnngl.reprotocol.packet.login;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class EncryptionRequest implements Packet {

  private String serverID;
  private byte[] publicKey;
  private byte[] verifyToken;

  public EncryptionRequest(String serverID, byte[] publicKey, byte[] verifyToken) {
    this.serverID = serverID;
    this.publicKey = publicKey;
    this.verifyToken = verifyToken;
  }

  public EncryptionRequest() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeString(buf, serverID);
    ProtocolUtils.writePrefixedBytes(buf, publicKey);
    ProtocolUtils.writePrefixedBytes(buf, verifyToken);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    serverID = ProtocolUtils.readString(buf);
    publicKey = ProtocolUtils.readPrefixedBytes(buf);
    verifyToken = ProtocolUtils.readPrefixedBytes(buf);
  }

  public String getServerID() {
    return serverID;
  }

  public void setServerID(String serverID) {
    this.serverID = serverID;
  }

  public byte[] getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(byte[] publicKey) {
    this.publicKey = publicKey;
  }

  public byte[] getVerifyToken() {
    return verifyToken;
  }

  public void setVerifyToken(byte[] verifyToken) {
    this.verifyToken = verifyToken;
  }
}
