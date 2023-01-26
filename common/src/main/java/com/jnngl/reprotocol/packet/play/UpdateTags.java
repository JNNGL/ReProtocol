package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.data.datapack.Tag;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateTags implements Packet {

  private Map<String, List<Tag>> tags;

  public UpdateTags(Map<String, List<Tag>> tags) {
    this.tags = tags;
  }

  public UpdateTags() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    ProtocolUtils.writeVarInt(buf, tags.size());
    tags.forEach((key, value) -> {
      ProtocolUtils.writeString(buf, key);
      ProtocolUtils.writeVarInt(buf, value.size());
      value.forEach(tag -> Tag.write(buf, version, tag));
    });
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    tags = new HashMap<>();
    for (int i = 0; i < ProtocolUtils.readVarInt(buf); i++) {
      String key = ProtocolUtils.readString(buf);
      List<Tag> value = new ArrayList<>();
      for (int j = 0; j < ProtocolUtils.readVarInt(buf); i++) {
        value.add(Tag.read(buf, version));
      }

      tags.put(key, value);
    }
  }

  public Map<String, List<Tag>> getTags() {
    return tags;
  }

  @Override
  public String toString() {
    return "UpdateTags{" +
        "tags=" + tags +
        '}';
  }
}
