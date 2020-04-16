package draylar.glimmeringpotions;

import draylar.glimmeringpotions.registry.Items;
import draylar.glimmeringpotions.util.BaseStatusEffect;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.condition.LootConditions;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashSet;
import java.util.Set;

public class GlimmeringPotions implements ModInitializer {

    public static final StatusEffect BULWARK = register(
            "bulwark",
            new BaseStatusEffect(
                    StatusEffectType.BENEFICIAL,
                    8171462
            ).addAttributeModifier(
                    EntityAttributes.ARMOR,
                    "91AEAA56-376B-4498-935B-2F7F68070635",
                    6,
                    EntityAttributeModifier.Operation.ADDITION
            )
    );

    public static final StatusEffect FALSE_HEROISM = register(
            "false_heroism",
            new BaseStatusEffect(
                    StatusEffectType.BENEFICIAL,
                    0x42f587
            )
    );

    public static final Set<Identifier> lootTables = new HashSet<>();
    public static final Set<String> lootPhrases = new HashSet<>();

    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(Items.TELEPORTATION_POTION));

    @Override
    public void onInitialize() {
        Items.init();

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

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
            if(lootTables.contains(identifier) || phrasesContains(identifier)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(ConstantLootTableRange.create(1))
                        .withCondition(RandomChanceLootCondition.builder(0.5f))
                        .withEntry(ItemEntry.builder(Items.BULWARK_POTION))
                        .withEntry(ItemEntry.builder(Items.FALSE_HEROISM_POTION))
                        .withEntry(ItemEntry.builder(Items.GREATER_RESTORATION_POTION))
                        .withEntry(ItemEntry.builder(Items.RECALL_POTION))
                        .withEntry(ItemEntry.builder(Items.RESTORATION_POTION))
                        .withEntry(ItemEntry.builder(Items.RECALL_POTION))
                        .withEntry(ItemEntry.builder(Items.SURFACING_POTION))
                        .withEntry(ItemEntry.builder(Items.TELEPORTATION_POTION))
                        .withEntry(ItemEntry.builder(Items.ULTIMATE_RESTORATION_POTION));

                supplier.withPool(poolBuilder);
            }
        });
    }

    private boolean phrasesContains(Identifier identifier) {
        for (String phrase : lootPhrases) {
            if (identifier.toString().contains(phrase)) {
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
