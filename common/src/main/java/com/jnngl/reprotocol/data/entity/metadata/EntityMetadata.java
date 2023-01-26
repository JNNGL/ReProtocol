package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.data.registry.ClassRegistry;
import com.jnngl.reprotocol.data.registry.VersionRegistry;
import com.jnngl.reprotocol.util.MapBuilder;
import com.jnngl.reprotocol.util.MinecraftVersion;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class EntityMetadata {

  public static final VersionRegistry<EntityMetadataItem<?>> REGISTRY =
      new VersionRegistry<EntityMetadataItem<?>>()
          .add(
              MinecraftVersion.MINECRAFT_1_19_1,
              new ClassRegistry<>(
                  new MapBuilder<Integer, Supplier<EntityMetadataItem<?>>>()
                      .put(0, ByteMetadataItem::new)
                      .put(1, VarintMetadataItem::new)
                      .put(2, FloatMetadataItem::new)
                      .put(3, StringMetadataItem::new)
                      .put(4, ChatMetadataItem::new)
                      .put(5, OptChatMetadataItem::new)
                      .put(6, ItemStackMetadataItem::new)
                      .put(7, BooleanMetadataItem::new)
                      .put(8, RotationMetadataItem::new)
                      .put(9, PositionMetadataItem::new)
                      .put(10, OptPositionMetadataItem::new)
                      .put(11, DirectionMetadataItem::new)
                      .put(12, OptUUIDMetadataItem::new)
                      .put(13, BlockMetadataItem::new)
                      .put(14, NBTMetadataItem::new)
                      .put(15, ParticleMetadataItem::new)
                      .put(16, VillagerMetadataItem::new)
                      .put(17, OptVarintMetadataItem::new)
                      .put(18, PoseMetadataItem::new)
                      .put(19, CatVariantMetadataItem::new)
                      .put(20, FrogVariantMetadataItem::new)
                      .put(21, GlobalPosMetadataItem::new)
                      .put(22, PaintingVariantMetadataItem::new)
                      .getUnmodifiable()
              )
          )
          .build();

  private final Map<Integer, EntityMetadataItem<?>> entries = new HashMap<>();

  private int ridx = 0;
  private int widx = 0;

  public void setItem(int index, EntityMetadataItem<?> value) {
    if (value == null) {
      entries.remove(index);
    } else {
      entries.put(index, value);
    }
  }

  public <T> void set(int index, T defaultValue, EntityMetadataItem<T> value) {
    if (value == null || !Objects.equals(value.getValue(), defaultValue)) {
      setItem(index, value);
    }
  }

  public <T> void write(T defaultValue, EntityMetadataItem<T> value) {
    set(widx++, defaultValue, value);
  }

  @SuppressWarnings("unchecked")
  public <T> EntityMetadataItem<T> getItem(int index) {
    return (EntityMetadataItem<T>) entries.get(index);
  }

  public <T> T get(int index, T defaultValue) {
    return Optional.ofNullable(this.<T>getItem(index))
        .map(EntityMetadataItem::getValue)
        .orElse(defaultValue);
  }

  public <T> T read(T defaultValue) {
    return get(ridx++, defaultValue);
  }

  public Map<Integer, EntityMetadataItem<?>> getEntries() {
    return entries;
  }

  public int getReaderIndex() {
    return ridx;
  }

  public void setReaderIndex(int ridx) {
    this.ridx = ridx;
  }

  public int getWriterIndex() {
    return widx;
  }

  public void setWriterIndex(int widx) {
    this.widx = widx;
  }

  public void resetReaderIndex() {
    ridx = 0;
  }

  public void resetWriterIndex() {
    widx = 0;
  }
}
