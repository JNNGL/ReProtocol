package com.jnngl.reprotocol.remapper;

import com.jnngl.reprotocol.Packet;

public class RemappedPacket {

  private final int id;
  private final Packet packet;

  public RemappedPacket(int id, Packet packet) {
    this.id = id;
    this.packet = packet;
  }

  public int getID() {
    return id;
  }

  public Packet getPacket() {
    return packet;
  }
}
