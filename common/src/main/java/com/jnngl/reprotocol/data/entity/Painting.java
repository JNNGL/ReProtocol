package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.PaintingVariant;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.PaintingVariantMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Painting extends Entity {

  private PaintingVariant paintingType = PaintingVariant.KEBAB;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(PaintingVariant.KEBAB, new PaintingVariantMetadataItem(paintingType));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    paintingType = metadata.read(PaintingVariant.KEBAB);
  }

  public PaintingVariant getPaintingType() {
    return paintingType;
  }

  public void setPaintingType(PaintingVariant paintingType) {
    this.paintingType = paintingType;
  }
}
