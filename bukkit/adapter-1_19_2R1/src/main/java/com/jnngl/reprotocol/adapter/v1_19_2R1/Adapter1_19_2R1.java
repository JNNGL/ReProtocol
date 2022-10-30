package com.jnngl.reprotocol.adapter.v1_19_2R1;

import com.google.gson.Gson;
import com.jnngl.reprotocol.ConnectionState;
import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.adapter.VersionAdapter;
import com.jnngl.reprotocol.data.BlockPos;
import com.jnngl.reprotocol.data.GameProfile;
import com.jnngl.reprotocol.data.GlobalBlockPos;
import com.jnngl.reprotocol.data.SignatureData;
import com.jnngl.reprotocol.data.chat.ChatStyle;
import com.jnngl.reprotocol.data.registry.BiomeType;
import com.jnngl.reprotocol.data.registry.ChatType;
import com.jnngl.reprotocol.data.registry.DimensionType;
import com.jnngl.reprotocol.data.registry.RegistryCodec;
import com.jnngl.reprotocol.packet.handshake.Handshake;
import com.jnngl.reprotocol.packet.login.EncryptionRequest;
import com.jnngl.reprotocol.packet.login.EncryptionResponse;
import com.jnngl.reprotocol.packet.login.LoginDisconnect;
import com.jnngl.reprotocol.packet.login.LoginPluginRequest;
import com.jnngl.reprotocol.packet.login.LoginPluginResponse;
import com.jnngl.reprotocol.packet.login.LoginStart;
import com.jnngl.reprotocol.packet.login.LoginSuccess;
import com.jnngl.reprotocol.packet.login.SetCompression;
import com.jnngl.reprotocol.packet.play.JoinGame;
import com.jnngl.reprotocol.packet.status.StatusPing;
import com.jnngl.reprotocol.packet.status.StatusRequest;
import com.jnngl.reprotocol.packet.status.StatusResponse;
import com.jnngl.reprotocol.packet.registry.VersionPacketRegistry;
import com.jnngl.reprotocol.remapper.InboundPacketRemapper;
import com.jnngl.reprotocol.remapper.OutboundPacketRemapper;
import com.jnngl.reprotocol.remapper.PacketRemapper;
import com.mojang.datafixers.util.Either;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.IRegistry;
import net.minecraft.core.IRegistryCustom;
import net.minecraft.network.EnumProtocol;
import net.minecraft.network.PacketDataSerializer;
import net.minecraft.network.chat.ChatHexColor;
import net.minecraft.network.chat.ChatMessageType;
import net.minecraft.network.chat.ChatModifier;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.PacketPlayOutLogin;
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
import net.minecraft.world.level.biome.BiomeBase;
import net.minecraft.world.level.biome.BiomeFog;
import net.minecraft.world.level.biome.BiomeParticles;
import net.minecraft.world.level.dimension.DimensionManager;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Adapter1_19_2R1 implements VersionAdapter {

  private static final Unsafe UNSAFE;

  private static final Gson STATUS_GSON;

  private static final Field BIOME_BASE$I;
  private static final Field BIOME_BASE$L;
  private static final Field BIOME_BASE$B$D;
  private static final Field BIOME_PARTICLES$C;

  static {
    try {
      Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
      theUnsafe.setAccessible(true);
      UNSAFE = (Unsafe) theUnsafe.get(null);

      Field statusGsonField = PacketStatusOutServerInfo.class.getDeclaredField("a");
      statusGsonField.setAccessible(true);
      STATUS_GSON = (Gson) statusGsonField.get(null);

      Class<?> BiomeBase$b =
          Arrays.stream(BiomeBase.class.getDeclaredClasses())
              .filter(cls -> cls.getSimpleName().equals("b"))
              .findFirst().orElseThrow();

      BIOME_BASE$I = BiomeBase.class.getDeclaredField("i");
      BIOME_BASE$I.setAccessible(true);

      BIOME_BASE$L = BiomeBase.class.getDeclaredField("l");
      BIOME_BASE$L.setAccessible(true);

      BIOME_BASE$B$D = BiomeBase$b.getDeclaredField("d");
      BIOME_BASE$B$D.setAccessible(true);

      BIOME_PARTICLES$C = BiomeParticles.class.getDeclaredField("c");
      BIOME_PARTICLES$C.setAccessible(true);
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

  public static RegistryCodec fromNMS(IRegistryCustom.Dimension codec) {
    Set<DimensionType> dimensionTypes = new LinkedHashSet<>();

    IRegistry<DimensionManager> dimensionCodec = codec.a(IRegistry.O).orElseThrow();
    dimensionCodec.f().forEach(entry -> {
      DimensionManager dimensionManager = entry.getValue();
      dimensionTypes.add(new DimensionType(
          entry.getKey().a().toString(), // name
          dimensionCodec.a(dimensionManager), // id
          dimensionManager.t().a(), // piglin safe
          dimensionManager.t().b(), // has raids
          dimensionManager.t().c().a(), // monster spawn light level
          dimensionManager.t().d(), // monster spawn block level limit
          dimensionManager.j(), // natural
          dimensionManager.s(), // ambient light
          dimensionManager.f().stream().boxed().findFirst().orElse(null), // fixed time
          dimensionManager.q().b().toString(), // infiniburn
          dimensionManager.m(), // respawn anchor works
          dimensionManager.g(), // has skylight
          dimensionManager.l(), // bed works
          dimensionManager.r().toString(), // effects
          dimensionManager.n(), // min y
          dimensionManager.o(), // height
          dimensionManager.p(), // logical height
          dimensionManager.k(), // coordinate scale
          dimensionManager.i(), // ultrawarm
          dimensionManager.h() // has ceiling
      ));
    });

    Set<BiomeType> biomeTypes = new LinkedHashSet<>();

    IRegistry<BiomeBase> biomeCodec = codec.a(IRegistry.aR).orElseThrow();
    biomeCodec.f().forEach(entry -> {
      try {
        BiomeBase biomeBase = entry.getValue();
        BiomeFog biomeFog = (BiomeFog) BIOME_BASE$L.get(biomeBase);

        biomeTypes.add(new BiomeType(
            entry.getKey().a().toString(), // key
            biomeCodec.a(biomeBase), // id
            biomeBase.c().a(), // precipitation
            null, // depth
            biomeBase.i(), // temperature
            null, // scale
            biomeBase.h(), // downfall
            null, // category
            ((BiomeBase.TemperatureModifier) BIOME_BASE$B$D.get(
                BIOME_BASE$I.get(biomeBase))).a(), // temperature modifier
            biomeFog.d(), // sky color
            biomeFog.c(), // water fog color
            biomeFog.a(), // fog color
            biomeFog.b(), // water color
            biomeFog.e().orElse(null), // foliage color
            biomeFog.f().orElse(null), // grass color
            biomeFog.g() != null ? biomeFog.g().a() : null, // grass color modifier
            biomeFog.l().map(music -> new BiomeType.Music(
                music.d(), // replace current music
                music.a().a().toString(), // sound
                music.c(), // max delay
                music.b() // min delay
            )).orElse(null), // music
            biomeFog.i().map(soundEffect -> soundEffect.a().toString()).orElse(null), // ambient sound
            biomeFog.k().map(caveSound -> new BiomeType.AdditionsSound(
                caveSound.a().a().toString(), // sound
                caveSound.b() // tick chance
            )).orElse(null), // additions sound
            biomeFog.j().map(caveSoundSettings -> new BiomeType.MoodSound(
                caveSoundSettings.a().a().toString(), // sound
                caveSoundSettings.b(), // tick delay
                caveSoundSettings.d(), // offset
                caveSoundSettings.c() // block search extent
            )).orElse(null), // mood sound
            biomeFog.h().map(biomeParticles -> {
              try {
                return new BiomeType.Particle(
                    BIOME_PARTICLES$C.getFloat(biomeParticles), // probability
                    Objects.requireNonNull(IRegistry.aa.b(biomeParticles.a().b())).a() // type
                );
              } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
              }
            }).orElse(null) // particle
        ));
      } catch (ReflectiveOperationException e) {
        throw new RuntimeException(e);
      }
    });

    Set<ChatType> chatTypes = new LinkedHashSet<>();

    IRegistry<ChatMessageType> chatCodec = codec.a(IRegistry.bI).orElseThrow();
    chatCodec.f().forEach(entry -> {
      ChatMessageType chatMessageType = entry.getValue();
      chatTypes.add(new ChatType(
          entry.getKey().a().toString(), // name
          chatCodec.a(chatMessageType), // id
          chatMessageType.a().b().stream().map(param -> param.c()).collect(Collectors.toList()), // chat parameters
          fromNMS(chatMessageType.a().c()), // chat style
          chatMessageType.a().a(), // chat translation key
          chatMessageType.b().b().stream().map(param -> param.c()).collect(Collectors.toList()), // narration parameters
          chatMessageType.b().a() // narration translation key
      ));
    });

    return new RegistryCodec(dimensionTypes, biomeTypes, chatTypes);
  }

  public static GlobalBlockPos fromNMS(GlobalPos globalPos) {
    return new GlobalBlockPos(
        globalPos.a().a().toString(), // identifier
        new BlockPos(globalPos.b().u(), globalPos.b().v(), globalPos.b().w()) // block position
    );
  }

  public static ChatStyle fromNMS(ChatModifier modifier) {
    return new ChatStyle(
        Optional.ofNullable(modifier.a()).map(ChatHexColor::b).orElse(null), // color
        modifier.b() ? true : null, // bold
        modifier.c() ? true : null, // italic
        modifier.d() ? true : null, // underlined
        modifier.e() ? true : null, // strikethrough
        modifier.f() ? true : null, // obfuscated
        modifier.j(), // insertion
        modifier.k().toString() // font
    );
  }

  @Override
  public PacketRemapper createRemapper(VersionPacketRegistry inboundRegistry, VersionPacketRegistry outboundRegistry) {
    Map<Class<? extends Packet>, InboundPacketRemapper> inboundRemapper = new HashMap<>();

    // HANDSHAKE

    inboundRemapper.put(Handshake.class, VersionAdapter.<Handshake>wrapInbound(packet ->
        new PacketHandshakingInSetProtocol(
            packet.getIP(),
            packet.getPort(),
            toNMS(packet.getNextState())
        )
    ));

    // STATUS

    inboundRemapper.put(StatusRequest.class, packet -> new PacketStatusInStart());

    inboundRemapper.put(StatusPing.class, VersionAdapter.<StatusPing>wrapInbound(packet ->
        new PacketStatusInPing(
            packet.getPayload()
        )
    ));

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
              UNSAFE.allocateInstance(PacketLoginInEncryptionBegin.class);

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
        new PacketLoginInCustomPayload(
            packet.getMessageID(),
            toPacketSerializer(packet.getData())
        )
    ));

    Map<Class<?>, OutboundPacketRemapper> outboundRemapper = new HashMap<>();

    // STATUS

    outboundRemapper.put(PacketStatusOutServerInfo.class, VersionAdapter.<PacketStatusOutServerInfo>wrapOutbound(
        packet -> new StatusResponse(
            STATUS_GSON.toJson(packet.b()) // response
        )
    ));

    outboundRemapper.put(PacketStatusOutPong.class, VersionAdapter.<PacketStatusOutPong>wrapOutbound(
        packet -> new StatusPing(
            packet.b() // payload
        )
    ));

    // LOGIN

    outboundRemapper.put(PacketLoginOutDisconnect.class, VersionAdapter.<PacketLoginOutDisconnect>wrapOutbound(
        packet -> new LoginDisconnect(
            toNMS(packet.b()) // reason
        )
    ));

    outboundRemapper.put(PacketLoginOutEncryptionBegin.class, VersionAdapter.<PacketLoginOutEncryptionBegin>wrapOutbound(
        packet -> {
          try {
            return new EncryptionRequest(
                packet.b(), // server id
                packet.c().getEncoded(), // public key
                packet.d() // verify token
            );
          } catch (CryptographyException e) {
            throw new RuntimeException(e);
          }
        }));

    outboundRemapper.put(PacketLoginOutSuccess.class, VersionAdapter.<PacketLoginOutSuccess>wrapOutbound(
        packet -> new LoginSuccess(
            fromNMS(packet.b()) // game profile
        )
    ));

    outboundRemapper.put(PacketLoginOutSetCompression.class, VersionAdapter.<PacketLoginOutSetCompression>wrapOutbound(
        packet -> new SetCompression(
            packet.b() // threshold
        )
    ));

    outboundRemapper.put(PacketLoginOutCustomPayload.class, VersionAdapter.<PacketLoginOutCustomPayload>wrapOutbound(
        packet -> new LoginPluginRequest(
            packet.b(), // message id
            packet.c().toString(), // channel
            packet.d() // data
        )
    ));

    // PLAY

    outboundRemapper.put(PacketPlayOutLogin.class, VersionAdapter.<PacketPlayOutLogin>wrapOutbound(
        packet -> new JoinGame(
            packet.b(), // entity id
            packet.c(), // is hardcore
            (byte) packet.d().a(), // gamemode
            (byte) (packet.e() != null ? packet.e().a() : -1), // previous gamemode
            packet.f().stream().map(key -> key.a().toString()).collect(Collectors.toSet()), // dimension names
            fromNMS(packet.g()), // registry codec
            packet.h().a().toString(), // dimension type
            packet.i().a().toString(), // dimension name
            packet.j(), // hashed seed
            packet.k(), // max players
            packet.l(), // view distance
            packet.m(), // simulation distance
            packet.n(), // reduced debug info
            packet.o(), // enable respawn screen
            packet.p(), // is debug
            packet.q(), // is flag
            packet.r().map(Adapter1_19_2R1::fromNMS).orElse(null) // death location
        )
    ));

    return new PacketRemapper(inboundRemapper, outboundRemapper, inboundRegistry, outboundRegistry);
  }
}
