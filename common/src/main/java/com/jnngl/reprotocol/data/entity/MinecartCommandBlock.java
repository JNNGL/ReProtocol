package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.StringMetadataItem;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class MinecartCommandBlock extends AbstractMinecart {

  private String command = "";
  private String lastOutput = "{\"text\":\"\"}";

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write("", new StringMetadataItem(command));
    metadata.write("{\"text\":\"\"}", new StringMetadataItem(lastOutput));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    command = metadata.read("");
    lastOutput = metadata.read("{\"text\":\"\"}");
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public String getLastOutput() {
    return lastOutput;
  }

  public void setLastOutput(String lastOutput) {
    this.lastOutput = lastOutput;
  }
}
