package com.jnngl.reprotocol.data;

import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GameProfile {

  public static class Property {

    private final String name;
    private final String value;
    private final String signature;

    public Property(String name, String value, String signature) {
      this.name = name;
      this.value = value;
      this.signature = signature;
    }

    public Property(String name, String value) {
      this(name, value, null);
    }

    public String getName() {
      return name;
    }

    public String getValue() {
      return value;
    }

    public String getSignature() {
      return signature;
    }
  }

  private final UUID uuid;
  private final String username;
  private final Set<Property> properties;

  public GameProfile(UUID uuid, String username, Set<Property> properties) {
    this.uuid = uuid;
    this.username = username;
    this.properties = properties;
  }

  public UUID getUUID() {
    return uuid;
  }

  public String getUsername() {
    return username;
  }

  public Set<Property> getProperties() {
    return properties;
  }

  public static GameProfile read(ByteBuf buf) {
    UUID uuid = ProtocolUtils.readUUID(buf);
    String username = ProtocolUtils.readString(buf);
    Set<Property> properties = new HashSet<>();
    for (int i = 0; i < ProtocolUtils.readVarInt(buf); i++) {
      properties.add(new Property(
          ProtocolUtils.readString(buf),
          ProtocolUtils.readString(buf),
          buf.readBoolean() ? ProtocolUtils.readString(buf) : null
      ));
    }

    return new GameProfile(uuid, username, Collections.unmodifiableSet(properties));
  }

  public static void write(ByteBuf buf, GameProfile gameProfile) {
    ProtocolUtils.writeUUID(buf, gameProfile.getUUID());
    ProtocolUtils.writeString(buf, gameProfile.getUsername());
    ProtocolUtils.writeVarInt(buf, gameProfile.getProperties().size());
    for (Property property : gameProfile.getProperties()) {
      ProtocolUtils.writeString(buf, property.getName());
      ProtocolUtils.writeString(buf, property.getValue());
      if (property.getSignature() != null) {
        buf.writeBoolean(true);
        ProtocolUtils.writeString(buf, property.getSignature());
      } else {
        buf.writeBoolean(false);
      }
    }
  }
}
