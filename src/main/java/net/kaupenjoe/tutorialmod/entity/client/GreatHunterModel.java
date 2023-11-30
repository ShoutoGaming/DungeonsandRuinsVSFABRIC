package net.kaupenjoe.tutorialmod.entity.client;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.entity.custom.GreatHunterEntity;
import net.kaupenjoe.tutorialmod.entity.custom.Royal_GuardEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GreatHunterModel extends GeoModel<GreatHunterEntity> {
    @Override
    public Identifier getModelResource(GreatHunterEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "geo/great_hunter.geo.json");
    }

    @Override
    public Identifier getTextureResource(GreatHunterEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "textures/entity/great_hunter.png");
    }

    @Override
    public Identifier getAnimationResource(GreatHunterEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "animations/great_hunter.animation.json");
    }

    @Override
    public void setCustomAnimations(GreatHunterEntity animatable, long instanceId, AnimationState<GreatHunterEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
