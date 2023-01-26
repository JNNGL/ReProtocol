package com.jnngl.reprotocol.data.particle;

import com.jnngl.reprotocol.data.registry.ClassRegistry;
import com.jnngl.reprotocol.data.registry.VersionRegistry;
import com.jnngl.reprotocol.util.MapBuilder;
import com.jnngl.reprotocol.util.MinecraftVersion;
import com.jnngl.reprotocol.util.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import java.util.function.Supplier;

public class Particle {

  public static final VersionRegistry<AbstractParticle> REGISTRY =
      new VersionRegistry<AbstractParticle>()
          .add(
              MinecraftVersion.MINECRAFT_1_19_1,
              new ClassRegistry<>(
                  new MapBuilder<Integer, Supplier<AbstractParticle>>()
                      .put(0, () -> AmbientEntityEffectParticle.INSTANCE)
                      .put(1, () -> AngryVillagerParticle.INSTANCE)
                      .put(2, BlockParticle::new)
                      .put(3, BlockMarkerParticle::new)
                      .put(4, () -> BubbleParticle.INSTANCE)
                      .put(5, () -> CloudParticle.INSTANCE)
                      .put(6, () -> CritParticle.INSTANCE)
                      .put(7, () -> DamageIndicatorParticle.INSTANCE)
                      .put(8, () -> DragonBreathParticle.INSTANCE)
                      .put(9, () -> DrippingLavaParticle.INSTANCE)
                      .put(10, () -> FallingLavaParticle.INSTANCE)
                      .put(11, () -> LandingLavaParticle.INSTANCE)
                      .put(12, () -> DrippingWaterParticle.INSTANCE)
                      .put(13, () -> FallingWaterParticle.INSTANCE)
                      .put(14, DustParticle::new)
                      .put(15, DustColorTransition::new)
                      .put(16, () -> EffectParticle.INSTANCE)
                      .put(17, () -> ElderGuardianParticle.INSTANCE)
                      .put(18, () -> EnchantedHitParticle.INSTANCE)
                      .put(19, () -> EnchantParticle.INSTANCE)
                      .put(20, () -> EndRodParticle.INSTANCE)
                      .put(21, () -> EntityEffectParticle.INSTANCE)
                      .put(22, () -> ExplosionEmitterParticle.INSTANCE)
                      .put(23, () -> ExplosionParticle.INSTANCE)
                      .put(24, FallingDustParticle::new)
                      .put(25, () -> FireworkParticle.INSTANCE)
                      .put(26, () -> FishingParticle.INSTANCE)
                      .put(27, () -> FlameParticle.INSTANCE)
                      .put(28, () -> SoulFireFlameParticle.INSTANCE)
                      .put(29, () -> SoulParticle.INSTANCE)
                      .put(30, () -> FlashParticle.INSTANCE)
                      .put(31, () -> HappyVillagerParticle.INSTANCE)
                      .put(32, () -> ComposterParticle.INSTANCE)
                      .put(33, () -> HeartParticle.INSTANCE)
                      .put(34, () -> InstantEffectParticle.INSTANCE)
                      .put(35, ItemParticle::new)
                      .put(36, VibrationParticle::new)
                      .put(37, () -> ItemSlimeParticle.INSTANCE)
                      .put(38, () -> ItemSnowballParticle.INSTANCE)
                      .put(39, () -> LargeSmokeParticle.INSTANCE)
                      .put(40, () -> LavaParticle.INSTANCE)
                      .put(41, () -> MyceliumParticle.INSTANCE)
                      .put(42, () -> NoteParticle.INSTANCE)
                      .put(43, () -> PoofParticle.INSTANCE)
                      .put(44, () -> PortalParticle.INSTANCE)
                      .put(45, () -> RainParticle.INSTANCE)
                      .put(46, () -> SmokeParticle.INSTANCE)
                      .put(47, () -> SneezeParticle.INSTANCE)
                      .put(48, () -> SpitParticle.INSTANCE)
                      .put(49, () -> SquidInkParticle.INSTANCE)
                      .put(50, () -> SweepAttackParticle.INSTANCE)
                      .put(51, () -> TotemOfUndyingParticle.INSTANCE)
                      .put(52, () -> UnderwaterParticle.INSTANCE)
                      .put(53, () -> SplashParticle.INSTANCE)
                      .put(54, () -> WitchParticle.INSTANCE)
                      .put(55, () -> BubblePopParticle.INSTANCE)
                      .put(56, () -> CurrentDownParticle.INSTANCE)
                      .put(57, () -> BubbleColumnUpParticle.INSTANCE)
                      .put(58, () -> NautilusParticle.INSTANCE)
                      .put(59, () -> DolphinParticle.INSTANCE)
                      .put(60, () -> CampfireCosySmokeParticle.INSTANCE)
                      .put(61, () -> CampfireSignalSmokeParticle.INSTANCE)
                      .put(62, () -> DrippingHoneyParticle.INSTANCE)
                      .put(63, () -> FallingHoneyParticle.INSTANCE)
                      .put(64, () -> LandingHoneyParticle.INSTANCE)
                      .put(65, () -> FallingNectarParticle.INSTANCE)
                      .put(66, () -> FallingSporeBlossomParticle.INSTANCE)
                      .put(67, () -> AshParticle.INSTANCE)
                      .put(68, () -> CrimsonSporeParticle.INSTANCE)
                      .put(69, () -> WarpedSporeParticle.INSTANCE)
                      .put(70, () -> SporeBlossomAirParticle.INSTANCE)
                      .put(71, () -> DrippingObsidianTearParticle.INSTANCE)
                      .put(72, () -> FallingObsidianTearParticle.INSTANCE)
                      .put(73, () -> LandingObsidianTearParticle.INSTANCE)
                      .put(74, () -> ReversePortalParticle.INSTANCE)
                      .put(75, () -> WhiteAshParticle.INSTANCE)
                      .put(76, () -> SmallFlameParticle.INSTANCE)
                      .put(77, () -> SnowflakeParticle.INSTANCE)
                      .put(78, () -> DrippingDripstoneLavaParticle.INSTANCE)
                      .put(79, () -> FallingDripstoneLavaParticle.INSTANCE)
                      .put(80, () -> DrippingDripstoneWaterParticle.INSTANCE)
                      .put(81, () -> FallingDripstoneWaterParticle.INSTANCE)
                      .put(82, () -> GlowSquidInkParticle.INSTANCE)
                      .put(83, () -> GlowParticle.INSTANCE)
                      .put(84, () -> WaxOnParticle.INSTANCE)
                      .put(85, () -> WaxOffParticle.INSTANCE)
                      .put(86, () -> ElectricSparkParticle.INSTANCE)
                      .put(87, () -> ScrapeParticle.INSTANCE)
                      .getUnmodifiable()
              )
          )
          .build();

  public static void write(ByteBuf buf, MinecraftVersion version, AbstractParticle particle) {
    ProtocolUtils.writeVarInt(buf, REGISTRY.getRegistry(version).getID(particle));
    particle.decode(buf, version);
  }

  public static AbstractParticle read(ByteBuf buf, MinecraftVersion version) {
    AbstractParticle particle = REGISTRY.getRegistry(version).get(ProtocolUtils.readVarInt(buf));
    particle.decode(buf, version);
    return particle;
  }
}
