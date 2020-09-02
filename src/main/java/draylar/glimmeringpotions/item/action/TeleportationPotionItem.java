package draylar.glimmeringpotions.item.action;

import draylar.glimmeringpotions.item.base.UsePotionItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

import java.util.List;

public class TeleportationPotionItem extends UsePotionItem {

    public TeleportationPotionItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void runDrinkAction(ItemStack stack, World world, LivingEntity user, ServerPlayerEntity playerEntity) {
        int randChunkX = world.getRandom().nextInt(10000) - 5000;
        int randChunkZ = world.getRandom().nextInt(10000) - 5000;

        BlockPos targetPos = new BlockPos(randChunkX + user.getX(), 0, randChunkZ + user.getZ());

        targetPos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, targetPos);
        user.requestTeleport(targetPos.getX(), 150, targetPos.getZ());
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 20 * 15, 0));
    }

    @Override
    public Formatting getFormatting() {
        return Formatting.LIGHT_PURPLE;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(new TranslatableText("glimmeringpotions.tooltip.teleporting").formatted(Formatting.GRAY));
    }
}