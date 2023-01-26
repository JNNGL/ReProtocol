package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class IronGolem extends AbstractGolem {

  private boolean playerCreated = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write((byte) 0, new ByteMetadataItem((byte) (playerCreated ? 0x01 : 0x00)));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    playerCreated = metadata.read((byte) 0) != 0;
  }

  public boolean isPlayerCreated() {
    return playerCreated;
  }

  public void setPlayerCreated(boolean playerCreated) {
    this.playerCreated = playerCreated;
  }
}
