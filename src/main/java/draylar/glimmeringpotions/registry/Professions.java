package draylar.glimmeringpotions.registry;

import com.google.common.collect.ImmutableMap;
import draylar.glimmeringpotions.GlimmeringPotions;
import draylar.glimmeringpotions.trade.PotionTrade;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class Professions {

//    public static final PointOfInterestType ALCHEMIST_POI = PointOfInterestHelper
//            .register(
//                    GlimmeringPotions.id("alchemist_poi"),
//                    1,
//                    1,
//                    Blocks.BREWING_STAND
//            );

    public static final VillagerProfession ALCHEMIST = Registry.register(
            Registry.VILLAGER_PROFESSION,
            GlimmeringPotions.id("alchemist"),
            VillagerProfessionBuilder
                    .create()
                    .id(GlimmeringPotions.id("alchemist"))
                    .harvestableItems(Items.NETHER_WART)
                    .workstation(PointOfInterestType.CLERIC)
                    .workSound(SoundEvents.BLOCK_BREWING_STAND_BREW)
                    .build()
    );

    public static void init() {
        TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(
                ALCHEMIST, copyToFastUtilMap(
                        ImmutableMap.of(
                                1, new TradeOffers.Factory[] {
                                        new TradeOffers.SellPotionHoldingItemFactory(Items.GLASS_BOTTLE, 1, Items.POTION, 1, 2, 3, 30),
                                        new TradeOffers.SellPotionHoldingItemFactory(Items.GLASS_BOTTLE, 1, Items.POTION, 1, 2, 3, 30),
                                        new TradeOffers.SellPotionHoldingItemFactory(Items.GLASS_BOTTLE, 1, Items.POTION, 1, 2, 3, 30)
                                },
                                2, new TradeOffers.Factory[] {
                                        new TradeOffers.BuyForOneEmeraldFactory(Items.NETHER_WART, 16, 4, 30),
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.NETHER_WART), 1, 4, 5, 30),
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.GUNPOWDER), 1, 4, 5, 30),
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.GLOWSTONE_DUST), 1, 16, 5, 30),
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.REDSTONE), 1, 16, 5, 30),
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.SUGAR), 1, 4, 5, 30),
                                },
                                3, new TradeOffers.Factory[] {
                                        new TradeOffers.SellItemFactory(Items.BREWING_STAND, 2, 1, 30),
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.GLISTERING_MELON_SLICE), 1, 4, 5, 30),
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.BLAZE_POWDER), 1, 2, 5, 30),
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.MAGMA_CREAM), 1, 4, 5, 30),
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.GOLDEN_CARROT), 1, 4, 5, 30)
                                },
                                4, new TradeOffers.Factory[]{
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.PUFFERFISH), 1, 4, 5, 30),
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.GHAST_TEAR), 1, 2, 5, 30),
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.RABBIT_FOOT), 1, 1, 5, 30),
                                        new PotionTrade(4, 2, 2, 1, 45, 0.1f)
                                },
                                5, new TradeOffers.Factory[]{
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.DRAGON_BREATH), 8, 1, 5, 30),
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.BLAZE_POWDER), 1, 4, 5, 30),
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.TURTLE_HELMET), 4, 1, 5, 30),
                                        new TradeOffers.SellItemFactory(new ItemStack(Items.PHANTOM_MEMBRANE), 4, 1, 5, 30),
                                        new PotionTrade(8, 3, 4, 1, 50, 0.1f)
                                }
                        )
                )
        );
    }

    private Professions() {

    }

    private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> immutableMap) {
        return new Int2ObjectOpenHashMap(immutableMap);
    }
}
