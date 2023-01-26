package com.jnngl.reprotocol.data.datapack;

import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

public class Tag {

  private final String name;
  private final List<Integer> entries;

  public Tag(String name, List<Integer> entries) {
    this.name = name;
    this.entries = entries;
  }

  public String getName() {
    return name;
  }

  public List<Integer> getEntries() {
    return entries;
  }

  @Override
  public String toString() {
    return "Tag{" +
        "name='" + name + '\'' +
        ", entries=" + entries +
        '}';
  }

  public static void write(ByteBuf buf, MinecraftVersion version, Tag tag) {
    ProtocolUtils.writeString(buf, tag.getName());
    ProtocolUtils.writeVarInt(buf, tag.getEntries().size());
    tag.getEntries().forEach(entry -> ProtocolUtils.writeVarInt(buf, entry));
  }

  public static Tag read(ByteBuf buf, MinecraftVersion version) {
    String name = ProtocolUtils.readString(buf);
    List<Integer> entries = new ArrayList<>();
    for (int i = 0; i < ProtocolUtils.readVarInt(buf); i++) {
      entries.add(ProtocolUtils.readVarInt(buf));
    }

    return new Tag(name, entries);
  }
}
