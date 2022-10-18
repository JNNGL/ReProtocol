package com.jnngl.reprotocol;

import com.jnngl.reprotocol.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;

public class Reprotocol extends JavaPlugin {

  private final Injector injector = new Injector();

  @Override
  public void onEnable() {
    injector.addInjector(channel -> getLogger().info(channel.pipeline().names().toString()));
    injector.inject();
  }
}
