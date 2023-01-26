package com.jnngl.reprotocol.data;

public class PlayerListData {

  private final GameProfile gameProfile;
  private final int gamemode;
  private final int latency;
  private final String displayName;
  private final SignatureData signatureData;

  public PlayerListData(GameProfile gameProfile, int gamemode, int latency, String displayName, SignatureData signatureData) {
    this.gameProfile = gameProfile;
    this.gamemode = gamemode;
    this.latency = latency;
    this.displayName = displayName;
    this.signatureData = signatureData;
  }

  public GameProfile getGameProfile() {
    return gameProfile;
  }

  public int getGamemode() {
    return gamemode;
  }

  public int getLatency() {
    return latency;
  }

  public String getDisplayName() {
    return displayName;
  }

  public SignatureData getSignatureData() {
    return signatureData;
  }
}
