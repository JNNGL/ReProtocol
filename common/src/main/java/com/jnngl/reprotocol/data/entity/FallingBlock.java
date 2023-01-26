package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.BlockPos;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.PositionMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class FallingBlock extends Entity {

  private BlockPos spawnPosition = BlockPos.ZERO;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(BlockPos.ZERO, new PositionMetadataItem(spawnPosition));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    spawnPosition = metadata.read(BlockPos.ZERO);
  }

  public BlockPos getSpawnPosition() {
    return spawnPosition;
  }

  public void setSpawnPosition(BlockPos spawnPosition) {
    this.spawnPosition = spawnPosition;
  }
}
