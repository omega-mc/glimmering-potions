package draylar.glimmeringpotions;

import draylar.glimmeringpotions.client.renderer.PotionStandBlockEntityRenderer;
import draylar.glimmeringpotions.registry.Blocks;
import draylar.glimmeringpotions.registry.Entities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class GlimmeringPotionsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.INSTANCE.register(
                Entities.POTION_STAND,
                PotionStandBlockEntityRenderer::new
        );

        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.POTION_STAND, RenderLayer.getCutout());
    }
}
