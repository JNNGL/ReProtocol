package com.jnngl.reprotocol.data.entity;

import com.jnngl.reprotocol.data.entity.metadata.BooleanMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.EntityMetadata;
import com.jnngl.reprotocol.data.entity.metadata.FloatMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.ParticleMetadataItem;
import com.jnngl.reprotocol.data.entity.metadata.VarintMetadataItem;
import com.jnngl.reprotocol.data.particle.AbstractParticle;
import com.jnngl.reprotocol.data.particle.EffectParticle;
import com.jnngl.reprotocol.util.MinecraftVersion;

public class AreaEffectCloud extends Entity {

  private float radius = 0.5F;
  private int color = 0;
  private boolean singlePoint = false;
  private AbstractParticle particle = EffectParticle.INSTANCE;

  @Override
  public void createMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.createMetadata(metadata, version);
    metadata.write(0.5F, new FloatMetadataItem(radius));
    metadata.write(0, new VarintMetadataItem(color));
    metadata.write(false, new BooleanMetadataItem(singlePoint));
    metadata.write(EffectParticle.INSTANCE, new ParticleMetadataItem(particle));
  }

  @Override
  public void loadMetadata(EntityMetadata metadata, MinecraftVersion version) {
    super.loadMetadata(metadata, version);
    radius = metadata.read(0.5F);
    color = metadata.read(0);
    singlePoint = metadata.read(false);
    particle = metadata.read(EffectParticle.INSTANCE);
  }

  public float getRadius() {
    return radius;
  }

  public void setRadius(float radius) {
    this.radius = radius;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public boolean isSinglePoint() {
    return singlePoint;
  }

  public void setSinglePoint(boolean singlePoint) {
    this.singlePoint = singlePoint;
  }

  public AbstractParticle getParticle() {
    return particle;
  }

  public void setParticle(AbstractParticle particle) {
    this.particle = particle;
  }
}
