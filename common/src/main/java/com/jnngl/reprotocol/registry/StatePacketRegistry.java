package com.jnngl.reprotocol.registry;

import com.jnngl.reprotocol.Packet;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class StatePacketRegistry {

  private final Map<Integer, Supplier<Packet>> suppliers;
  private final Map<Class<? extends Packet>, Integer> classToID;

  public StatePacketRegistry(Map<Integer, Supplier<Packet>> suppliers) {
    this.suppliers = suppliers;
    this.classToID = new HashMap<>();

    suppliers.forEach((id, supplier) -> classToID.put(supplier.get().getClass(), id));
  }

  public StatePacketRegistry() {
    this(new HashMap<>());
  }

  public Packet getPacket(int id) {
    Supplier<Packet> supplier = suppliers.get(id);
    if (supplier != null) {
      return supplier.get();
    } else {
      return null;
    }
  }

  public int getID(Class<? extends Packet> packetClass) {
    return classToID.getOrDefault(packetClass, 0);
  }

  public int getID(Packet packet) {
    return getID(packet.getClass());
  }

  public void registerPacket(int id, Supplier<Packet> supplier) {
    suppliers.put(id, supplier);
  }

  public Map<Integer, Supplier<Packet>> getSuppliers() {
    return suppliers;
  }
}
