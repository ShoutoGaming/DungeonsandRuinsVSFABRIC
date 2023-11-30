package net.kaupenjoe.tutorialmod.block.entity.client;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.entity.AnimatedBlockEntity;
import net.kaupenjoe.tutorialmod.block.entity.LootChestBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class LootchestBlockModel extends GeoModel<LootChestBlockEntity> {
    @Override
    public Identifier getModelResource(LootChestBlockEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "geo/lootchest.geo.json");
    }

    @Override
    public Identifier getTextureResource(LootChestBlockEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "textures/block/lootchest.png");
    }

    @Override
    public Identifier getAnimationResource(LootChestBlockEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "animations/lootchest.animation.json");
    }
}
