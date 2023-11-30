package net.kaupenjoe.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.block.entity.ModBlockEntities;
import net.kaupenjoe.tutorialmod.block.entity.client.AnimatedBlockRenderer;
import net.kaupenjoe.tutorialmod.block.entity.client.LootchestBlockRenderer;
import net.kaupenjoe.tutorialmod.entity.ModEntities;
import net.kaupenjoe.tutorialmod.entity.client.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RED_MAPLE_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RED_MAPLE_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VALVULE, RenderLayer.getCutout());


        EntityRendererRegistry.register(ModEntities.TIGER, TigerRenderer::new);
        EntityRendererRegistry.register(ModEntities.GREAT_HUNTER, GreatHunterRenderer::new);
        EntityRendererRegistry.register(ModEntities.ROYAL_GUARD, Royal_GuardRenderer::new);
        EntityRendererRegistry.register(ModEntities.LIVINGTABLE, LivingTableRenderer::new);
        EntityRendererRegistry.register(ModEntities.WRAITH, WraithRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.ANIMATED_BLOCK_ENTITY, AnimatedBlockRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.LOOTCHEST_BLOCK_ENTITY, LootchestBlockRenderer::new);
         }
}
