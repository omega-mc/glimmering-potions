package draylar.glimmeringpotions.registry;

import draylar.glimmeringpotions.GlimmeringPotions;
import draylar.glimmeringpotions.entity.PotionStandBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class GPEntities {

    public static final BlockEntityType<PotionStandBlockEntity> POTION_STAND = register(
            "potion_stand",
            FabricBlockEntityTypeBuilder.create(PotionStandBlockEntity::new, GPBlocks.POTION_STAND).build(null)
    );

    private static <BE extends BlockEntity> BlockEntityType<BE> register(String name, BlockEntityType<BE> blockEntityType) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, GlimmeringPotions.id(name), blockEntityType);
    }

    public static void init() {
        // NO-OP
    }

    private GPEntities() {
        // NO-OP
    }
}
