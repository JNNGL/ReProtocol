package com.jnngl.reprotocol.nbt;

import com.jnngl.reprotocol.data.nbt.Nbt;
import com.jnngl.reprotocol.data.nbt.NbtTag;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;
import java.util.zip.GZIPInputStream;

class NbtTest {

  static ByteBuf readFile(String filename, Function<InputStream, InputStream> inputStreamFunction)
      throws IOException {
    try (InputStream inputStream = inputStreamFunction.apply(
        NbtTest.class.getResourceAsStream("/" + filename))) {
      ByteBuf buf = Unpooled.buffer();
      while (inputStream.available() > 0) {
        buf.writeBytes(inputStream, inputStream.available());
      }
      return buf;
    }
  }

  @Test
  void helloWorldNbt() throws IOException {
    ByteBuf input = readFile("hello_world.nbt", Function.identity());

    NbtTag nbt = Nbt.read(input);
    System.out.println(nbt);

    ByteBuf output = Unpooled.buffer();
    Nbt.write(output, nbt);

    input.resetReaderIndex();

    Assertions.assertEquals(0, input.compareTo(output));
  }

  @Test
  void bigtestNbt() throws IOException {
    ByteBuf input = readFile("bigtest.nbt", inputStream -> {
      try {
        return new GZIPInputStream(inputStream);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });

    NbtTag nbt = Nbt.read(input);
    System.out.println(nbt);

    ByteBuf output = Unpooled.buffer();
    Nbt.write(output, nbt);

    input.resetReaderIndex();

    Assertions.assertEquals(0, input.compareTo(output));
  }
}
