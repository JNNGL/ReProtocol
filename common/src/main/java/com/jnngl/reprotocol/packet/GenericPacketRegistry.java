package com.jnngl.reprotocol.packet;

import com.jnngl.reprotocol.ConnectionState;
import com.jnngl.reprotocol.packet.handshake.Handshake;
import com.jnngl.reprotocol.packet.status.StatusPing;
import com.jnngl.reprotocol.packet.status.StatusRequest;
import com.jnngl.reprotocol.packet.status.StatusResponse;
import com.jnngl.reprotocol.registry.PacketRegistry;
import com.jnngl.reprotocol.registry.StatePacketRegistry;
import com.jnngl.reprotocol.registry.VersionPacketRegistry;
import com.jnngl.reprotocol.util.MinecraftVersion;

import java.util.Collections;
import java.util.HashMap;

public class GenericPacketRegistry {

  /*** COMMON STATE REGISTRIES ***/

  private static final StatePacketRegistry HANDSHAKE_STATE_REGISTRY =
      new StatePacketRegistry(Collections.singletonMap(0x00, Handshake::new));

  private static final StatePacketRegistry C2S_STATUS_STATE_REGISTRY =
      new StatePacketRegistry(new HashMap<>() {{
        put(0x00, StatusRequest::new);
        put(0x01, StatusPing::new);
      }});

  private static final StatePacketRegistry S2C_STATUS_STATE_REGISTRY =
      new StatePacketRegistry(new HashMap<>() {{
        put(0x00, StatusResponse::new);
        put(0x01, StatusPing::new);
      }});

  /*** VERSION REGISTRIES ***/

  private static final VersionPacketRegistry SERVERBOUND_PACKET_REGISTRY =
      new VersionPacketRegistry(new HashMap<>() {{
        put(MinecraftVersion.MINECRAFT_1_19_1, createRegistry1_19_1C2S());
      }});

  private static final VersionPacketRegistry CLIENTBOUND_PACKET_REGISTRY =
      new VersionPacketRegistry(new HashMap<>() {{
        put(MinecraftVersion.MINECRAFT_1_19_1, createRegistry1_19_1S2C());
      }});

  /*** 1.19.1 ***/

  private static PacketRegistry createRegistry1_19_1C2S() {
    return new PacketRegistry(new HashMap<>() {{
      put(ConnectionState.HANDSHAKE, HANDSHAKE_STATE_REGISTRY);
      put(ConnectionState.STATUS, C2S_STATUS_STATE_REGISTRY);
    }});
  }

  private static PacketRegistry createRegistry1_19_1S2C() {
    return new PacketRegistry(new HashMap<>() {{
      put(ConnectionState.STATUS, S2C_STATUS_STATE_REGISTRY);
    }});
  }

  /*** GETTERS ***/

  public static VersionPacketRegistry getServerboundRegistry() {
    return SERVERBOUND_PACKET_REGISTRY;
  }

  public static VersionPacketRegistry getClientboundRegistry() {
    return CLIENTBOUND_PACKET_REGISTRY;
  }
}
