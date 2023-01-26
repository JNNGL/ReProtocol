package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.VillagerData;
import com.jnngl.reprotocol.data.VillagerProfession;
import com.jnngl.reprotocol.data.VillagerType;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VillagerMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Villager extends AbstractVillager {

  private static final VillagerData VILLAGER_DATA = new VillagerData(VillagerType.PLAINS, VillagerProfession.NONE, 1);

  private VillagerData villagerData = VILLAGER_DATA;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(VILLAGER_DATA, new VillagerMetadataItem(villagerData));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    villagerData = metadata.read(VILLAGER_DATA);
  }

  public VillagerData getVillagerData() {
    return villagerData;
  }

  public void setVillagerData(VillagerData villagerData) {
    this.villagerData = villagerData;
  }
}
