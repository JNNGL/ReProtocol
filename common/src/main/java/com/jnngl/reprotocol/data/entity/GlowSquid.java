package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class GlowSquid extends Squid {

  private int darkTicksRemaining = 0;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(0, new VarintMetadataItem(darkTicksRemaining));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    darkTicksRemaining = metadata.read(0);
  }

  public int getDarkTicksRemaining() {
    return darkTicksRemaining;
  }

  public void setDarkTicksRemaining(int darkTicksRemaining) {
    this.darkTicksRemaining = darkTicksRemaining;
  }
}
