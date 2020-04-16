package draylar.glimmeringpotions.item.action;

import draylar.glimmeringpotions.item.base.UsePotionItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class RecallPotionItem extends UsePotionItem {

    public RecallPotionItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void runDrinkAction(ItemStack stack, World world, LivingEntity user, PlayerEntity playerEntity) {
        if (user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;
            BlockPos spawn = player.getSpawnPosition();

            if(spawn != null) {
                user.requestTeleport(spawn.getX(), spawn.getY(), spawn.getZ());
            }
        }
    }

    @Override
    public Formatting getFormatting() {
        return Formatting.BLUE;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(new TranslatableText("glimmeringpotions.tooltip.recall").formatted(Formatting.GRAY));
    }
}