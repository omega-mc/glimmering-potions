package draylar.glimmeringpotions.item.effect;

import draylar.glimmeringpotions.GlimmeringPotions;
import draylar.glimmeringpotions.item.base.EffectPotionItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class BulwarkPotion extends EffectPotionItem {

    public BulwarkPotion(Settings settings) {
        super(settings);

        statusEffects.add(new StatusEffectInstance(GlimmeringPotions.BULWARK, 20 * 60, 0));
    }

    @Override
    public Formatting getFormatting() {
        return Formatting.DARK_GRAY;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(Text.translatable("glimmeringpotions.tooltip.bulwark_1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("glimmeringpotions.tooltip.bulwark_2").formatted(Formatting.GRAY));
    }
}