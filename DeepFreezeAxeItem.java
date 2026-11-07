package com.example.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;

public class DeepFreezeAxeItem extends AxeItem {

    public DeepFreezeAxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties props) {
        super(material, attackDamage, attackSpeed, props);
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.hurtEnemy(stack, target, attacker);
        if (target.level().isClientSide()) return;

        target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 4), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 0), attacker);
    }
}
