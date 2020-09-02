package draylar.glimmeringpotions.trade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.LiteralText;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PotionTrade implements TradeOffers.Factory {

    private final ItemStack firstPurchaseItem;
    private ItemStack secondPurchaseItem = ItemStack.EMPTY;
    private final int maxEffectLevel;
    private final int maxEffects;
    private final int maxTradeUsages;
    private final int rewardedExp;
    private final float priceMultiplier;

    public PotionTrade(int emeralds, int maxEffectLevel, int maxEffects, int maxTradeUsages, int rewardedExp, float priceMultiplier) {
        this.firstPurchaseItem = new ItemStack(Items.EMERALD, emeralds);
        this.maxEffectLevel = maxEffectLevel;
        this.maxEffects = maxEffects;
        this.maxTradeUsages = maxTradeUsages;
        this.rewardedExp = rewardedExp;
        this.priceMultiplier = priceMultiplier;
    }

    public PotionTrade(int emeralds, ItemStack secondPurchaseItem, int maxEffectLevel, int maxEffects, int maxTradeUsages, int rewardedExp, float priceMultiplier) {
        this.firstPurchaseItem = new ItemStack(Items.EMERALD, emeralds);
        this.secondPurchaseItem = secondPurchaseItem;
        this.maxEffectLevel = maxEffectLevel;
        this.maxEffects = maxEffects;
        this.maxTradeUsages = maxTradeUsages;
        this.rewardedExp = rewardedExp;
        this.priceMultiplier = priceMultiplier;
    }

    public PotionTrade(ItemStack firstPurchaseItem, int maxEffectLevel, int maxEffects, int maxTradeUsages, int rewardedExp, float priceMultiplier) {
        this.firstPurchaseItem = firstPurchaseItem;
        this.maxEffectLevel = maxEffectLevel;
        this.maxEffects = maxEffects;
        this.maxTradeUsages = maxTradeUsages;
        this.rewardedExp = rewardedExp;
        this.priceMultiplier = priceMultiplier;
    }

    public PotionTrade(ItemStack firstPurchaseItem, ItemStack secondPurchaseItem, int maxEffectLevel, int maxEffects, int maxTradeUsages, int rewardedExp, float priceMultiplier) {
        this.firstPurchaseItem = firstPurchaseItem;
        this.secondPurchaseItem = secondPurchaseItem;
        this.maxEffectLevel = maxEffectLevel;
        this.maxEffects = maxEffects;
        this.maxTradeUsages = maxTradeUsages;
        this.rewardedExp = rewardedExp;
        this.priceMultiplier = priceMultiplier;
    }

    @Override
    public TradeOffer create(Entity entity, Random random) {
        List<StatusEffect> registeredEffects = Registry.STATUS_EFFECT.stream().collect(Collectors.toList());
        List<StatusEffectInstance> selectedEffects = new ArrayList<>();

        for(int i = 0; i < maxEffects; i++) {
            StatusEffect selectedEffect = registeredEffects.get(random.nextInt(registeredEffects.size()));
            registeredEffects.remove(selectedEffect);
            StatusEffectInstance newEffect = new StatusEffectInstance(selectedEffect, 20 * 30 * random.nextInt(16), random.nextInt(maxEffectLevel));
            selectedEffects.add(newEffect);
        }

        ItemStack result = PotionUtil.setCustomPotionEffects(new ItemStack(Items.POTION), selectedEffects);
        result.setCustomName(new LiteralText("Magical Potion"));

        return new TradeOffer(
                firstPurchaseItem,
                secondPurchaseItem,
                result,
                maxTradeUsages,
                rewardedExp,
                priceMultiplier
        );
    }
}
