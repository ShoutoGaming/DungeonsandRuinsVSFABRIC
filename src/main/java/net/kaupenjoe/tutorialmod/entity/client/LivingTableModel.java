package net.kaupenjoe.tutorialmod.entity.client;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.entity.custom.GreatHunterEntity;
import net.kaupenjoe.tutorialmod.entity.custom.LivingTableEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class LivingTableModel extends GeoModel<LivingTableEntity> {
    @Override
    public Identifier getModelResource(LivingTableEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "geo/livingtable.geo.json");
    }

    @Override
    public Identifier getTextureResource(LivingTableEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "textures/entity/livingtable.png");
    }

    @Override
    public Identifier getAnimationResource(LivingTableEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "animations/livingtable.animation.json");
    }

    @Override
    public void setCustomAnimations(LivingTableEntity animatable, long instanceId, AnimationState<LivingTableEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
