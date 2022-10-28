package com.jnngl.reprotocol.adapter.v1_19_2R1;

import com.google.gson.Gson;
import com.jnngl.reprotocol.ConnectionState;
import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.adapter.VersionAdapter;
import com.jnngl.reprotocol.data.GameProfile;
import com.jnngl.reprotocol.data.SignatureData;
import com.jnngl.reprotocol.packet.handshake.Handshake;
import com.jnngl.reprotocol.packet.login.EncryptionRequest;
import com.jnngl.reprotocol.packet.login.EncryptionResponse;
import com.jnngl.reprotocol.packet.login.LoginDisconnect;
import com.jnngl.reprotocol.packet.login.LoginPluginRequest;
import com.jnngl.reprotocol.packet.login.LoginPluginResponse;
import com.jnngl.reprotocol.packet.login.LoginStart;
import com.jnngl.reprotocol.packet.login.LoginSuccess;
import com.jnngl.reprotocol.packet.login.SetCompression;
import com.jnngl.reprotocol.packet.status.StatusPing;
import com.jnngl.reprotocol.packet.status.StatusRequest;
import com.jnngl.reprotocol.packet.status.StatusResponse;
import com.jnngl.reprotocol.registry.VersionPacketRegistry;
import com.jnngl.reprotocol.remapper.InboundPacketRemapper;
import com.jnngl.reprotocol.remapper.OutboundPacketRemapper;
import com.jnngl.reprotocol.remapper.PacketRemapper;
import com.mojang.datafixers.util.Either;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.EnumProtocol;
import net.minecraft.network.PacketDataSerializer;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.handshake.PacketHandshakingInSetProtocol;
import net.minecraft.network.protocol.login.PacketLoginInCustomPayload;
import net.minecraft.network.protocol.login.PacketLoginInEncryptionBegin;
import net.minecraft.network.protocol.login.PacketLoginInStart;
import net.minecraft.network.protocol.login.PacketLoginOutCustomPayload;
import net.minecraft.network.protocol.login.PacketLoginOutDisconnect;
import net.minecraft.network.protocol.login.PacketLoginOutEncryptionBegin;
import net.minecraft.network.protocol.login.PacketLoginOutSetCompression;
import net.minecraft.network.protocol.login.PacketLoginOutSuccess;
import net.minecraft.network.protocol.status.PacketStatusInPing;
import net.minecraft.network.protocol.status.PacketStatusInStart;
import net.minecraft.network.protocol.status.PacketStatusOutPong;
import net.minecraft.network.protocol.status.PacketStatusOutServerInfo;
import net.minecraft.util.CryptographyException;
import net.minecraft.util.MinecraftEncryption;
import net.minecraft.world.entity.player.ProfilePublicKey;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Adapter1_19_2R1 implements VersionAdapter {

  private static final Unsafe unsafe;

  static {
    try {
      Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
      theUnsafe.setAccessible(true);
      unsafe = (Unsafe) theUnsafe.get(null);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }

  private static EnumProtocol toNMS(ConnectionState state) {
    return EnumProtocol.a(state.ordinal());
  }

  public static IChatBaseComponent fromNMS(String component) {
    return IChatBaseComponent.a(component);
  }

  public static String toNMS(IChatBaseComponent component) {
    return IChatBaseComponent.ChatSerializer.a(component);
  }

  public static PacketDataSerializer toPacketSerializer(ByteBuf buf) {
    if (buf == null) {
      return null;
    }

    return new PacketDataSerializer(buf);
  }

  public static GameProfile fromNMS(com.mojang.authlib.GameProfile profile) {
    return new GameProfile(profile.getId(), profile.getName(),
        profile.getProperties().entries().stream().map(entry ->
            new GameProfile.Property(
                entry.getValue().getName(),
                entry.getValue().getValue(),
                entry.getValue().getSignature()))
            .collect(Collectors.toSet()));
  }

  public static ProfilePublicKey.a toNMS(SignatureData signatureData) throws CryptographyException {
    if (signatureData == null) {
      return null;
    }

    return new ProfilePublicKey.a(
        signatureData.getTimestamp(),
        MinecraftEncryption.a(signatureData.getPublicKey()),
        signatureData.getSignature()
    );
  }

  @Override
  public PacketRemapper createRemapper(VersionPacketRegistry inboundRegistry, VersionPacketRegistry outboundRegistry) {
    Gson statusGson;

    try {
      Field statusGsonField = PacketStatusOutServerInfo.class.getDeclaredField("a");
      statusGsonField.setAccessible(true);
      statusGson = (Gson) statusGsonField.get(null);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }

    Map<Class<? extends Packet>, InboundPacketRemapper> inboundRemapper = new HashMap<>();

    // HANDSHAKE

    inboundRemapper.put(Handshake.class, VersionAdapter.<Handshake>wrapInbound(packet ->
        new PacketHandshakingInSetProtocol(packet.getIP(), packet.getPort(), toNMS(packet.getNextState()))));

    // STATUS

    inboundRemapper.put(StatusRequest.class, packet -> new PacketStatusInStart());

    inboundRemapper.put(StatusPing.class, VersionAdapter.<StatusPing>wrapInbound(packet ->
        new PacketStatusInPing(packet.getPayload())));

    // LOGIN

    inboundRemapper.put(LoginStart.class, VersionAdapter.<LoginStart>wrapInbound(packet -> {
      try {
        return new PacketLoginInStart(
            packet.getName(),
            Optional.ofNullable(toNMS(packet.getSignatureData())),
            Optional.ofNullable(packet.getPlayerUUID()));
      } catch (CryptographyException e) {
        throw new RuntimeException(e);
      }
    }));

    try {
      Field sharedSecretField = PacketLoginInEncryptionBegin.class.getDeclaredField("a");
      sharedSecretField.setAccessible(true);

      Field eitherField = PacketLoginInEncryptionBegin.class.getDeclaredField("b");
      eitherField.setAccessible(true);

      inboundRemapper.put(EncryptionResponse.class, VersionAdapter.<EncryptionResponse>wrapInbound(packet -> {
        try {
          PacketLoginInEncryptionBegin nmsPacket = (PacketLoginInEncryptionBegin)
              unsafe.allocateInstance(PacketLoginInEncryptionBegin.class);

          sharedSecretField.set(nmsPacket, packet.getSharedSecret());
          if (packet.isHasVerifyToken()) {
            eitherField.set(nmsPacket, Either.left(packet.getVerifyToken()));
          } else {
            eitherField.set(nmsPacket, Either.right(
                new MinecraftEncryption.b(packet.getSalt(), packet.getMessageSignature())));
          }

          return nmsPacket;
        } catch (ReflectiveOperationException e) {
          throw new RuntimeException(e);
        }
      }));
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }

    inboundRemapper.put(LoginPluginResponse.class, VersionAdapter.<LoginPluginResponse>wrapInbound(packet ->
        new PacketLoginInCustomPayload(packet.getMessageID(), toPacketSerializer(packet.getData()))));

    Map<Class<?>, OutboundPacketRemapper> outboundRemapper = new HashMap<>();

    // STATUS

    outboundRemapper.put(PacketStatusOutServerInfo.class, VersionAdapter.<PacketStatusOutServerInfo>wrapOutbound(
        packet -> new StatusResponse(statusGson.toJson(packet.b()))));

    outboundRemapper.put(PacketStatusOutPong.class, VersionAdapter.<PacketStatusOutPong>wrapOutbound(
        packet -> new StatusPing(packet.b())));

    // LOGIN

    outboundRemapper.put(PacketLoginOutDisconnect.class, VersionAdapter.<PacketLoginOutDisconnect>wrapOutbound(
        packet -> new LoginDisconnect(toNMS(packet.b()))));

    outboundRemapper.put(PacketLoginOutEncryptionBegin.class, VersionAdapter.<PacketLoginOutEncryptionBegin>wrapOutbound(
        packet -> {
          try {
            return new EncryptionRequest(packet.b(), packet.c().getEncoded(), packet.d());
          } catch (CryptographyException e) {
            throw new RuntimeException(e);
          }
        }));

    outboundRemapper.put(PacketLoginOutSuccess.class, VersionAdapter.<PacketLoginOutSuccess>wrapOutbound(
        packet -> new LoginSuccess(fromNMS(packet.b()))));

    outboundRemapper.put(PacketLoginOutSetCompression.class, VersionAdapter.<PacketLoginOutSetCompression>wrapOutbound(
        packet -> new SetCompression(packet.b())));

    outboundRemapper.put(PacketLoginOutCustomPayload.class, VersionAdapter.<PacketLoginOutCustomPayload>wrapOutbound(
        packet -> new LoginPluginRequest(packet.b(), packet.c().toString(), packet.d())));

    return new PacketRemapper(inboundRemapper, outboundRemapper, inboundRegistry, outboundRegistry);
  }
}
