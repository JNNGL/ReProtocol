package com.jnngl.reprotocol.packet.login;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.data.GameProfile;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class LoginSuccess implements Packet {

  private GameProfile gameProfile;

  public LoginSuccess(GameProfile gameProfile) {
    this.gameProfile = gameProfile;
  }

  public LoginSuccess() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    GameProfile.write(buf, gameProfile);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    gameProfile = GameProfile.read(buf);
  }

  public GameProfile getGameProfile() {
    return gameProfile;
  }
}
