package com.example.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FrostGemItem extends Item {

    private static final double RADIUS = 6.0;
    private static final int SELF_TICKS = 160;
    private static final int ENEMY_TICKS = 100;
    private static final int COOLDOWN_TICKS = 400;

    public FrostGemItem(Properties props) {
        super(props);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!(level instanceof ServerLevel server)) return InteractionResult.SUCCESS;

        player.addEffect(new MobEffectInstance(MobEffects.SPEED, SELF_TICKS, 2));
        player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, SELF_TICKS, 1));

        double r2 = RADIUS * RADIUS;
        for (LivingEntity e : level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(RADIUS),
                e -> e != player && e.isAlive() && !e.isAlliedTo(player) && e.distanceToSqr(player) <= r2)) {
            e.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, ENEMY_TICKS, 3), player);
            e.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, ENEMY_TICKS, 1), player);
        }

        server.sendParticles(ParticleTypes.SNOWFLAKE, player.getX(), player.getY() + 1, player.getZ(),
                80, RADIUS / 2, 0.5, RADIUS / 2, 0.05);
        level.playSound(null, player.blockPosition(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1f, 0.6f);

        player.getCooldowns().addCooldown(stack, COOLDOWN_TICKS);
        return InteractionResult.SUCCESS;
    }
}
