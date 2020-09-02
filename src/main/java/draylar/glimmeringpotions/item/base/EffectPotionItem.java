package draylar.glimmeringpotions.item.base;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class EffectPotionItem extends UsePotionItem {

    protected final List<StatusEffectInstance> statusEffects;

    public EffectPotionItem(Settings settings) {
        super(settings);
        statusEffects = new ArrayList<>();
    }

    @Override
    public void runDrinkAction(ItemStack stack, World world, LivingEntity user, ServerPlayerEntity playerEntity) {
        for (StatusEffectInstance statusEffectInstance : statusEffects) {
            if (statusEffectInstance.getEffectType().isInstant()) {
                statusEffectInstance.getEffectType().applyInstantEffect(playerEntity, playerEntity, user, statusEffectInstance.getAmplifier(), 1.0D);
            } else {
                user.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
            }
        }
    }
}
