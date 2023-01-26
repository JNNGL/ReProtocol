package com.jnngl.reprotocol.data.entity.metadata;

import com.jnngl.reprotocol.data.particle.AbstractParticle;
import com.jnngl.reprotocol.data.particle.Particle;
import com.jnngl.reprotocol.util.MinecraftVersion;
import io.netty.buffer.ByteBuf;

public class ParticleMetadataItem extends EntityMetadataItem<AbstractParticle> {

  private AbstractParticle value;

  public ParticleMetadataItem(AbstractParticle value) {
    this.value = value;
  }

  public ParticleMetadataItem() {

  }

  @Override
  public void encode(ByteBuf buf, MinecraftVersion version) {
    Particle.write(buf, version, value);
  }

  @Override
  public void decode(ByteBuf buf, MinecraftVersion version) {
    value = Particle.read(buf, version);
  }

  @Override
  public AbstractParticle getValue() {
    return value;
  }

  @Override
  public void setValue(AbstractParticle value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "ParticleMetadataItem{" +
        "value=" + value +
        '}';
  }
}
