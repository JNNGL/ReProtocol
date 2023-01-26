package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Panda extends Animal {

  private int breedTimer = 0;
  private int sneezeTimer = 0;
  private int eatTimer = 0;
  private byte mainGene = 0;
  private byte hiddenGene = 0;
  private boolean sneezing = false;
  private boolean rolling = false;
  private boolean sitting = false;
  private boolean onBack = false;

  private byte getFlags(MinecraftVersion version) {
    return (byte) ((sneezing ? 0x02 : 0x00) |
        (rolling ? 0x04 : 0x00) |
        (sitting ? 0x08 : 0x00) |
        (onBack ? 0x10 : 0x00));
  }

  private void setFlags(MinecraftVersion version, byte flags) {
    sneezing = (flags & 0x02) != 0;
    rolling = (flags & 0x04) != 0;
    sitting = (flags & 0x08) != 0;
    onBack = (flags & 0x10) != 0;
  }

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(0, new VarintMetadataItem(breedTimer));
    metadata.write(0, new VarintMetadataItem(sneezeTimer));
    metadata.write(0, new VarintMetadataItem(eatTimer));
    metadata.write((byte) 0, new ByteMetadataItem(mainGene));
    metadata.write((byte) 0, new ByteMetadataItem(hiddenGene));
    metadata.write((byte) 0, new ByteMetadataItem(getFlags(version)));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    breedTimer = metadata.read(0);
    sneezeTimer = metadata.read(0);
    eatTimer = metadata.read(0);
    mainGene = metadata.read((byte) 0);
    hiddenGene = metadata.read((byte) 0);
    setFlags(version, metadata.read((byte) 0));
  }

  public int getBreedTimer() {
    return breedTimer;
  }

  public void setBreedTimer(int breedTimer) {
    this.breedTimer = breedTimer;
  }

  public int getSneezeTimer() {
    return sneezeTimer;
  }

  public void setSneezeTimer(int sneezeTimer) {
    this.sneezeTimer = sneezeTimer;
  }

  public int getEatTimer() {
    return eatTimer;
  }

  public void setEatTimer(int eatTimer) {
    this.eatTimer = eatTimer;
  }

  public byte getMainGene() {
    return mainGene;
  }

  public void setMainGene(byte mainGene) {
    this.mainGene = mainGene;
  }

  public byte getHiddenGene() {
    return hiddenGene;
  }

  public void setHiddenGene(byte hiddenGene) {
    this.hiddenGene = hiddenGene;
  }

  public boolean isSneezing() {
    return sneezing;
  }

  public void setSneezing(boolean sneezing) {
    this.sneezing = sneezing;
  }

  public boolean isRolling() {
    return rolling;
  }

  public void setRolling(boolean rolling) {
    this.rolling = rolling;
  }

  public boolean isSitting() {
    return sitting;
  }

  public void setSitting(boolean sitting) {
    this.sitting = sitting;
  }

  public boolean isOnBack() {
    return onBack;
  }

  public void setOnBack(boolean onBack) {
    this.onBack = onBack;
  }
}
