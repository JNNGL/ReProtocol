package com.jnngl.reprotocol.packet.play;

import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class ChangeDifficulty implements Packet {

  private byte difficulty;
  private boolean difficultyLocked;

  public ChangeDifficulty(byte difficulty, boolean difficultyLocked) {
    this.difficulty = difficulty;
    this.difficultyLocked = difficultyLocked;
  }

  public ChangeDifficulty() {

  }

  @Override
  public void encode(MinecraftVersion version, ByteBuf buf) {
    buf.writeByte(difficulty);
    buf.writeBoolean(difficultyLocked);
  }

  @Override
  public void decode(MinecraftVersion version, ByteBuf buf) {
    difficulty = buf.readByte();
    difficultyLocked = buf.readBoolean();
  }

  public byte getDifficulty() {
    return difficulty;
  }

  public boolean isDifficultyLocked() {
    return difficultyLocked;
  }

  @Override
  public String toString() {
    return "ChangeDifficulty{" +
        "difficulty=" + difficulty +
        ", difficultyLocked=" + difficultyLocked +
        '}';
  }
}
