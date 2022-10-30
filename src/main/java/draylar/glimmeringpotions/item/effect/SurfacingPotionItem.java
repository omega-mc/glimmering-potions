package draylar.glimmeringpotions.item.effect;

import draylar.glimmeringpotions.item.base.UsePotionItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

import java.util.List;

public class SurfacingPotionItem extends UsePotionItem {

    public SurfacingPotionItem(Settings settings) {
        super(settings);
    }

    @Override
    public void runDrinkAction(ItemStack stack, World world, LivingEntity user, ServerPlayerEntity playerEntity) {
        if(playerEntity != null) {
            BlockPos up = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, playerEntity.getBlockPos());
            playerEntity.requestTeleport(up.getX() + .5, up.getY(), up.getZ() + .5);
        }
    }

    @Override
    public Formatting getFormatting() {
        return Formatting.DARK_GREEN;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(Text.translatable("glimmeringpotions.tooltip.surfacing").formatted(Formatting.GRAY));
    }
}
