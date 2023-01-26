package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.BlockPos;
import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.OptPositionMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class EndCrystal extends Entity {

  private BlockPos beamTarget = null;
  private boolean showBottom = true;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(null, new OptPositionMetadataItem(beamTarget));
    metadata.write(true, new BooleanMetadataItem(showBottom));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    beamTarget = metadata.read(null);
    showBottom = metadata.read(true);
  }

  public BlockPos getBeamTarget() {
    return beamTarget;
  }

  public void setBeamTarget(BlockPos beamTarget) {
    this.beamTarget = beamTarget;
  }

  public boolean isShowBottom() {
    return showBottom;
  }

  public void setShowBottom(boolean showBottom) {
    this.showBottom = showBottom;
  }
}
