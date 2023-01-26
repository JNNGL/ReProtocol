package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.data.GameProfile;
import com.jnngl.reprotocol.data.PlayerListData;
import com.jnngl.reprotocol.data.SignatureData;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

public class PlayerInfo implements Packet {

  public enum Action {
    ADD_PLAYER {
      @Override
      public void encode(MinecraftVersion version, ByteBuf buf, PlayerListData data) {
        GameProfile.write(buf, data.getGameProfile());
        ProtocolUtils.writeVarInt(buf, data.getGamemode());
        ProtocolUtils.writeVarInt(buf, data.getLatency());

        if (data.getDisplayName() != null) {
          buf.writeBoolean(true);
          ProtocolUtils.writeString(buf, data.getDisplayName());
        } else {
          buf.writeBoolean(false);
        }

        if (data.getSignatureData() != null) {
          buf.writeBoolean(true);
          SignatureData.write(buf, data.getSignatureData());
        } else {
          buf.writeBoolean(false);
        }
      }

      @Override
      public PlayerListData decode(MinecraftVersion version, ByteBuf buf) {
        return new PlayerListData(
            GameProfile.read(buf),
            ProtocolUtils.readVarInt(buf),
            ProtocolUtils.readVarInt(buf),
            buf.readBoolean() ? ProtocolUtils.readString(buf) : null,
            buf.readBoolean() ? SignatureData.read(buf) : null
        );
      }
    },
    UPDATE_GAMEMODE {
      @Override
      public void encode(MinecraftVersion version, ByteBuf buf, PlayerListData data) {
        ProtocolUtils.writeUUID(buf, data.getGameProfile().getUUID());
        ProtocolUtils.writeVarInt(buf, data.getGamemode());
      }

      @Override
      public PlayerListData decode(MinecraftVersion version, ByteBuf buf) {
        return new PlayerListData(
            new GameProfile(ProtocolUtils.readUUID(buf), null, null),
            ProtocolUtils.readVarInt(buf),
            0, null, null
        );
      }
    },
    UPDATE_LATENCY {
      @Override
      public void encode(MinecraftVersion version, ByteBuf buf, PlayerListData data) {
        ProtocolUtils.writeUUID(buf, data.getGameProfile().getUUID());
        ProtocolUtils.writeVarInt(buf, data.getLatency());
      }

      @Override
      public PlayerListData decode(MinecraftVersion version, ByteBuf buf) {
        return new PlayerListData(
            new GameProfile(ProtocolUtils.readUUID(buf), null, null),
            0,
            ProtocolUtils.readVarInt(buf),
            null, null
        );
      }
    },
    UPDATE_DISPLAY_NAME {
      @Override
      public void encode(MinecraftVersion version, ByteBuf buf, PlayerListData data) {
        ProtocolUtils.writeUUID(buf, data.getGameProfile().getUUID());

        if (data.getDisplayName() != null) {
          buf.writeBoolean(true);
          ProtocolUtils.writeString(buf, data.getDisplayName());
        } else {
          buf.writeBoolean(false);
        }
      }

      @Override
      public PlayerListData decode(MinecraftVersion version, ByteBuf buf) {
        return new PlayerListData(
            new GameProfile(ProtocolUtils.readUUID(buf), null, null),
            0, 0,
            buf.readBoolean() ? ProtocolUtils.readString(buf) : null,
            null
        );
      }
    },
    REMOVE_PLAYER {
      @Override
      public void encode(MinecraftVersion version, ByteBuf buf, PlayerListData data) {
        ProtocolUtils.writeUUID(buf, data.getGameProfile().getUUID());
      }

      @Override
      public PlayerListData decode(MinecraftVersion version, ByteBuf buf) {
        return new PlayerListData(
            new GameProfile(ProtocolUtils.readUUID(buf), null, null),
            0, 0, null, null
        );
      }
    };

    public abstract void encode(MinecraftVersion version, ByteBuf buf, PlayerListData data);
    public abstract PlayerListData decode(MinecraftVersion version, ByteBuf buf);
  }

  private Action action;
  private List<PlayerListData> players;

  public PlayerInfo(Action action, List<PlayerListData> players) {
    this.action = action;
    this.players = players;
  }

  public PlayerInfo() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeVarInt(buf, action.ordinal());
    players.forEach(player -> action.encode(version, buf, player));
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    action = Action.values()[ProtocolUtils.readVarInt(buf)];
    players = new ArrayList<>();
    for (int i = 0; i < ProtocolUtils.readVarInt(buf); i++) {
      players.add(action.decode(version, buf));
    }
  }

  public Action getAction() {
    return action;
  }

  public List<PlayerListData> getPlayers() {
    return players;
  }

  @Override
  public String toString() {
    return "PlayerInfo{" +
        "action=" + action +
        ", players=" + players +
        '}';
  }
}
