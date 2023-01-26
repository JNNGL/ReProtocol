package com.jnngl.reprotocol.data.particle;

import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class DustColorTransition extends AbstractParticle {

  private float fromRed;
  private float fromGreen;
  private float fromBlue;
  private float scale;
  private float toRed;
  private float toGreen;
  private float toBlue;

  public DustColorTransition(float fromRed, float fromGreen, float fromBlue, float scale, float toRed, float toGreen, float toBlue) {
    this.fromRed = fromRed;
    this.fromGreen = fromGreen;
    this.fromBlue = fromBlue;
    this.scale = scale;
    this.toRed = toRed;
    this.toGreen = toGreen;
    this.toBlue = toBlue;
  }

  public DustColorTransition() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    buf.writeFloat(fromRed);
    buf.writeFloat(fromGreen);
    buf.writeFloat(fromBlue);
    buf.writeFloat(scale);
    buf.writeFloat(toRed);
    buf.writeFloat(toGreen);
    buf.writeFloat(toBlue);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    fromRed = buf.readFloat();
    fromGreen = buf.readFloat();
    fromBlue = buf.readFloat();
    scale = buf.readFloat();
    toRed = buf.readFloat();
    toGreen = buf.readFloat();
    toBlue = buf.readFloat();
  }

  public float getFromRed() {
    return fromRed;
  }

  public void setFromRed(float fromRed) {
    this.fromRed = fromRed;
  }

  public float getFromGreen() {
    return fromGreen;
  }

  public void setFromGreen(float fromGreen) {
    this.fromGreen = fromGreen;
  }

  public float getFromBlue() {
    return fromBlue;
  }

  public void setFromBlue(float fromBlue) {
    this.fromBlue = fromBlue;
  }

  public float getScale() {
    return scale;
  }

  public void setScale(float scale) {
    this.scale = scale;
  }

  public float getToRed() {
    return toRed;
  }

  public void setToRed(float toRed) {
    this.toRed = toRed;
  }

  public float getToGreen() {
    return toGreen;
  }

  public void setToGreen(float toGreen) {
    this.toGreen = toGreen;
  }

  public float getToBlue() {
    return toBlue;
  }

  public void setToBlue(float toBlue) {
    this.toBlue = toBlue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DustColorTransition that = (DustColorTransition) o;

    if (Float.compare(that.fromRed, fromRed) != 0) {
      return false;
    }
    if (Float.compare(that.fromGreen, fromGreen) != 0) {
      return false;
    }
    if (Float.compare(that.fromBlue, fromBlue) != 0) {
      return false;
    }
    if (Float.compare(that.scale, scale) != 0) {
      return false;
    }
    if (Float.compare(that.toRed, toRed) != 0) {
      return false;
    }
    if (Float.compare(that.toGreen, toGreen) != 0) {
      return false;
    }
    return Float.compare(that.toBlue, toBlue) == 0;
  }

  @Override
  public int hashCode() {
    int result = (fromRed != 0.0f ? Float.floatToIntBits(fromRed) : 0);
    result = 31 * result + (fromGreen != 0.0f ? Float.floatToIntBits(fromGreen) : 0);
    result = 31 * result + (fromBlue != 0.0f ? Float.floatToIntBits(fromBlue) : 0);
    result = 31 * result + (scale != 0.0f ? Float.floatToIntBits(scale) : 0);
    result = 31 * result + (toRed != 0.0f ? Float.floatToIntBits(toRed) : 0);
    result = 31 * result + (toGreen != 0.0f ? Float.floatToIntBits(toGreen) : 0);
    result = 31 * result + (toBlue != 0.0f ? Float.floatToIntBits(toBlue) : 0);
    return result;
  }

  @Override
  public String toString() {
    return "DustColorTransition{" +
        "fromRed=" + fromRed +
        ", fromGreen=" + fromGreen +
        ", fromBlue=" + fromBlue +
        ", scale=" + scale +
        ", toRed=" + toRed +
        ", toGreen=" + toGreen +
        ", toBlue=" + toBlue +
        '}';
  }
}
