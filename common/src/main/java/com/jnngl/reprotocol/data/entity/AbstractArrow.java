package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class AbstractArrow extends Entity {

  private boolean critical = false;
  private boolean noclip = false;
  private byte piercingLevel = 0;

  private byte getFlags(MinecraftVersion version) {
    return (byte) ((critical ? 0x01 : 0x00) |
            (noclip ? 0x02 : 0x00));
  }

  private void setFlags(MinecraftVersion version, byte flags) {
    critical = (flags & 0x01) != 0;
    noclip = (flags & 0x02) != 0;
  }

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write((byte) 0, new ByteMetadataItem(getFlags(version)));
    metadata.write((byte) 0, new ByteMetadataItem(piercingLevel));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    setFlags(version, metadata.read((byte) 0));
    piercingLevel = metadata.read((byte) 0);
  }

  public boolean isCritical() {
    return critical;
  }

  public void setCritical(boolean critical) {
    this.critical = critical;
  }

  public boolean isNoclip() {
    return noclip;
  }

  public void setNoclip(boolean noclip) {
    this.noclip = noclip;
  }

  public byte getPiercingLevel() {
    return piercingLevel;
  }

  public void setPiercingLevel(byte piercingLevel) {
    this.piercingLevel = piercingLevel;
  }
}
