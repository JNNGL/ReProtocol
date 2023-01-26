package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.OptVarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Guardian extends Monster {

  private boolean retractingSpikes = false;
  private Integer targetEntity = null;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(false, new BooleanMetadataItem(retractingSpikes));
    metadata.write(null, new OptVarintMetadataItem(targetEntity));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    retractingSpikes = metadata.read(false);
    targetEntity = metadata.read(null);
  }

  public boolean isRetractingSpikes() {
    return retractingSpikes;
  }

  public void setRetractingSpikes(boolean retractingSpikes) {
    this.retractingSpikes = retractingSpikes;
  }

  public Integer getTargetEntity() {
    return targetEntity;
  }

  public void setTargetEntity(Integer targetEntity) {
    this.targetEntity = targetEntity;
  }
}
