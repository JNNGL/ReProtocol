package com.jnngl.reprotocol.data.registry;

import com.jnngl.reprotocol.data.chat.ChatStyle;
import com.jnngl.reprotocol.data.nbt.NbtTag;
import com.jnngl.reprotocol.data.nbt.TagCompound;
import com.jnngl.reprotocol.util.MinecraftVersion;

import java.util.List;
import java.util.stream.Collectors;

public class ChatType {

  private final String key;
  private final int id;
  private final List<String> chatParameters;
  private final ChatStyle chatStyle;
  private final String chatTranslationKey;
  private final List<String> narrationParameters;
  private final String narrationTranslationKey;

  public ChatType(String key, int id, List<String> chatParameters, ChatStyle chatStyle, String chatTranslationKey,
                  List<String> narrationParameters, String narrationTranslationKey) {
    this.key = key;
    this.id = id;
    this.chatParameters = chatParameters;
    this.chatStyle = chatStyle;
    this.chatTranslationKey = chatTranslationKey;
    this.narrationParameters = narrationParameters;
    this.narrationTranslationKey = narrationTranslationKey;
  }

  public String getKey() {
    return key;
  }

  public int getId() {
    return id;
  }

  public List<String> getChatParameters() {
    return chatParameters;
  }

  public ChatStyle getChatStyle() {
    return chatStyle;
  }

  public String getChatTranslationKey() {
    return chatTranslationKey;
  }

  public List<String> getNarrationParameters() {
    return narrationParameters;
  }

  public String getNarrationTranslationKey() {
    return narrationTranslationKey;
  }

  @Override
  public String toString() {
    return "ChatType{" +
        "key='" + key + '\'' +
        ", id=" + id +
        ", chatParameters=" + chatParameters +
        ", chatStyle=" + chatStyle +
        ", chatTranslationKey='" + chatTranslationKey + '\'' +
        ", narrationParameters=" + narrationParameters +
        ", narrationTranslationKey='" + narrationTranslationKey + '\'' +
        '}';
  }

  public NbtTag toNBT(MinecraftVersion version) {
    TagCompound chatTag = NbtTag.emptyCompound("chat");
    chatTag.set(NbtTag.of("parameters",
        chatParameters.stream()
            .map(parameter -> NbtTag.of("", parameter))
            .collect(Collectors.toList())
    ));

    if (chatStyle != null) {
      chatTag.set(chatStyle.toNBT(version));
    }

    chatTag.set(NbtTag.of("translation_key", chatTranslationKey));

    TagCompound narrationTag = NbtTag.emptyCompound("narration");
    narrationTag.set(NbtTag.of("parameters",
        narrationParameters.stream()
            .map(parameter -> NbtTag.of("", parameter))
            .collect(Collectors.toList())
    ));

    narrationTag.set(NbtTag.of("translation_key", narrationTranslationKey));

    TagCompound elementTag = NbtTag.emptyCompound("element");
    elementTag.set(chatTag);
    elementTag.set(narrationTag);

    TagCompound rootTag = NbtTag.emptyCompound("");
    rootTag.set(NbtTag.of("name", key));
    rootTag.set(NbtTag.of("id", id));
    rootTag.set(elementTag);
    return rootTag;
  }

  public static ChatType fromNBT(NbtTag nbtTag, MinecraftVersion version) {
    // TODO: ChatType#fromNBT
    return null;
  }
}
