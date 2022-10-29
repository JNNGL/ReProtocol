package com.jnngl.reprotocol.data.nbt;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Nbt {

  private static final List<Function<String, NbtTag>> CONSTRUCTORS =
      Collections.unmodifiableList(Arrays.asList(
          null, TagByte::new, TagShort::new, TagInt::new, TagLong::new, TagFloat::new, TagDouble::new,
          TagByteArray::new, TagString::new, TagList::new, TagCompound::new, TagIntArray::new, TagLongArray::new
      ));

  public static void write(ByteBuf buf, NbtTag nbtTag) {
    if (nbtTag == null) {
      buf.writeByte(0);
      return;
    }

    buf.writeByte(nbtTag.getTypeID());

    byte[] nameBytes = nbtTag.getName().getBytes(StandardCharsets.UTF_8);
    buf.writeShort(nameBytes.length);
    buf.writeBytes(nameBytes);

    nbtTag.encode(buf);
  }

  public static NbtTag read(ByteBuf buf) {
    int id = buf.readByte();

    Function<String, NbtTag> ctor = CONSTRUCTORS.get(id);
    if (ctor == null) {
      return null;
    }

    String name;

    {
      byte[] bytes = new byte[buf.readShort()];
      buf.readBytes(bytes);
      name = new String(bytes, StandardCharsets.UTF_8);
    }

    NbtTag nbtTag = ctor.apply(name);
    nbtTag.decode(buf);

    return nbtTag;
  }

  public static Function<String, NbtTag> getConstructor(int id) {
    return CONSTRUCTORS.get(id);
  }
}
