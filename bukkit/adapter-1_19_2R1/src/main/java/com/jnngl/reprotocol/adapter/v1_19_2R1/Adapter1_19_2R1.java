package com.jnngl.reprotocol.adapter.v1_19_2R1;

import com.google.gson.Gson;
import com.jnngl.reprotocol.ConnectionState;
import com.jnngl.reprotocol.Packet;
import com.jnngl.reprotocol.adapter.VersionAdapter;
import com.jnngl.reprotocol.packet.handshake.Handshake;
import com.jnngl.reprotocol.packet.status.StatusPing;
import com.jnngl.reprotocol.packet.status.StatusRequest;
import com.jnngl.reprotocol.packet.status.StatusResponse;
import com.jnngl.reprotocol.registry.VersionPacketRegistry;
import com.jnngl.reprotocol.remapper.InboundPacketRemapper;
import com.jnngl.reprotocol.remapper.OutboundPacketRemapper;
import com.jnngl.reprotocol.remapper.PacketRemapper;
import net.minecraft.network.EnumProtocol;
import net.minecraft.network.protocol.handshake.PacketHandshakingInSetProtocol;
import net.minecraft.network.protocol.status.PacketStatusInPing;
import net.minecraft.network.protocol.status.PacketStatusInStart;
import net.minecraft.network.protocol.status.PacketStatusOutPong;
import net.minecraft.network.protocol.status.PacketStatusOutServerInfo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Adapter1_19_2R1 implements VersionAdapter {

  private static EnumProtocol toEnumProtocol(ConnectionState state) {
    return EnumProtocol.a(state.ordinal());
  }

  @Override
  public PacketRemapper createRemapper(VersionPacketRegistry inboundRegistry, VersionPacketRegistry outboundRegistry) {
    Gson statusGson;

    try {
      Field statusGsonField = PacketStatusOutServerInfo.class.getDeclaredField("a");
      statusGsonField.setAccessible(true);
      statusGson = (Gson) statusGsonField.get(null);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }

    Map<Class<? extends Packet>, InboundPacketRemapper> inboundRemapper = new HashMap<>();

    inboundRemapper.put(Handshake.class, VersionAdapter.<Handshake>wrapInbound(packet ->
        new PacketHandshakingInSetProtocol(packet.getIP(), packet.getPort(), toEnumProtocol(packet.getNextState()))));

    inboundRemapper.put(StatusRequest.class, packet -> new PacketStatusInStart());

    inboundRemapper.put(StatusPing.class, VersionAdapter.<StatusPing>wrapInbound(packet ->
        new PacketStatusInPing(packet.getPayload())));

    Map<Class<?>, OutboundPacketRemapper> outboundRemapper = new HashMap<>();

    outboundRemapper.put(PacketStatusOutServerInfo.class, VersionAdapter.<PacketStatusOutServerInfo>wrapOutbound(
        packet -> new StatusResponse(statusGson.toJson(packet.b()))));

    outboundRemapper.put(PacketStatusOutPong.class, VersionAdapter.<PacketStatusOutPong>wrapOutbound(
        packet -> new StatusPing(packet.b())));

    return new PacketRemapper(inboundRemapper, outboundRemapper, inboundRegistry, outboundRegistry);
  }
}
