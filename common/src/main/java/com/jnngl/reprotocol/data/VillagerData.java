package com.jnngl.reprotocol.data;

import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class VillagerData {

  private final VillagerType type;
  private final VillagerProfession profession;
  private final int level;

  public VillagerData(VillagerType type, VillagerProfession profession, int level) {
    this.type = type;
    this.profession = profession;
    this.level = level;
  }

  public VillagerType getType() {
    return type;
  }

  public VillagerProfession getProfession() {
    return profession;
  }

  public int getLevel() {
    return level;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    VillagerData that = (VillagerData) o;

    if (level != that.level) {
      return false;
    }
    if (type != that.type) {
      return false;
    }
    return profession == that.profession;
  }

  @Override
  public int hashCode() {
    int result = type != null ? type.hashCode() : 0;
    result = 31 * result + (profession != null ? profession.hashCode() : 0);
    result = 31 * result + level;
    return result;
  }

  @Override
  public String toString() {
    return "VillagerData{" +
        "type=" + type +
        ", profession=" + profession +
        ", level=" + level +
        '}';
  }

  public static void write(ByteBuf buf, MinecraftVersion version, VillagerData data) {
    ProtocolUtils.writeVarInt(buf, VillagerType.REGISTRY.getRegistry(version).getID(data.getType()));
    ProtocolUtils.writeVarInt(buf, VillagerProfession.REGISTRY.getRegistry(version).getID(data.getProfession()));
    ProtocolUtils.writeVarInt(buf, data.getLevel());
  }

  public static VillagerData read(ByteBuf buf, MinecraftVersion version) {
    return new VillagerData(
        VillagerType.REGISTRY.getRegistry(version).get(ProtocolUtils.readVarInt(buf)),
        VillagerProfession.REGISTRY.getRegistry(version).get(ProtocolUtils.readVarInt(buf)),
        ProtocolUtils.readVarInt(buf)
    );
  }
}
