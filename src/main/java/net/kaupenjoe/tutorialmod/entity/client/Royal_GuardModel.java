package net.kaupenjoe.tutorialmod.entity.client;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.entity.custom.Royal_GuardEntity;
import net.kaupenjoe.tutorialmod.entity.custom.TigerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class Royal_GuardModel extends GeoModel<Royal_GuardEntity> {
    @Override
    public Identifier getModelResource(Royal_GuardEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "geo/royal_guard.geo.json");
    }

    @Override
    public Identifier getTextureResource(Royal_GuardEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "textures/entity/royal_guard.png");
    }

    @Override
    public Identifier getAnimationResource(Royal_GuardEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "animations/royal_guard.animation.json");
    }

    @Override
    public void setCustomAnimations(Royal_GuardEntity animatable, long instanceId, AnimationState<Royal_GuardEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
