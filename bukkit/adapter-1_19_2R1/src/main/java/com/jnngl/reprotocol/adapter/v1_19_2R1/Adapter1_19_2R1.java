package com.jnngl.reprotocol.adapter.v1_19_2R1;

import com.google.gson.Gson;
import com.jnngl.reprotocol.ConnectionState;
import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.adapter.VersionAdapter;
import com.jnngl.reprotocol.data.BlockPos;
import com.jnngl.reprotocol.data.GameProfile;
import com.jnngl.reprotocol.data.GlobalBlockPos;
import com.jnngl.reprotocol.data.ItemStack;
import com.jnngl.reprotocol.data.PlayerListData;
import com.jnngl.reprotocol.data.SignatureData;
import com.jnngl.reprotocol.data.chat.ChatStyle;
import com.jnngl.reprotocol.data.datapack.Tag;
import com.jnngl.reprotocol.data.recipe.CookingRecipe;
import com.jnngl.reprotocol.data.recipe.Ingredient;
import com.jnngl.reprotocol.data.recipe.Recipe;
import com.jnngl.reprotocol.data.recipe.RecipeBookSettings;
import com.jnngl.reprotocol.data.recipe.ShapedRecipe;
import com.jnngl.reprotocol.data.recipe.ShapelessRecipe;
import com.jnngl.reprotocol.data.recipe.SmithingRecipe;
import com.jnngl.reprotocol.data.recipe.StonecutterRecipe;
import com.jnngl.reprotocol.data.codec.BiomeType;
import com.jnngl.reprotocol.data.codec.ChatType;
import com.jnngl.reprotocol.data.codec.DimensionType;
import com.jnngl.reprotocol.data.codec.RegistryCodec;
import com.jnngl.reprotocol.packet.handshake.Handshake;
import com.jnngl.reprotocol.packet.login.EncryptionRequest;
import com.jnngl.reprotocol.packet.login.EncryptionResponse;
import com.jnngl.reprotocol.packet.login.LoginDisconnect;
import com.jnngl.reprotocol.packet.login.LoginPluginRequest;
import com.jnngl.reprotocol.packet.login.LoginPluginResponse;
import com.jnngl.reprotocol.packet.login.LoginStart;
import com.jnngl.reprotocol.packet.login.LoginSuccess;
import com.jnngl.reprotocol.packet.login.SetCompression;
import com.jnngl.reprotocol.packet.play.ChangeDifficulty;
import com.jnngl.reprotocol.packet.play.Disconnect;
import com.jnngl.reprotocol.packet.play.EntityEvent;
import com.jnngl.reprotocol.packet.play.JoinGame;
import com.jnngl.reprotocol.packet.play.PlayerInfo;
import com.jnngl.reprotocol.packet.play.PluginMessage;
import com.jnngl.reprotocol.packet.play.ServerPlayerAbilities;
import com.jnngl.reprotocol.packet.play.ServerSetHeldItem;
import com.jnngl.reprotocol.packet.play.SetCenterChunk;
import com.jnngl.reprotocol.packet.play.SetRenderDistance;
import com.jnngl.reprotocol.packet.play.SetSimulationDistance;
import com.jnngl.reprotocol.packet.play.SpawnEntity;
import com.jnngl.reprotocol.packet.play.SynchronizePlayerPosition;
import com.jnngl.reprotocol.packet.play.SystemChat;
import com.jnngl.reprotocol.packet.play.UpdateRecipeBook;
import com.jnngl.reprotocol.packet.play.UpdateRecipes;
import com.jnngl.reprotocol.packet.play.UpdateTags;
import com.jnngl.reprotocol.packet.status.StatusPing;
import com.jnngl.reprotocol.packet.status.StatusRequest;
import com.jnngl.reprotocol.packet.status.StatusResponse;
import com.jnngl.reprotocol.packet.registry.VersionPacketRegistry;
import com.jnngl.reprotocol.remapper.InboundPacketRemapper;
import com.jnngl.reprotocol.remapper.OutboundPacketRemapper;
import com.jnngl.reprotocol.remapper.PacketRemapper;
import com.jnngl.reprotocol.util.ExceptionUtil;
import com.mojang.datafixers.util.Either;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.IRegistry;
import net.minecraft.core.IRegistryCustom;
import net.minecraft.network.EnumProtocol;
import net.minecraft.network.PacketDataSerializer;
import net.minecraft.network.chat.ChatHexColor;
import net.minecraft.network.chat.ChatMessageType;
import net.minecraft.network.chat.ChatModifier;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.ClientboundSetSimulationDistancePacket;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import net.minecraft.network.protocol.game.PacketPlayOutAbilities;
import net.minecraft.network.protocol.game.PacketPlayOutCustomPayload;
import net.minecraft.network.protocol.game.PacketPlayOutEntityStatus;
import net.minecraft.network.protocol.game.PacketPlayOutHeldItemSlot;
import net.minecraft.network.protocol.game.PacketPlayOutKickDisconnect;
import net.minecraft.network.protocol.game.PacketPlayOutLogin;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.network.protocol.game.PacketPlayOutPosition;
import net.minecraft.network.protocol.game.PacketPlayOutRecipeUpdate;
import net.minecraft.network.protocol.game.PacketPlayOutRecipes;
import net.minecraft.network.protocol.game.PacketPlayOutServerDifficulty;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity;
import net.minecraft.network.protocol.game.PacketPlayOutTags;
import net.minecraft.network.protocol.game.PacketPlayOutViewCentre;
import net.minecraft.network.protocol.game.PacketPlayOutViewDistance;
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
import net.minecraft.resources.MinecraftKey;
import net.minecraft.tags.TagNetworkSerialization;
import net.minecraft.util.CryptographyException;
import net.minecraft.util.MinecraftEncryption;
import net.minecraft.world.entity.player.ProfilePublicKey;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeCooking;
import net.minecraft.world.item.crafting.RecipeItemStack;
import net.minecraft.world.item.crafting.RecipeMapExtend;
import net.minecraft.world.item.crafting.RecipeSmithing;
import net.minecraft.world.item.crafting.RecipeStonecutting;
import net.minecraft.world.item.crafting.ShapedRecipes;
import net.minecraft.world.item.crafting.ShapelessRecipes;
import net.minecraft.world.level.biome.BiomeBase;
import net.minecraft.world.level.biome.BiomeFog;
import net.minecraft.world.level.biome.BiomeParticles;
import net.minecraft.world.level.dimension.DimensionManager;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.util.AbstractMap;
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
  private static final Field RECIPE_SMITHING$A;
  private static final Field RECIPE_SMITHING$B;
  private static final Field TAG_NETWORK$A$A;
  private static final Field ENTITY_STATUS$A;

  private static final Field ENC_BEGIN$A;
  private static final Field ENC_BEGIN$B;

  private static final MethodHandle SYSTEM_CHAT_CONTENT_GETTER;

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

      // TODO: Replace with MethodHandles
      (BIOME_BASE$I = BiomeBase.class.getDeclaredField("i")).setAccessible(true);
      (BIOME_BASE$L = BiomeBase.class.getDeclaredField("l")).setAccessible(true);
      (BIOME_BASE$B$D = BiomeBase$b.getDeclaredField("d")).setAccessible(true);
      (BIOME_PARTICLES$C = BiomeParticles.class.getDeclaredField("c")).setAccessible(true);
      (RECIPE_SMITHING$A = RecipeSmithing.class.getDeclaredField("a")).setAccessible(true);
      (RECIPE_SMITHING$B = RecipeSmithing.class.getDeclaredField("b")).setAccessible(true);
      (TAG_NETWORK$A$A = TagNetworkSerialization.a.class.getDeclaredField("a")).setAccessible(true);
      (ENTITY_STATUS$A = PacketPlayOutEntityStatus.class.getDeclaredField("a")).setAccessible(true);

      (ENC_BEGIN$A = PacketLoginInEncryptionBegin.class.getDeclaredField("a")).setAccessible(true);
      (ENC_BEGIN$B = PacketLoginInEncryptionBegin.class.getDeclaredField("b")).setAccessible(true);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }

    SYSTEM_CHAT_CONTENT_GETTER =
        ExceptionUtil.doQuiet(() ->
            MethodHandles.publicLookup()
                .findVirtual(
                    ClientboundSystemChatPacket.class,
                    "content",
                    MethodType.methodType(String.class)
                )
        ).orElse(null);
  }

  private static EnumProtocol toNMS(ConnectionState state) {
    return EnumProtocol.a(state.ordinal());
  }

  private static IChatBaseComponent toNMS(String component) {
    return component != null ? IChatBaseComponent.a(component) : null;
  }

  private static String fromNMS(IChatBaseComponent component) {
    return component != null ? IChatBaseComponent.ChatSerializer.a(component) : null;
  }

  private static PacketDataSerializer toPacketSerializer(ByteBuf buf) {
    return buf != null ? new PacketDataSerializer(buf) : null;
  }

  private static GameProfile fromNMS(com.mojang.authlib.GameProfile profile) {
    return new GameProfile(profile.getId(), profile.getName(),
        profile.getProperties().entries().stream().map(entry ->
                new GameProfile.Property(
                    entry.getValue().getName(),
                    entry.getValue().getValue(),
                    entry.getValue().getSignature()
                ))
            .collect(Collectors.toSet())
    );
  }

  private static ProfilePublicKey.a toNMS(SignatureData signatureData) throws CryptographyException {
    return signatureData == null ? null :
        new ProfilePublicKey.a(
            signatureData.getTimestamp(),
            MinecraftEncryption.a(signatureData.getPublicKey()),
            signatureData.getSignature()
        );
  }

  private static SignatureData fromNMS(ProfilePublicKey.a signatureData) {
    return signatureData == null ? null :
        new SignatureData(
            signatureData.b(),
            signatureData.c().getEncoded(),
            signatureData.d()
        );
  }

  private static RegistryCodec fromNMS(IRegistryCustom.Dimension codec) {
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

  private static GlobalBlockPos fromNMS(GlobalPos globalPos) {
    return new GlobalBlockPos(
        globalPos.a().a().toString(), // identifier
        new BlockPos(globalPos.b().u(), globalPos.b().v(), globalPos.b().w()) // block position
    );
  }

  private static ChatStyle fromNMS(ChatModifier modifier) {
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

  private static ItemStack fromNMS(net.minecraft.world.item.ItemStack itemStack) {
    if (itemStack.d().b()) { // present
      return new ItemStack(
          IRegistry.Y.a(itemStack.d().a()), // item id ; TODO: Material class, mappings
          itemStack.K(), // count
          null // TODO: nbt
      );
    } else {
      return new ItemStack();
    }
  }

  private static Ingredient fromNMS(RecipeItemStack recipeItemStack) {
    return new Ingredient(
        Arrays.stream(recipeItemStack.a())
            .map(Adapter1_19_2R1::fromNMS)
            .collect(Collectors.toList()) // items
    );
  }

  @Override
  public PacketRemapper createRemapper(VersionPacketRegistry inboundRegistry, VersionPacketRegistry outboundRegistry) {
    Map<Class<? extends Packet>, InboundPacketRemapper> inboundRemapper = new HashMap<>();

    // HANDSHAKE

    inboundRemapper.put(Handshake.class, VersionAdapter.wrapInbound(this::remapHandshake));

    // STATUS

    inboundRemapper.put(StatusRequest.class, VersionAdapter.wrapInbound(this::remapStatusRequest));
    inboundRemapper.put(StatusPing.class, VersionAdapter.wrapInbound(this::remapStatusPing));

    // LOGIN

    inboundRemapper.put(LoginStart.class, VersionAdapter.wrapInbound(this::remapLoginStart));
    inboundRemapper.put(EncryptionResponse.class, VersionAdapter.wrapInbound(this::remapEncryptionResponse));
    inboundRemapper.put(LoginPluginResponse.class, VersionAdapter.wrapInbound(this::remapLoginPluginMessage));

    Map<Class<?>, OutboundPacketRemapper> outboundRemapper = new HashMap<>();

    // STATUS

    outboundRemapper.put(PacketStatusOutServerInfo.class, VersionAdapter.wrapOutbound(this::remapStatusResponse));
    outboundRemapper.put(PacketStatusOutPong.class, VersionAdapter.wrapOutbound(this::remapStatusPong));

    // LOGIN

    outboundRemapper.put(PacketLoginOutDisconnect.class, VersionAdapter.wrapOutbound(this::remapLoginDisconnect));
    outboundRemapper.put(PacketLoginOutEncryptionBegin.class, VersionAdapter.wrapOutbound(this::remapEncryptionRequest));
    outboundRemapper.put(PacketLoginOutSuccess.class, VersionAdapter.wrapOutbound(this::remapLoginSuccess));
    outboundRemapper.put(PacketLoginOutSetCompression.class, VersionAdapter.wrapOutbound(this::remapSetCompression));
    outboundRemapper.put(PacketLoginOutCustomPayload.class, VersionAdapter.wrapOutbound(this::remapLoginPluginRequest));

    // PLAY

    outboundRemapper.put(PacketPlayOutSpawnEntity.class, VersionAdapter.wrapOutbound(this::remapSpawnEntity));
    outboundRemapper.put(PacketPlayOutServerDifficulty.class, VersionAdapter.wrapOutbound(this::remapChangeDifficulty));
    outboundRemapper.put(PacketPlayOutCustomPayload.class, VersionAdapter.wrapOutbound(this::remapPluginMessage));
    outboundRemapper.put(PacketPlayOutKickDisconnect.class, VersionAdapter.wrapOutbound(this::remapDisconnect));
    outboundRemapper.put(PacketPlayOutEntityStatus.class, VersionAdapter.wrapOutbound(this::remapEntityEvent));
    outboundRemapper.put(PacketPlayOutLogin.class, VersionAdapter.wrapOutbound(this::remapJoinGame));
    outboundRemapper.put(PacketPlayOutAbilities.class, VersionAdapter.wrapOutbound(this::remapServerPlayerAbilities));
    outboundRemapper.put(PacketPlayOutPlayerInfo.class, VersionAdapter.wrapOutbound(this::remapPlayerInfo));
    outboundRemapper.put(PacketPlayOutPosition.class, VersionAdapter.wrapOutbound(this::remapSynchronizePlayerPosition));
    outboundRemapper.put(PacketPlayOutRecipes.class, VersionAdapter.wrapOutbound(this::remapUpdateRecipeBook));
    outboundRemapper.put(PacketPlayOutHeldItemSlot.class, VersionAdapter.wrapOutbound(this::remapServerSetHeldItem));
    outboundRemapper.put(PacketPlayOutViewCentre.class, VersionAdapter.wrapOutbound(this::remapSetCenterChunk));
    outboundRemapper.put(PacketPlayOutViewDistance.class, VersionAdapter.wrapOutbound(this::remapSetRenderDistance));
    outboundRemapper.put(ClientboundSetSimulationDistancePacket.class, VersionAdapter.wrapOutbound(this::remapSetSimulationDistance));
    outboundRemapper.put(ClientboundSystemChatPacket.class, VersionAdapter.wrapOutbound(this::remapSystemChat));
    outboundRemapper.put(PacketPlayOutRecipeUpdate.class, VersionAdapter.wrapOutbound(this::remapUpdateRecipes));
    outboundRemapper.put(PacketPlayOutTags.class, VersionAdapter.wrapOutbound(this::remapUpdateTags));

    return new PacketRemapper(inboundRemapper, outboundRemapper, inboundRegistry, outboundRegistry);
  }

  private PacketHandshakingInSetProtocol remapHandshake(Handshake packet) {
    return new PacketHandshakingInSetProtocol(
        packet.getIP(),
        packet.getPort(),
        toNMS(packet.getNextState())
    );
  }

  private PacketStatusInStart remapStatusRequest(StatusRequest packet) {
    return new PacketStatusInStart();
  }

  private PacketStatusInPing remapStatusPing(StatusPing packet) {
    return new PacketStatusInPing(
        packet.getPayload()
    );
  }

  private PacketLoginInStart remapLoginStart(LoginStart packet) {
    try {
      return new PacketLoginInStart(
          packet.getName(),
          Optional.ofNullable(toNMS(packet.getSignatureData())),
          Optional.ofNullable(packet.getPlayerUUID())
      );
    } catch (CryptographyException e) {
      throw new RuntimeException(e);
    }
  }

  private PacketLoginInEncryptionBegin remapEncryptionResponse(EncryptionResponse packet) {
    try {
      PacketLoginInEncryptionBegin nmsPacket = (PacketLoginInEncryptionBegin)
          UNSAFE.allocateInstance(PacketLoginInEncryptionBegin.class);

      ENC_BEGIN$A.set(nmsPacket, packet.getSharedSecret());
      if (packet.isHasVerifyToken()) {
        ENC_BEGIN$B.set(nmsPacket, Either.left(packet.getVerifyToken()));
      } else {
        ENC_BEGIN$B.set(nmsPacket, Either.right(
            new MinecraftEncryption.b(packet.getSalt(), packet.getMessageSignature())));
      }

      return nmsPacket;
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }

  private PacketLoginInCustomPayload remapLoginPluginMessage(LoginPluginResponse packet) {
    return new PacketLoginInCustomPayload(
        packet.getMessageID(),
        toPacketSerializer(packet.getData())
    );
  }

  private StatusResponse remapStatusResponse(PacketStatusOutServerInfo packet) {
    return new StatusResponse(
        STATUS_GSON.toJson(packet.b()) // response
    );
  }

  private StatusPing remapStatusPong(PacketStatusOutPong packet) {
    return new StatusPing(
        packet.b() // payload
    );
  }

  private LoginDisconnect remapLoginDisconnect(PacketLoginOutDisconnect packet) {
    return new LoginDisconnect(
        fromNMS(packet.b()) // reason
    );
  }

  private EncryptionRequest remapEncryptionRequest(PacketLoginOutEncryptionBegin packet) {
    try {
      return new EncryptionRequest(
          packet.b(), // server id
          packet.c().getEncoded(), // public key
          packet.d() // verify token
      );
    } catch (CryptographyException e) {
      throw new RuntimeException(e);
    }
  }

  private LoginSuccess remapLoginSuccess(PacketLoginOutSuccess packet) {
    return new LoginSuccess(
        fromNMS(packet.b()) // game profile
    );
  }

  private SetCompression remapSetCompression(PacketLoginOutSetCompression packet) {
    return new SetCompression(
        packet.b() // threshold
    );
  }

  private LoginPluginRequest remapLoginPluginRequest(PacketLoginOutCustomPayload packet) {
    return new LoginPluginRequest(
        packet.b(), // message id
        packet.c().toString(), // channel
        packet.d() // data
    );
  }

  private SpawnEntity remapSpawnEntity(PacketPlayOutSpawnEntity packet) {
    return new SpawnEntity(
        packet.b(), // entity id
        packet.c(), // uuid
        IRegistry.X.a(packet.d()), // type
        packet.e(), // x
        packet.f(), // y
        packet.g(), // z
        packet.k(), // pitch
        packet.l(), // yaw
        packet.m(), // head yaw
        packet.n(), // data
        packet.h(), // velocity x
        packet.i(), // velocity y
        packet.j()  // velocity z
    );
  }

  private ChangeDifficulty remapChangeDifficulty(PacketPlayOutServerDifficulty packet) {
    return new ChangeDifficulty(
        (byte) packet.c().a(), // difficulty
        packet.b() // difficulty locked
    );
  }

  private PluginMessage remapPluginMessage(PacketPlayOutCustomPayload packet) {
    return new PluginMessage(
        packet.b().toString(), // channel
        packet.c() // data
    );
  }

  private Disconnect remapDisconnect(PacketPlayOutKickDisconnect packet) {
    return new Disconnect(
        fromNMS(packet.b())
    );
  }

  private EntityEvent remapEntityEvent(PacketPlayOutEntityStatus packet) {
    try {
      return new EntityEvent(
          ENTITY_STATUS$A.getInt(packet), // entity id
          packet.b() // status
      );
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }

  private JoinGame remapJoinGame(PacketPlayOutLogin packet) {
    return new JoinGame(
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
    );
  }

  private ServerPlayerAbilities remapServerPlayerAbilities(PacketPlayOutAbilities packet) {
    return new ServerPlayerAbilities(
        (byte) ((packet.b() ? 0x01 : 0) | // invulnerable
            (packet.c() ? 0x02 : 0) | // flying
            (packet.d() ? 0x04 : 0) | // allow flying
            (packet.e() ? 0x08 : 0)), // creative mode
        packet.f(), // flying speed
        packet.g() // fov modifier
    );
  }

  private SynchronizePlayerPosition remapSynchronizePlayerPosition(PacketPlayOutPosition packet) {
    return new SynchronizePlayerPosition(
        packet.b(), // x
        packet.c(), // y
        packet.d(), // z
        packet.e(), // yaw
        packet.f(), // pitch
        PacketPlayOutPosition.EnumPlayerTeleportFlags.a(packet.i()), // flags
        packet.g(), // teleport id
        packet.h() // dismount vehicle
    );
  }

  private PlayerInfo remapPlayerInfo(PacketPlayOutPlayerInfo packet) {
    return new PlayerInfo(
        PlayerInfo.Action.values()[packet.c().ordinal()], // action
        packet.b().stream()
            .map(player ->
                new PlayerListData(
                    fromNMS(player.a()), // game profile
                    player.c().a(), // gamemode
                    player.b(), // latency
                    fromNMS(player.d()), // display name
                    fromNMS(player.e()) // signature
                )
            )
            .collect(Collectors.toList()) // players
    );
  }

  private UpdateRecipeBook remapUpdateRecipeBook(PacketPlayOutRecipes packet) {
    return new UpdateRecipeBook(
        packet.e().ordinal(), // action
        Arrays.stream(RecipeBookType.values())
            .map(type -> new RecipeBookSettings(
                packet.d().a(type),
                packet.d().b(type)
            )).toArray(RecipeBookSettings[]::new), // settings
        packet.b().stream()
            .map(MinecraftKey::toString)
            .collect(Collectors.toList()), // recipes
        packet.c() != null
            ? packet.c().stream()
            .map(MinecraftKey::toString)
            .collect(Collectors.toList())
            : null // init recipes
    );
  }

  private ServerSetHeldItem remapServerSetHeldItem(PacketPlayOutHeldItemSlot packet) {
    return new ServerSetHeldItem(
        (byte) packet.b() // slot
    );
  }

  private SetCenterChunk remapSetCenterChunk(PacketPlayOutViewCentre packet) {
    return new SetCenterChunk(
        packet.b(), // chunk x
        packet.c() // chunk z
    );
  }

  private SetRenderDistance remapSetRenderDistance(PacketPlayOutViewDistance packet) {
    return new SetRenderDistance(
        packet.b() // view distance
    );
  }

  private SetSimulationDistance remapSetSimulationDistance(ClientboundSetSimulationDistancePacket packet) {
    return new SetSimulationDistance(
        packet.b() // simulation distance
    );
  }

  private SystemChat remapSystemChat(ClientboundSystemChatPacket packet) {
    if (SYSTEM_CHAT_CONTENT_GETTER != null) {
      try {
        return new SystemChat(
            (String) SYSTEM_CHAT_CONTENT_GETTER.invokeExact(packet), // content
            packet.c() // overlay
        );
      } catch (Throwable e) {
        throw new IllegalStateException(e);
      }
    }
    {
      return new SystemChat(
          fromNMS(packet.b()), // content
          packet.c() // overlay
      );
    }
  }

  private UpdateRecipes remapUpdateRecipes(PacketPlayOutRecipeUpdate packet) {
    return new UpdateRecipes(
        packet.b().stream().map(recipe -> {
          String type = String.valueOf(IRegistry.aj.b(recipe.U_()));
          String recipeID = String.valueOf(recipe.f());

          if (recipe instanceof ShapelessRecipes shapelessRecipe) {
            return new ShapelessRecipe(
                type, recipeID,
                shapelessRecipe.d(), // group
                shapelessRecipe.a().stream()
                    .map(Adapter1_19_2R1::fromNMS)
                    .collect(Collectors.toList()), // ingredients
                fromNMS(shapelessRecipe.c())
            );
          } else if (recipe instanceof ShapedRecipes shapedRecipe && !(recipe instanceof RecipeMapExtend)) {
            return new ShapedRecipe(
                type, recipeID,
                shapedRecipe.j(), // width
                shapedRecipe.k(), // height
                shapedRecipe.d(), // group
                shapedRecipe.a().stream()
                    .map(Adapter1_19_2R1::fromNMS)
                    .collect(Collectors.toList()), // ingredients
                fromNMS(shapedRecipe.c()) // result
            );
          } else if (recipe instanceof RecipeCooking cookingRecipe) {
            return new CookingRecipe(
                type, recipeID,
                cookingRecipe.d(), // group
                cookingRecipe.a().stream()
                    .findFirst()
                    .map(Adapter1_19_2R1::fromNMS)
                    .orElse(null), // ingredient
                fromNMS(cookingRecipe.c()), // result
                cookingRecipe.b(), // experience
                cookingRecipe.e() // cooking time
            );
          } else if (recipe instanceof RecipeStonecutting) {
            return new StonecutterRecipe(
                type, recipeID,
                recipe.d(),
                recipe.a().stream()
                    .findFirst()
                    .map(Adapter1_19_2R1::fromNMS)
                    .orElse(null), // ingredient
                fromNMS(recipe.c())
            );
          } else if (recipe instanceof RecipeSmithing) {
            try {
              return new SmithingRecipe(
                  type, recipeID,
                  fromNMS((RecipeItemStack) RECIPE_SMITHING$A.get(recipe)),
                  fromNMS((RecipeItemStack) RECIPE_SMITHING$B.get(recipe)),
                  fromNMS(recipe.c())
              );
            } catch (ReflectiveOperationException e) {
              throw new RuntimeException(e);
            }
          }

          return new Recipe(type, recipeID);
        }).collect(Collectors.toList())
    );
  }

  @SuppressWarnings("unchecked")
  private UpdateTags remapUpdateTags(PacketPlayOutTags packet) {
    return new UpdateTags(
        packet.b().entrySet().stream()
            .map(entry -> {
              try {
                return new AbstractMap.SimpleEntry<>(
                    entry.getKey().a().toString(),
                    ((Map<MinecraftKey, IntList>) TAG_NETWORK$A$A.get(entry.getValue()))
                        .entrySet().stream().map(
                            tagEntry -> new Tag(
                                tagEntry.getKey().toString(),
                                tagEntry.getValue().intStream().boxed().toList()
                            )
                        ).collect(Collectors.toList())
                );
              } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
              }
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
    );
  }
}
