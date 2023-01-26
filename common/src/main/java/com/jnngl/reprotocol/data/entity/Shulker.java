package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.BlockPos;
import com.jnngl.reprotocol.data.Direction;
import com.jnngl.reprotocol.data.entity.metadata.ByteMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.DirectionMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.OptPositionMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class Shulker extends AbstractGolem {

  private Direction attachFace = Direction.DOWN;
  private BlockPos attachmentPosition = null;
  private byte shieldHeight = 0;
  private byte color = 10;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(Direction.DOWN, new DirectionMetadataItem(attachFace));
    metadata.write(null, new OptPositionMetadataItem(attachmentPosition));
    metadata.write((byte) 0, new ByteMetadataItem(shieldHeight));
    metadata.write((byte) 10, new ByteMetadataItem(color));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    attachFace = metadata.read(Direction.DOWN);
    attachmentPosition = metadata.read(null);
    shieldHeight = metadata.read((byte) 0);
    color = metadata.read((byte) 10);
  }

  public Direction getAttachFace() {
    return attachFace;
  }

  public void setAttachFace(Direction attachFace) {
    this.attachFace = attachFace;
  }

  public BlockPos getAttachmentPosition() {
    return attachmentPosition;
  }

  public void setAttachmentPosition(BlockPos attachmentPosition) {
    this.attachmentPosition = attachmentPosition;
  }

  public byte getShieldHeight() {
    return shieldHeight;
  }

  public void setShieldHeight(byte shieldHeight) {
    this.shieldHeight = shieldHeight;
  }

  public byte getColor() {
    return color;
  }

  public void setColor(byte color) {
    this.color = color;
  }
}
