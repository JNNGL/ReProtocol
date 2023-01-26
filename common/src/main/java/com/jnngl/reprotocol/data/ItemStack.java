package com.jnngl.reprotocol.data;

import com.jnngl.reprotocol.data.nbt.Nbt;
import com.jnngl.reprotocol.data.nbt.NbtTag;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;

public class ItemStack {

  public static ItemStack EMPTY = new ItemStack();

  private final boolean present;
  private final int itemID;
  private final int count;
  private final NbtTag nbt;

  public ItemStack(boolean present, int itemID, int count, NbtTag nbt) {
    this.present = present;
    this.itemID = itemID;
    this.count = count;
    this.nbt = nbt;
  }

  public ItemStack(int itemID, int count, NbtTag nbt) {
    this(true, itemID, count, nbt);
  }

  public ItemStack() {
    this(false, 0, 0, null);
  }

  public boolean isPresent() {
    return present;
  }

  public int getItemID() {
    return itemID;
  }

  public int getCount() {
    return count;
  }

  public NbtTag getNbt() {
    return nbt;
  }

  @Override
  public String toString() {
    return "ItemStack{" +
        "present=" + present +
        ", itemID=" + itemID +
        ", count=" + count +
        ", nbt=" + nbt +
        '}';
  }

  public static void write(ByteBuf buf, ItemStack itemStack) {
    if (itemStack == null) {
      buf.writeBoolean(false);
      return;
    }

    buf.writeBoolean(itemStack.isPresent());
    if (itemStack.isPresent()) {
      ProtocolUtils.writeVarInt(buf, itemStack.getItemID());
      buf.writeByte(itemStack.getCount());
      if (itemStack.getNbt() != null) {
        Nbt.write(buf, itemStack.getNbt());
      } else {
        buf.writeByte(0);
      }
    }
  }

  public static ItemStack read(ByteBuf buf) {
    if (!buf.readBoolean()) {
      return EMPTY;
    }

    return new ItemStack(
        ProtocolUtils.readVarInt(buf),
        buf.readByte(),
        Nbt.read(buf)
    );
  }
}
