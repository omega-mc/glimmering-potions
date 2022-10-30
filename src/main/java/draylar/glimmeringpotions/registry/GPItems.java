package draylar.glimmeringpotions.registry;

import draylar.glimmeringpotions.GlimmeringPotions;
import draylar.glimmeringpotions.item.action.RecallPotionItem;
import draylar.glimmeringpotions.item.effect.BulwarkPotion;
import draylar.glimmeringpotions.item.effect.FalseHeroismPotion;
import draylar.glimmeringpotions.item.effect.RestorationPotion;
import draylar.glimmeringpotions.item.action.TeleportationPotionItem;
import draylar.glimmeringpotions.item.effect.SurfacingPotionItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class GPItems {

    public static final RecallPotionItem RECALL_POTION = register("recall_potion", new RecallPotionItem(new Item.Settings().maxCount(16).group(GlimmeringPotions.GROUP)));
    public static final RestorationPotion RESTORATION_POTION = register("restoration_potion", new RestorationPotion(new Item.Settings().maxCount(16).group(GlimmeringPotions.GROUP), 2, 20 * 15, 1));
    public static final RestorationPotion GREATER_RESTORATION_POTION = register("greater_restoration_potion", new RestorationPotion(new Item.Settings().maxCount(16).group(GlimmeringPotions.GROUP), 3, 20 * 20, 1));
    public static final RestorationPotion ULTIMATE_RESTORATION_POTION = register("ultimate_restoration_potion", new RestorationPotion(new Item.Settings().maxCount(16).group(GlimmeringPotions.GROUP), 4, 20 * 25, 2));
    public static final TeleportationPotionItem TELEPORTATION_POTION = register("teleportation_potion", new TeleportationPotionItem(new Item.Settings().maxCount(16).group(GlimmeringPotions.GROUP)));
    public static final BulwarkPotion BULWARK_POTION = register("bulwark_potion", new BulwarkPotion(new Item.Settings().maxCount(16).group(GlimmeringPotions.GROUP)));
    public static final SurfacingPotionItem SURFACING_POTION = register("surfacing_potion", new SurfacingPotionItem(new Item.Settings().maxCount(16).group(GlimmeringPotions.GROUP)));
    public static final FalseHeroismPotion FALSE_HEROISM_POTION = register("false_heroism_potion", new FalseHeroismPotion(new Item.Settings().maxCount(16).group(GlimmeringPotions.GROUP)));

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registry.ITEM, GlimmeringPotions.id(name), item);
    }

    public static void init() {
        // NO-OP
    }
}
