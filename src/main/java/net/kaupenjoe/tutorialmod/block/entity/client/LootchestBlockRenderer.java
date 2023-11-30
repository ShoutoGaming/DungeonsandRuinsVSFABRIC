package net.kaupenjoe.tutorialmod.block.entity.client;

import net.kaupenjoe.tutorialmod.block.entity.AnimatedBlockEntity;
import net.kaupenjoe.tutorialmod.block.entity.LootChestBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class LootchestBlockRenderer extends GeoBlockRenderer<LootChestBlockEntity> {
    public LootchestBlockRenderer(BlockEntityRendererFactory.Context context) {

        super(new LootchestBlockModel());
    }
}
