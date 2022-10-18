package com.jnngl.reprotocol.inject;

import io.netty.channel.Channel;

public interface ChannelInjector {

  void inject(Channel channel);
}
