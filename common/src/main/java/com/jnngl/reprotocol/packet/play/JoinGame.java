package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.data.GlobalBlockPos;
import com.jnngl.reprotocol.data.nbt.Nbt;
import com.jnngl.reprotocol.data.codec.RegistryCodec;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.HashSet;
import java.util.Set;

public class JoinGame implements Packet {

  private int entityID;
  private boolean isHardcore;
  private byte gamemode;
  private byte previousGamemode;
  private Set<String> dimensionNames;
  private RegistryCodec registryCodec;
  private String dimensionType;
  private String dimensionName;
  private long hashedSeed;
  private int maxPlayers;
  private int viewDistance;
  private int simulationDistance;
  private boolean reducedDebugInfo;
  private boolean enableRespawnScreen;
  private boolean isDebug;
  private boolean isFlat;
  private GlobalBlockPos deathLocation;

  public JoinGame(int entityID, boolean isHardcore, byte gamemode, byte previousGamemode, Set<String> dimensionNames,
                  RegistryCodec registryCodec, String dimensionType, String dimensionName, long hashedSeed,
                  int maxPlayers, int viewDistance, int simulationDistance, boolean reducedDebugInfo,
                  boolean enableRespawnScreen, boolean isDebug, boolean isFlat, GlobalBlockPos deathLocation) {
    this.entityID = entityID;
    this.isHardcore = isHardcore;
    this.gamemode = gamemode;
    this.previousGamemode = previousGamemode;
    this.dimensionNames = dimensionNames;
    this.registryCodec = registryCodec;
    this.dimensionType = dimensionType;
    this.dimensionName = dimensionName;
    this.hashedSeed = hashedSeed;
    this.maxPlayers = maxPlayers;
    this.viewDistance = viewDistance;
    this.simulationDistance = simulationDistance;
    this.reducedDebugInfo = reducedDebugInfo;
    this.enableRespawnScreen = enableRespawnScreen;
    this.isDebug = isDebug;
    this.isFlat = isFlat;
    this.deathLocation = deathLocation;
  }

  public JoinGame() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    buf.writeInt(entityID);
    buf.writeBoolean(isHardcore);
    buf.writeByte(gamemode);
    buf.writeByte(previousGamemode);
    ProtocolUtils.writeVarInt(buf, dimensionNames.size());
    dimensionNames.forEach(dimensionName -> ProtocolUtils.writeString(buf, dimensionName));
    Nbt.write(buf, registryCodec.toNBT(version));
    ProtocolUtils.writeString(buf, dimensionType);
    ProtocolUtils.writeString(buf, dimensionName);
    buf.writeLong(hashedSeed);
    ProtocolUtils.writeVarInt(buf, maxPlayers);
    ProtocolUtils.writeVarInt(buf, viewDistance);
    ProtocolUtils.writeVarInt(buf, simulationDistance);
    buf.writeBoolean(reducedDebugInfo);
    buf.writeBoolean(enableRespawnScreen);
    buf.writeBoolean(isDebug);
    buf.writeBoolean(isFlat);
    if (deathLocation != null) {
      buf.writeBoolean(true);
      GlobalBlockPos.write(buf, deathLocation);
    } else {
      buf.writeBoolean(false);
    }
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    entityID = buf.readInt();
    isHardcore = buf.readBoolean();
    gamemode = buf.readByte();
    previousGamemode = buf.readByte();

    dimensionNames = new HashSet<>();
    for (int i = 0; i < ProtocolUtils.readVarInt(buf); i++) {
      dimensionNames.add(ProtocolUtils.readString(buf));
    }

    registryCodec = RegistryCodec.fromNBT(Nbt.read(buf), version);
    dimensionType = ProtocolUtils.readString(buf);
    dimensionName = ProtocolUtils.readString(buf);
    hashedSeed = buf.readLong();
    maxPlayers = ProtocolUtils.readVarInt(buf);
    viewDistance = ProtocolUtils.readVarInt(buf);
    simulationDistance = ProtocolUtils.readVarInt(buf);
    reducedDebugInfo = buf.readBoolean();
    enableRespawnScreen = buf.readBoolean();
    isDebug = buf.readBoolean();
    isFlat = buf.readBoolean();

    if (buf.readBoolean()) {
      deathLocation = GlobalBlockPos.read(buf);
    } else {
      deathLocation = null;
    }
  }

  public int getEntityID() {
    return entityID;
  }

  public boolean isHardcore() {
    return isHardcore;
  }

  public byte getGamemode() {
    return gamemode;
  }

  public byte getPreviousGamemode() {
    return previousGamemode;
  }

  public Set<String> getDimensionNames() {
    return dimensionNames;
  }

  public RegistryCodec getRegistryCodec() {
    return registryCodec;
  }

  public String getDimensionType() {
    return dimensionType;
  }

  public String getDimensionName() {
    return dimensionName;
  }

  public long getHashedSeed() {
    return hashedSeed;
  }

  public int getMaxPlayers() {
    return maxPlayers;
  }

  public int getViewDistance() {
    return viewDistance;
  }

  public int getSimulationDistance() {
    return simulationDistance;
  }

  public boolean isReducedDebugInfo() {
    return reducedDebugInfo;
  }

  public boolean isEnableRespawnScreen() {
    return enableRespawnScreen;
  }

  public boolean isDebug() {
    return isDebug;
  }

  public boolean isFlat() {
    return isFlat;
  }

  public GlobalBlockPos getDeathLocation() {
    return deathLocation;
  }

  @Override
  public String toString() {
    return "JoinGame{" +
        "entityID=" + entityID +
        ", isHardcore=" + isHardcore +
        ", gamemode=" + gamemode +
        ", previousGamemode=" + previousGamemode +
        ", dimensionNames=" + dimensionNames +
        ", registryCodec=" + registryCodec +
        ", dimensionType='" + dimensionType + '\'' +
        ", dimensionName='" + dimensionName + '\'' +
        ", hashedSeed=" + hashedSeed +
        ", maxPlayers=" + maxPlayers +
        ", viewDistance=" + viewDistance +
        ", simulationDistance=" + simulationDistance +
        ", reducedDebugInfo=" + reducedDebugInfo +
        ", enableRespawnScreen=" + enableRespawnScreen +
        ", isDebug=" + isDebug +
        ", isFlat=" + isFlat +
        ", deathLocation=" + deathLocation +
        '}';
  }
}
