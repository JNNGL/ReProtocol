package com.jnngl.reprotocol.packet.login;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.Arrays;

public class EncryptionResponse implements Packet {

  private byte[] sharedSecret;
  private boolean hasVerifyToken;
  private byte[] verifyToken;
  private long salt;
  private byte[] messageSignature;

  public EncryptionResponse(byte[] sharedSecret, boolean hasVerifyToken, byte[] verifyToken, long salt, byte[] messageSignature) {
    this.sharedSecret = sharedSecret;
    this.hasVerifyToken = hasVerifyToken;
    this.verifyToken = verifyToken;
    this.salt = salt;
    this.messageSignature = messageSignature;
  }

  public EncryptionResponse(byte[] sharedSecret, byte[] verifyToken) {
    this(sharedSecret, true, verifyToken, 0, null);
  }

  public EncryptionResponse(byte[] sharedSecret, long salt, byte[] messageSignature) {
    this(sharedSecret, false, null, salt, messageSignature);
  }

  public EncryptionResponse() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writePrefixedBytes(buf, sharedSecret);
    buf.writeBoolean(hasVerifyToken);
    if (hasVerifyToken) {
      ProtocolUtils.writePrefixedBytes(buf, verifyToken);
    } else {
      buf.writeLong(salt);
      ProtocolUtils.writePrefixedBytes(buf, messageSignature);
    }
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    sharedSecret = ProtocolUtils.readPrefixedBytes(buf);
    if (hasVerifyToken = buf.readBoolean()) {
      verifyToken = ProtocolUtils.readPrefixedBytes(buf);
    } else {
      salt = buf.readLong();
      messageSignature = ProtocolUtils.readPrefixedBytes(buf);
    }
  }

  public byte[] getSharedSecret() {
    return sharedSecret;
  }

  public boolean isHasVerifyToken() {
    return hasVerifyToken;
  }

  public byte[] getVerifyToken() {
    return verifyToken;
  }

  public long getSalt() {
    return salt;
  }

  public byte[] getMessageSignature() {
    return messageSignature;
  }

  @Override
  public String toString() {
    return "EncryptionResponse{" +
        "sharedSecret=" + Arrays.toString(sharedSecret) +
        ", hasVerifyToken=" + hasVerifyToken +
        ", verifyToken=" + Arrays.toString(verifyToken) +
        ", salt=" + salt +
        ", messageSignature=" + Arrays.toString(messageSignature) +
        '}';
  }
}
