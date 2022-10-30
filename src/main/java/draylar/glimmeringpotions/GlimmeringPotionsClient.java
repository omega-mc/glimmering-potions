package draylar.glimmeringpotions;

import draylar.glimmeringpotions.client.renderer.PotionStandBlockEntityRenderer;
import draylar.glimmeringpotions.registry.GPBlocks;
import draylar.glimmeringpotions.registry.GPEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class GlimmeringPotionsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(
                GPEntities.POTION_STAND,
                context -> new PotionStandBlockEntityRenderer()
        );

        BlockRenderLayerMap.INSTANCE.putBlock(GPBlocks.POTION_STAND, RenderLayer.getCutout());
    }
}
