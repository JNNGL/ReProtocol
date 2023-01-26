package com.jnngl.reprotocol.data.particle;

import com.jnngl.reprotocol.data.BlockPos;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import java.util.Objects;

public class VibrationParticle extends AbstractParticle {

  private String positionSourceType;
  private BlockPos blockPosition;
  private int entityID;
  private float entityEyeHeight;
  private int ticks;

  public VibrationParticle(String positionSourceType, BlockPos blockPosition, int entityID, float entityEyeHeight, int ticks) {
    this.positionSourceType = positionSourceType;
    this.blockPosition = blockPosition;
    this.entityID = entityID;
    this.entityEyeHeight = entityEyeHeight;
    this.ticks = ticks;
  }

  public VibrationParticle(BlockPos blockPosition, int ticks) {
    this("minecraft:block", blockPosition, 0, 0, ticks);
  }

  public VibrationParticle(int entityID, float entityEyeHeight, int ticks) {
    this("minecraft:entity", null, entityID, entityEyeHeight, ticks);
  }

  public VibrationParticle() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    ProtocolUtils.writeString(buf, positionSourceType);
    switch (positionSourceType) {
      case "minecraft:block": {
        BlockPos.write(buf, blockPosition);
        break;
      }

      case "minecraft:entity": {
        ProtocolUtils.writeVarInt(buf, entityID);
        buf.writeFloat(entityEyeHeight);
        break;
      }
    }

    ProtocolUtils.writeVarInt(buf, ticks);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    positionSourceType = ProtocolUtils.readString(buf);
    switch (positionSourceType) {
      case "minecraft:block": {
        blockPosition = BlockPos.read(buf);
        break;
      }

      case "minecraft:entity": {
        entityID = ProtocolUtils.readVarInt(buf);
        entityEyeHeight = buf.readFloat();
        break;
      }
    }

    ticks = ProtocolUtils.readVarInt(buf);
  }

  public String getPositionSourceType() {
    return positionSourceType;
  }

  public void setPositionSourceType(String positionSourceType) {
    this.positionSourceType = positionSourceType;
  }

  public BlockPos getBlockPosition() {
    return blockPosition;
  }

  public void setBlockPosition(BlockPos blockPosition) {
    this.blockPosition = blockPosition;
  }

  public int getEntityID() {
    return entityID;
  }

  public void setEntityID(int entityID) {
    this.entityID = entityID;
  }

  public float getEntityEyeHeight() {
    return entityEyeHeight;
  }

  public void setEntityEyeHeight(float entityEyeHeight) {
    this.entityEyeHeight = entityEyeHeight;
  }

  public int getTicks() {
    return ticks;
  }

  public void setTicks(int ticks) {
    this.ticks = ticks;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    VibrationParticle that = (VibrationParticle) o;

    if (entityID != that.entityID) {
      return false;
    }
    if (Float.compare(that.entityEyeHeight, entityEyeHeight) != 0) {
      return false;
    }
    if (ticks != that.ticks) {
      return false;
    }
    if (!Objects.equals(positionSourceType, that.positionSourceType)) {
      return false;
    }
    return Objects.equals(blockPosition, that.blockPosition);
  }

  @Override
  public int hashCode() {
    int result = positionSourceType != null ? positionSourceType.hashCode() : 0;
    result = 31 * result + (blockPosition != null ? blockPosition.hashCode() : 0);
    result = 31 * result + entityID;
    result = 31 * result + (entityEyeHeight != 0.0f ? Float.floatToIntBits(entityEyeHeight) : 0);
    result = 31 * result + ticks;
    return result;
  }

  @Override
  public String toString() {
    return "VibrationParticle{" +
        "positionSourceType='" + positionSourceType + '\'' +
        ", blockPosition=" + blockPosition +
        ", entityID=" + entityID +
        ", entityEyeHeight=" + entityEyeHeight +
        ", ticks=" + ticks +
        '}';
  }
}
