package com.jnngl.reprotocol;

import com.jnngl.reprotocol.util.MinecraftVersion;

public class ConnectionData {

  private ConnectionState state = ConnectionState.HANDSHAKE;
  private MinecraftVersion version = MinecraftVersion.MAXIMUM_VERSION;

  public ConnectionState getState() {
    return state;
  }

  public void setState(ConnectionState state) {
    this.state = state;
  }

  public MinecraftVersion getVersion() {
    return version;
  }

  public void setVersion(MinecraftVersion version) {
    this.version = version;
  }
}
