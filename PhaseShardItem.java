package name.modid;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PhaseShardItem extends Item {
    public PhaseShardItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            // 3 seconds (60 ticks) of Invisibility & Speed III
            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 60, 0));
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 2));

            // Weakness penalty during phase
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0));

            // Play sound at player's position
            level.playSound(
                    null,
                    player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENDERMAN_TELEPORT,
                    SoundSource.PLAYERS,
                    1.0F, 1.0F
            );

            // 15-second cooldown (300 ticks)
            player.getCooldowns().addCooldown(itemStack, 300);

            // Use up one shard (does nothing in creative mode)
            itemStack.consume(1, player);
        }

        return InteractionResult.SUCCESS;
    }
}
