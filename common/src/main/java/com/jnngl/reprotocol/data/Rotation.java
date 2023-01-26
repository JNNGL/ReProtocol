package com.jnngl.reprotocol.data;

public class Rotation {

  public static final Rotation ZERO = new Rotation(0, 0, 0);

  private final float x;
  private final float y;
  private final float z;

  public Rotation(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public float getZ() {
    return z;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Rotation rotation = (Rotation) o;

    if (Float.compare(rotation.x, x) != 0) {
      return false;
    }
    if (Float.compare(rotation.y, y) != 0) {
      return false;
    }
    return Float.compare(rotation.z, z) == 0;
  }

  @Override
  public int hashCode() {
    int result = (x != 0.0f ? Float.floatToIntBits(x) : 0);
    result = 31 * result + (y != 0.0f ? Float.floatToIntBits(y) : 0);
    result = 31 * result + (z != 0.0f ? Float.floatToIntBits(z) : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Rotation{" +
        "x=" + x +
        ", y=" + y +
        ", z=" + z +
        '}';
  }
}
