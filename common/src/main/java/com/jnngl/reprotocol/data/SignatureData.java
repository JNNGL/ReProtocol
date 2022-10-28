package com.jnngl.reprotocol.data;

import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.time.Instant;

public class SignatureData {

  private final Instant timestamp;
  private final byte[] publicKey;
  private final byte[] signature;

  public SignatureData(Instant timestamp, byte[] publicKey, byte[] signature) {
    this.timestamp = timestamp;
    this.publicKey = publicKey;
    this.signature = signature;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public byte[] getPublicKey() {
    return publicKey;
  }

  public byte[] getSignature() {
    return signature;
  }

  public static void write(ByteBuf buf, SignatureData signatureData) {
    ProtocolUtils.writeInstant(buf, signatureData.getTimestamp());
    ProtocolUtils.writePrefixedBytes(buf, signatureData.getPublicKey());
    ProtocolUtils.writePrefixedBytes(buf, signatureData.getSignature());
  }

  public static SignatureData read(ByteBuf buf) {
    return new SignatureData(
        ProtocolUtils.readInstant(buf),
        ProtocolUtils.readPrefixedBytes(buf),
        ProtocolUtils.readPrefixedBytes(buf)
    );
  }
}
