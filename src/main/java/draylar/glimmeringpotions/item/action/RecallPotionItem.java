package draylar.glimmeringpotions.item.action;

import draylar.glimmeringpotions.item.base.UsePotionItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BedBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class RecallPotionItem extends UsePotionItem {

    public RecallPotionItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void runDrinkAction(ItemStack stack, World world, LivingEntity user, ServerPlayerEntity player) {
        BlockPos spawn = player.getSpawnPointPosition();

        if (spawn != null) {
            Vec3d spawnPos;

            // Run standard bed placement logic only if the player would spawn at a bed
            if(world.getBlockState(spawn).getBlock() instanceof BedBlock) {
                spawnPos = BedBlock.findWakeUpPosition(EntityType.PLAYER, world, spawn, 0).orElseGet(() -> {
                    BlockPos blockPos2 = spawn.up();
                    return new Vec3d((double) blockPos2.getX() + 0.5D, (double) blockPos2.getY() + 0.1D, (double) blockPos2.getZ() + 0.5D);
                });
            } else {
                BlockPos add = spawn.up().add(0.5D, 0, 0.5D);
                spawnPos = new Vec3d(add.getX(), add.getY(), add.getZ());
            }

            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0f, 1.0f);
            user.requestTeleport(spawnPos.x, spawnPos.y, spawnPos.z);
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

        tooltip.add(Text.translatable("glimmeringpotions.tooltip.recall").formatted(Formatting.GRAY));
    }
}