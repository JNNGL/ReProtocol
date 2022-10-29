package com.jnngl.reprotocol.data.nbt;

import io.netty.buffer.ByteBuf;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TagCompound extends NbtTag implements Iterable<NbtTag> {

  private Map<String, NbtTag> value;

  public TagCompound(String name, Set<NbtTag> value) {
    super(name);
    setValue(value);
  }

  public TagCompound(String name) {
    super(name);
  }

  @Override
  public int getTypeID() {
    return 10;
  }

  @Override
  public void encode(ByteBuf buf) {
    if (value != null) {
      value.values().forEach(tag -> Nbt.write(buf, tag));
    }

    buf.writeByte(0);
  }

  @Override
  public void decode(ByteBuf buf) {
    value = new LinkedHashMap<>();

    NbtTag tag;
    while ((tag = Nbt.read(buf)) != null) {
      value.put(tag.getName(), tag);
    }
  }

  public Map<String, NbtTag> getValue() {
    return value;
  }

  public NbtTag get(String key) {
    return value.get(key);
  }

  public TagByte getByte(String key) {
    return (TagByte) get(key);
  }

  public TagByteArray getByteArray(String key) {
    return (TagByteArray) get(key);
  }

  public TagCompound getCompound(String key) {
    return (TagCompound) get(key);
  }

  public TagDouble getDouble(String key) {
    return (TagDouble) get(key);
  }

  public TagFloat getFloat(String key) {
    return (TagFloat) get(key);
  }

  public TagInt getInt(String key) {
    return (TagInt) get(key);
  }

  public TagIntArray getIntArray(String key) {
    return (TagIntArray) get(key);
  }

  @SuppressWarnings("unchecked")
  public <T extends NbtTag> TagList<T> getList(String key) {
    return (TagList<T>) get(key);
  }

  public TagLong getLong(String key) {
    return (TagLong) get(key);
  }

  public TagLongArray getLongArray(String key) {
    return (TagLongArray) get(key);
  }

  public TagShort getShort(String key) {
    return (TagShort) get(key);
  }

  public TagString getString(String key) {
    return (TagString) get(key);
  }

  public void setValue(Set<NbtTag> value) {
    this.value = value.stream().collect(Collectors.toMap(NbtTag::getName, Function.identity()));
  }

  public void set(NbtTag tag) {
    value.put(tag.getName(), tag);
  }

  @Override
  public String toString() {
    String[] values = value.values().stream().map(NbtTag::toString).toArray(String[]::new);
    String valueString = String.join("\n", values);
    String withName = "Compound('" + getName() + "'): \n" + valueString;
    return withName.replace("\n", "\n  ");
  }

  @Override
  public Iterator<NbtTag> iterator() {
    return value.values().iterator();
  }
}
