package draylar.glimmeringpotions.registry;

import draylar.glimmeringpotions.GlimmeringPotions;
import draylar.glimmeringpotions.block.PotionStandBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class GPBlocks {

    public static final PotionStandBlock POTION_STAND = register("potion_stand", new PotionStandBlock(FabricBlockSettings.of(Material.METAL).hardness(2.0f).nonOpaque()), new Item.Settings().group(GlimmeringPotions.GROUP));

    private static <T extends Block> T register(String name, T block, Item.Settings settings) {
        T registeredBlock = Registry.register(Registry.BLOCK, GlimmeringPotions.id(name), block);
        Registry.register(Registry.ITEM, GlimmeringPotions.id(name), new BlockItem(registeredBlock, settings));
        return registeredBlock;
    }

    private static <T extends Block> T register(String name, T block) {
        return Registry.register(Registry.BLOCK, GlimmeringPotions.id(name), block);
    }

    public static void init() {
        // NO-OP
    }

    private GPBlocks() {
        // NO-OP
    }
}
