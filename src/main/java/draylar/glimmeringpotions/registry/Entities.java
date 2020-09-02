package draylar.glimmeringpotions.registry;

import draylar.glimmeringpotions.GlimmeringPotions;
import draylar.glimmeringpotions.entity.PotionStandBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class Entities {

    public static final BlockEntityType<PotionStandBlockEntity> POTION_STAND = register(
            "potion_stand",
            BlockEntityType.Builder.create(PotionStandBlockEntity::new, Blocks.POTION_STAND).build(null)
    );

    private static <BE extends BlockEntity> BlockEntityType<BE> register(String name, BlockEntityType<BE> blockEntityType) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, GlimmeringPotions.id(name), blockEntityType);
    }

    public static void init() {
        // NO-OP
    }

    private Entities() {
        // NO-OP
    }
}
