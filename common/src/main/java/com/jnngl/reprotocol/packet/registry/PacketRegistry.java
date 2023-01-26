package com.jnngl.reprotocol.packet.registry;

import com.jnngl.reprotocol.ConnectionState;
import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.data.registry.Registry;
import com.jnngl.reprotocol.util.MapBuilder;

import java.util.Map;

public class PacketRegistry {

  private final Map<ConnectionState, Registry<Packet>> stateRegistries;

  public PacketRegistry(Map<ConnectionState, Registry<Packet>> stateRegistries) {
    this.stateRegistries = stateRegistries;
  }

  public PacketRegistry(Registry<Packet> handshake, Registry<Packet> status,
                        Registry<Packet> login, Registry<Packet> play) {
    this(
        new MapBuilder<ConnectionState, Registry<Packet>>()
            .put(ConnectionState.HANDSHAKE, handshake)
            .put(ConnectionState.STATUS, status)
            .put(ConnectionState.LOGIN, login)
            .put(ConnectionState.PLAY, play)
            .getUnmodifiable()
    );
  }

  public Map<ConnectionState, Registry<Packet>> getStateRegistries() {
    return stateRegistries;
  }

  public Registry<Packet> getHandshakeRegistry() {
    return stateRegistries.get(ConnectionState.HANDSHAKE);
  }

  public Registry<Packet> getStatusRegistry() {
    return stateRegistries.get(ConnectionState.STATUS);
  }

  public Registry<Packet> getLoginRegistry() {
    return stateRegistries.get(ConnectionState.LOGIN);
  }

  public Registry<Packet> getPlayRegistry() {
    return stateRegistries.get(ConnectionState.PLAY);
  }

  public Registry<Packet> getStateRegistry(ConnectionState state) {
    return stateRegistries.get(state);
  }
}
