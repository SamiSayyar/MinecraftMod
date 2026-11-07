package com.example.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FrostbiteSwordItem extends Item {

    private static final int TICKS = 80;

    public FrostbiteSwordItem(Properties props) {
        super(props);
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.hurtEnemy(stack, target, attacker);
        if (target.level().isClientSide()) return;

        target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, TICKS, 2), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, TICKS, 0), attacker);
    }
}
