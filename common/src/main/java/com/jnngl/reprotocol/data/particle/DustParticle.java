package com.jnngl.reprotocol.data.particle;

import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class DustParticle extends AbstractParticle {

  private float red;
  private float green;
  private float blue;
  private float scale;

  public DustParticle(float red, float green, float blue, float scale) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.scale = scale;
  }

  public DustParticle() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    buf.writeFloat(red);
    buf.writeFloat(green);
    buf.writeFloat(blue);
    buf.writeFloat(scale);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    red = buf.readFloat();
    green = buf.readFloat();
    blue = buf.readFloat();
    scale = buf.readFloat();
  }

  public float getRed() {
    return red;
  }

  public void setRed(float red) {
    this.red = red;
  }

  public float getGreen() {
    return green;
  }

  public void setGreen(float green) {
    this.green = green;
  }

  public float getBlue() {
    return blue;
  }

  public void setBlue(float blue) {
    this.blue = blue;
  }

  public float getScale() {
    return scale;
  }

  public void setScale(float scale) {
    this.scale = scale;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DustParticle that = (DustParticle) o;

    if (Float.compare(that.red, red) != 0) {
      return false;
    }
    if (Float.compare(that.green, green) != 0) {
      return false;
    }
    if (Float.compare(that.blue, blue) != 0) {
      return false;
    }
    return Float.compare(that.scale, scale) == 0;
  }

  @Override
  public int hashCode() {
    int result = (red != 0.0f ? Float.floatToIntBits(red) : 0);
    result = 31 * result + (green != 0.0f ? Float.floatToIntBits(green) : 0);
    result = 31 * result + (blue != 0.0f ? Float.floatToIntBits(blue) : 0);
    result = 31 * result + (scale != 0.0f ? Float.floatToIntBits(scale) : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Dust{" +
        "red=" + red +
        ", green=" + green +
        ", blue=" + blue +
        ", scale=" + scale +
        '}';
  }
}
