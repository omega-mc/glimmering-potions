package draylar.glimmeringpotions.item.effect;

import draylar.glimmeringpotions.item.base.EffectPotionItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.util.Formatting;

public class RestorationPotion extends EffectPotionItem {

    public RestorationPotion(Item.Settings settings, int healAmplifier, int regenDuration, int durationAmplifier) {
        super(settings);

        statusEffects.add(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 0, healAmplifier));
        statusEffects.add(new StatusEffectInstance(StatusEffects.REGENERATION, regenDuration, durationAmplifier));
    }

    @Override
    public Formatting getFormatting() {
        return Formatting.RED;
    }
}