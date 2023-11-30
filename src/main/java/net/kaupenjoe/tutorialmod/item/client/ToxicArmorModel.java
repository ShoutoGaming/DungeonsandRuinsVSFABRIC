package net.kaupenjoe.tutorialmod.item.client;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.item.custom.AmethystArmorItem;
import net.kaupenjoe.tutorialmod.item.custom.ToxicArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ToxicArmorModel extends GeoModel<ToxicArmorItem> {
    @Override
    public Identifier getModelResource(ToxicArmorItem animatable) {
        return new Identifier(TutorialMod.MOD_ID, "geo/toxic_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(ToxicArmorItem animatable) {
        return new Identifier(TutorialMod.MOD_ID, "textures/armor/toxic_armor.png");
    }

    @Override
    public Identifier getAnimationResource(ToxicArmorItem animatable) {
        return new Identifier(TutorialMod.MOD_ID, "animations/toxic_armor.animation.json");
    }
}
