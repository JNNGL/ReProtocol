package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.VillagerData;
import com.jnngl.reprotocol.data.VillagerProfession;
import com.jnngl.reprotocol.data.VillagerType;
import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VillagerMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class ZombieVillager extends Zombie {

  private static final VillagerData VILLAGER_DATA = new VillagerData(VillagerType.PLAINS, VillagerProfession.NONE, 1);

  private boolean convertingID = false;
  private VillagerData villagerData = VILLAGER_DATA;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(convertingID));
    metadata.write(VILLAGER_DATA, new VillagerMetadataItem(villagerData));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    convertingID = metadata.read(false);
    villagerData = metadata.read(VILLAGER_DATA);
  }

  public boolean isConvertingID() {
    return convertingID;
  }

  public void setConvertingID(boolean convertingID) {
    this.convertingID = convertingID;
  }

  public VillagerData getVillagerData() {
    return villagerData;
  }

  public void setVillagerData(VillagerData villagerData) {
    this.villagerData = villagerData;
  }
}
