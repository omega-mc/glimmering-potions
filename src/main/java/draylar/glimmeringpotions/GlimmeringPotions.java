package draylar.glimmeringpotions;

import draylar.glimmeringpotions.registry.GPBlocks;
import draylar.glimmeringpotions.registry.GPEntities;
import draylar.glimmeringpotions.registry.GPItems;
import draylar.glimmeringpotions.registry.GPProfessions;
import draylar.glimmeringpotions.util.BaseStatusEffect;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashSet;
import java.util.Set;

public class GlimmeringPotions implements ModInitializer {

    public static final StatusEffect BULWARK = register(
            "bulwark",
            new BaseStatusEffect(
                    StatusEffectCategory.BENEFICIAL,
                    8171462
            ).addAttributeModifier(
                    EntityAttributes.GENERIC_ARMOR,
                    "91AEAA56-376B-4498-935B-2F7F68070635",
                    6,
                    EntityAttributeModifier.Operation.ADDITION
            )
    );

    public static final StatusEffect FALSE_HEROISM = register(
            "false_heroism",
            new BaseStatusEffect(
                    StatusEffectCategory.BENEFICIAL,
                    0x42f587
            )
    );

    public static final Set<Identifier> lootTables = new HashSet<>();
    public static final Set<String> lootPhrases = new HashSet<>();

    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(GPItems.TELEPORTATION_POTION));

    @Override
    public void onInitialize() {
        GPItems.init();
        GPBlocks.init();
        GPEntities.init();
        GPProfessions.init();

        lootTables.add(new Identifier("minecraft:chests/abandoned_mineshaft"));
        lootTables.add(new Identifier("minecraft:chests/buried_treasure"));
        lootTables.add(new Identifier("minecraft:chests/desert_pyramid"));
        lootTables.add(new Identifier("minecraft:chests/end_city_treasure"));
        lootTables.add(new Identifier("minecraft:chests/igloo_chest"));
        lootTables.add(new Identifier("minecraft:chests/jungle_temple"));
        lootTables.add(new Identifier("minecraft:chests/nether_bridge"));
        lootTables.add(new Identifier("minecraft:chests/pillager_outpost"));
        lootTables.add(new Identifier("minecraft:chests/shipwreck_treasure"));
        lootTables.add(new Identifier("minecraft:chests/simple_dungeon"));
        lootTables.add(new Identifier("minecraft:chests/woodland_mansion"));

        lootPhrases.add("dungeon");
        lootPhrases.add("treasure");

        LootTableEvents.MODIFY.register((resourceManager, lootManager, identifier, supplier, source) -> {
            if(lootTables.contains(identifier) || phrasesContains(identifier)) {
                LootPool pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5f).build())
                        .with(ItemEntry.builder(GPItems.BULWARK_POTION).build())
                        .with(ItemEntry.builder(GPItems.FALSE_HEROISM_POTION).build())
                        .with(ItemEntry.builder(GPItems.GREATER_RESTORATION_POTION).build())
                        .with(ItemEntry.builder(GPItems.RECALL_POTION).build())
                        .with(ItemEntry.builder(GPItems.RESTORATION_POTION).build())
                        .with(ItemEntry.builder(GPItems.RECALL_POTION).build())
                        .with(ItemEntry.builder(GPItems.SURFACING_POTION).build())
                        .with(ItemEntry.builder(GPItems.TELEPORTATION_POTION).build())
                        .with(ItemEntry.builder(GPItems.ULTIMATE_RESTORATION_POTION).build())
                        .build();

                supplier.pool(pool);
            }
        });
    }

    private boolean phrasesContains(Identifier identifier) {
        for (String phrase : lootPhrases) {
            if(identifier.getPath().contains(phrase)) {
                return true;
            }
        }

        return false;
    }

    private static StatusEffect register(String name, StatusEffect entry) {
        return Registry.register(Registry.STATUS_EFFECT, id(name), entry);
    }

    public static Identifier id(String name) {
        return new Identifier("glimmeringpotions", name);
    }
}
