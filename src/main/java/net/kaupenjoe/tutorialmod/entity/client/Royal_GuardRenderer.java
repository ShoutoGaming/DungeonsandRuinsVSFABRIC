package net.kaupenjoe.tutorialmod.entity.client;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.entity.custom.Royal_GuardEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Royal_GuardRenderer extends GeoEntityRenderer<Royal_GuardEntity> {
    public Royal_GuardRenderer(EntityRendererFactory.Context renderManager) {super(renderManager, new Royal_GuardModel());}

    @Override
    public Identifier getTextureLocation(Royal_GuardEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "textures/entity/royal_guard.png");
    }

    @Override
    public void render(Royal_GuardEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
