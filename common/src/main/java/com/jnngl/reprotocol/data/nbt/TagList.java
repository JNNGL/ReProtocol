package com.jnngl.reprotocol.data.nbt;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TagList<T extends NbtTag> extends NbtTag {

  private List<T> value;

  public TagList(String name, List<T> value) {
    super(name);
    this.value = value;
  }

  public TagList(String name) {
    super(name);
  }

  @Override
  public int getTypeID() {
    return 9;
  }

  @Override
  public void encode(ByteBuf buf) {
    if (value == null || value.isEmpty()) {
      buf.writeByte(0);
      buf.writeInt(0);
    } else {
      buf.writeByte(value.get(0).getTypeID());
      buf.writeInt(value.size());
      value.forEach(tag -> tag.encode(buf));
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public void decode(ByteBuf buf) {
    int id = buf.readByte();
    int length = buf.readInt();

    value = new ArrayList<>();
    if (length > 0) {
      Function<String, NbtTag> ctor = Nbt.getConstructor(id);

      for (int i = 0; i < length; i++) {
        T tag = (T) ctor.apply("");
        tag.decode(buf);
        value.add(tag);
      }
    }
  }

  public List<T> getValue() {
    return value;
  }

  public void setValue(List<T> value) {
    this.value = value;
  }

  @Override
  public String toString() {
    String[] values = value.stream().map(NbtTag::toString).toArray(String[]::new);
    String valueString = String.join("\n", values);
    String withName = "Compound('" + getName() + "'): \n" + valueString;
    return withName.replace("\n", "\n  ");
  }
}
