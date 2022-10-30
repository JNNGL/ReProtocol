package com.jnngl.reprotocol.data.chat;

import com.jnngl.reprotocol.data.nbt.NbtTag;
import com.jnngl.reprotocol.data.nbt.TagCompound;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class ChatStyle {

  private final String color;
  private final Boolean bold;
  private final Boolean italic;
  private final Boolean underlined;
  private final Boolean strikethrough;
  private final Boolean obfuscated;
  private final String insertion;
  private final String font;

  public ChatStyle(String color, Boolean bold, Boolean italic, Boolean underlined, Boolean strikethrough,
                   Boolean obfuscated, String insertion, String font) {
    this.color = color;
    this.bold = bold;
    this.italic = italic;
    this.underlined = underlined;
    this.strikethrough = strikethrough;
    this.obfuscated = obfuscated;
    this.insertion = insertion;
    this.font = font;
  }

  public String getColor() {
    return color;
  }

  public Boolean getBold() {
    return bold;
  }

  public Boolean getItalic() {
    return italic;
  }

  public Boolean getUnderlined() {
    return underlined;
  }

  public Boolean getStrikethrough() {
    return strikethrough;
  }

  public Boolean getObfuscated() {
    return obfuscated;
  }

  public String getInsertion() {
    return insertion;
  }

  public String getFont() {
    return font;
  }

  @Override
  public String toString() {
    return "ChatStyle{" +
        "color='" + color + '\'' +
        ", bold=" + bold +
        ", italic=" + italic +
        ", underlined=" + underlined +
        ", strikethrough=" + strikethrough +
        ", obfuscated=" + obfuscated +
        ", insertion='" + insertion + '\'' +
        ", font='" + font + '\'' +
        '}';
  }

  public NbtTag toNBT(MinecraftVersion version) {
    TagCompound tag = NbtTag.emptyCompound("style");

    if (color != null) {
      tag.set(NbtTag.of("color", color));
    }

    if (bold != null) {
      tag.set(NbtTag.of("bold", bold));
    }

    if (italic != null) {
      tag.set(NbtTag.of("italic", italic));
    }

    if (underlined != null) {
      tag.set(NbtTag.of("underlined", underlined));
    }

    if (strikethrough != null) {
      tag.set(NbtTag.of("strikethrough", strikethrough));
    }

    if (obfuscated != null) {
      tag.set(NbtTag.of("obfuscated", obfuscated));
    }

    if (insertion != null) {
      tag.set(NbtTag.of("insertion", insertion));
    }

    if (font != null) {
      tag.set(NbtTag.of("font", font));
    }

    return tag;
  }

  public static ChatStyle fromNBT(MinecraftVersion version) {
    // TODO: ChatStyle#fromNBT
    return null;
  }
}
