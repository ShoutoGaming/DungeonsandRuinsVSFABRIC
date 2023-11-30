package net.kaupenjoe.tutorialmod.entity.client;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.entity.custom.GreatHunterEntity;
import net.kaupenjoe.tutorialmod.entity.custom.Royal_GuardEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GreatHunterRenderer extends GeoEntityRenderer<GreatHunterEntity> {
    public GreatHunterRenderer(EntityRendererFactory.Context renderManager) {super(renderManager, new GreatHunterModel());}

    @Override
    public Identifier getTextureLocation(GreatHunterEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "textures/entity/great_hunter.png");
    }

    @Override
    public void render(GreatHunterEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
