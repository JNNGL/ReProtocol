package com.jnngl.reprotocol.packet;

import com.jnngl.reprotocol.ConnectionState;
import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.data.registry.ClassRegistry;
import com.jnngl.reprotocol.data.registry.Registry;
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
import com.jnngl.reprotocol.packet.registry.PacketRegistry;
import com.jnngl.reprotocol.packet.registry.VersionPacketRegistry;
import com.jnngl.reprotocol.util.MapBuilder;
import com.jnngl.reprotocol.util.MinecraftVersion;

import java.util.Collections;
import java.util.function.Supplier;

public class GenericPacketRegistry {

  /* COMMON STATE REGISTRIES */

  private static final Registry<Packet> HANDSHAKE_STATE_REGISTRY =
      new ClassRegistry<>(Collections.singletonMap(0x00, Handshake::new));

  private static final Registry<Packet> C2S_STATUS_STATE_REGISTRY =
      new ClassRegistry<>(
          new MapBuilder<Integer, Supplier<Packet>>()
              .put(0x00, StatusRequest::new)
              .put(0x01, StatusPing::new)
              .getUnmodifiable()
      );

  private static final Registry<Packet> S2C_STATUS_STATE_REGISTRY =
      new ClassRegistry<>(
          new MapBuilder<Integer, Supplier<Packet>>()
              .put(0x00, StatusResponse::new)
              .put(0x01, StatusPing::new)
              .getUnmodifiable()
      );

  /* VERSION REGISTRIES */

  private static final VersionPacketRegistry SERVERBOUND_PACKET_REGISTRY =
      new VersionPacketRegistry(
          new MapBuilder<MinecraftVersion, PacketRegistry>()
              .put(MinecraftVersion.MINECRAFT_1_19_1, createRegistry1_19_1C2S())
              .getUnmodifiable()
      );

  private static final VersionPacketRegistry CLIENTBOUND_PACKET_REGISTRY =
      new VersionPacketRegistry(
          new MapBuilder<MinecraftVersion, PacketRegistry>()
              .put(MinecraftVersion.MINECRAFT_1_19_1, createRegistry1_19_1S2C())
              .getUnmodifiable()
      );

  /* 1.19.1 */

  private static PacketRegistry createRegistry1_19_1C2S() {
    return new PacketRegistry(
        new MapBuilder<ConnectionState, Registry<Packet>>()
            .put(ConnectionState.HANDSHAKE, HANDSHAKE_STATE_REGISTRY)
            .put(ConnectionState.STATUS, C2S_STATUS_STATE_REGISTRY)
            .put(ConnectionState.LOGIN, new ClassRegistry<>(
                    new MapBuilder<Integer, Supplier<Packet>>()
                        .put(0x00, LoginStart::new)
                        .put(0x01, EncryptionResponse::new)
                        .put(0x02, LoginPluginResponse::new)
                        .getUnmodifiable()
                )
            )
            .getUnmodifiable()
    );
  }

  private static PacketRegistry createRegistry1_19_1S2C() {
    return new PacketRegistry(
        new MapBuilder<ConnectionState, Registry<Packet>>()
            .put(ConnectionState.STATUS, S2C_STATUS_STATE_REGISTRY)
            .put(ConnectionState.LOGIN, new ClassRegistry<>(
                    new MapBuilder<Integer, Supplier<Packet>>()
                        .put(0x00, LoginDisconnect::new)
                        .put(0x01, EncryptionRequest::new)
                        .put(0x02, LoginSuccess::new)
                        .put(0x03, SetCompression::new)
                        .put(0x04, LoginPluginRequest::new)
                        .getUnmodifiable()
                )
            )
            .put(ConnectionState.PLAY, new ClassRegistry<>(
                    new MapBuilder<Integer, Supplier<Packet>>()
                        .put(0x00, SpawnEntity::new)
                        .put(0x0B, ChangeDifficulty::new)
                        .put(0x16, PluginMessage::new)
                        .put(0x19, Disconnect::new)
                        .put(0x1A, EntityEvent::new)
                        .put(0x25, JoinGame::new)
                        .put(0x31, ServerPlayerAbilities::new)
                        .put(0x37, PlayerInfo::new)
                        .put(0x39, SynchronizePlayerPosition::new)
                        .put(0x3A, UpdateRecipeBook::new)
                        .put(0x4A, ServerSetHeldItem::new)
                        .put(0x4B, SetCenterChunk::new)
                        .put(0x4C, SetRenderDistance::new)
                        .put(0x5A, SetSimulationDistance::new)
                        .put(0x62, SystemChat::new)
                        .put(0x6A, UpdateRecipes::new)
                        .put(0x6B, UpdateTags::new)
                        .getUnmodifiable()
                )
            )
            .getUnmodifiable()
    );
  }

  /* GETTERS */

  public static VersionPacketRegistry getServerboundRegistry() {
    return SERVERBOUND_PACKET_REGISTRY;
  }

  public static VersionPacketRegistry getClientboundRegistry() {
    return CLIENTBOUND_PACKET_REGISTRY;
  }
}
