package draylar.glimmeringpotions.mixin;

import draylar.glimmeringpotions.GlimmeringPotions;
import net.minecraft.entity.ai.brain.task.GiveGiftsToHeroTask;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GiveGiftsToHeroTask.class)
public abstract class GiveGiftsToHeroMixin {

    @Inject(method = "isHero", at = @At("HEAD"), cancellable = true)
    private void hasStatusEffect(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if(player.hasStatusEffect(GlimmeringPotions.FALSE_HEROISM)) {
            cir.setReturnValue(true);
        }
    }
}
