package com.jnngl.reprotocol.packet.registry;

import com.jnngl.reprotocol.ConnectionState;
import com.jnngl.reprotocol.util.MapBuilder;

import java.util.Map;

public class PacketRegistry {

  private final Map<ConnectionState, StatePacketRegistry> stateRegistries;

  public PacketRegistry(Map<ConnectionState, StatePacketRegistry> stateRegistries) {
    this.stateRegistries = stateRegistries;
  }

  public PacketRegistry(StatePacketRegistry handshake, StatePacketRegistry status,
                        StatePacketRegistry login, StatePacketRegistry play) {
    this(
        new MapBuilder<ConnectionState, StatePacketRegistry>()
            .put(ConnectionState.HANDSHAKE, handshake)
            .put(ConnectionState.STATUS, status)
            .put(ConnectionState.LOGIN, login)
            .put(ConnectionState.PLAY, play)
            .getUnmodifiable()
    );
  }

  public Map<ConnectionState, StatePacketRegistry> getStateRegistries() {
    return stateRegistries;
  }

  public StatePacketRegistry getHandshakeRegistry() {
    return stateRegistries.get(ConnectionState.HANDSHAKE);
  }

  public StatePacketRegistry getStatusRegistry() {
    return stateRegistries.get(ConnectionState.STATUS);
  }

  public StatePacketRegistry getLoginRegistry() {
    return stateRegistries.get(ConnectionState.LOGIN);
  }

  public StatePacketRegistry getPlayRegistry() {
    return stateRegistries.get(ConnectionState.PLAY);
  }

  public StatePacketRegistry getStateRegistry(ConnectionState state) {
    return stateRegistries.get(state);
  }
}
