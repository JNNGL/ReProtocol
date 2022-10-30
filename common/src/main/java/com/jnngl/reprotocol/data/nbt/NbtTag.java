package com.jnngl.reprotocol.data.nbt;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class NbtTag {

  private String name;

  public NbtTag(String name) {
    this.name = name;
  }

  public abstract int getTypeID();
  public abstract void encode(ByteBuf buf);
  public abstract void decode(ByteBuf buf);

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static TagByte of(String name, byte value) {
    return new TagByte(name, value);
  }

  public static TagByte of(String name, boolean value) {
    return new TagByte(name, (byte) (value ? 1 : 0));
  }

  public static TagByteArray of(String name, byte[] value) {
    return new TagByteArray(name, value);
  }

  public static TagCompound of(String name, Set<NbtTag> value) {
    return new TagCompound(name, value);
  }

  public static TagDouble of(String name, double value) {
    return new TagDouble(name, value);
  }

  public static TagFloat of(String name, float value) {
    return new TagFloat(name, value);
  }

  public static TagInt of(String name, int value) {
    return new TagInt(name, value);
  }

  public static TagIntArray of(String name, int[] value) {
    return new TagIntArray(name, value);
  }

  public static <T extends NbtTag> TagList<T> of(String name, List<T> value) {
    return new TagList<>(name, value);
  }

  public static <T extends NbtTag> TagList<T> of(String name, T[] value) {
    return new TagList<>(name, Arrays.asList(value));
  }

  public static TagLong of(String name, long value) {
    return new TagLong(name, value);
  }

  public static TagLongArray of(String name, long[] value) {
    return new TagLongArray(name, value);
  }

  public static TagShort of(String name, short value) {
    return new TagShort(name, value);
  }

  public static TagString of(String name, String value) {
    return new TagString(name, value);
  }

  public static <T extends NbtTag> TagList<T> emptyList(String name) {
    return new TagList<>(name, new ArrayList<>());
  }

  public static TagCompound emptyCompound(String name) {
    return new TagCompound(name, new LinkedHashSet<>());
  }
}
