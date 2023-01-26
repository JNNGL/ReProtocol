package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Creeper extends Monster {

  private int state = -1;
  private boolean charged = false;
  private boolean ignited = false;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(-1, new VarintMetadataItem(state));
    metadata.write(false, new BooleanMetadataItem(charged));
    metadata.write(false, new BooleanMetadataItem(ignited));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    state = metadata.read(-1);
    charged = metadata.read(false);
    ignited = metadata.read(false);
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public boolean isCharged() {
    return charged;
  }

  public void setCharged(boolean charged) {
    this.charged = charged;
  }

  public boolean isIgnited() {
    return ignited;
  }

  public void setIgnited(boolean ignited) {
    this.ignited = ignited;
  }
}
