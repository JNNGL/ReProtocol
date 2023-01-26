package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class SetSimulationDistance implements Packet {

  private int simulationDistance;

  public SetSimulationDistance(int simulationDistance) {
    this.simulationDistance = simulationDistance;
  }

  public SetSimulationDistance() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeVarInt(buf, simulationDistance);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    simulationDistance = ProtocolUtils.readVarInt(buf);
  }

  public int getSimulationDistance() {
    return simulationDistance;
  }

  @Override
  public String toString() {
    return "SetSimulationDistance{" +
        "simulationDistance=" + simulationDistance +
        '}';
  }
}
