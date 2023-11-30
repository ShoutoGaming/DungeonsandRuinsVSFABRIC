package net.kaupenjoe.tutorialmod.entity.client;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.entity.custom.LivingTableEntity;
import net.kaupenjoe.tutorialmod.entity.custom.WraithEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WraithRenderer extends GeoEntityRenderer<WraithEntity> {
    public WraithRenderer(EntityRendererFactory.Context renderManager) {super(renderManager, new WraithModel());}

    @Override
    public Identifier getTextureLocation(WraithEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "textures/entity/wraith.png");
    }

    @Override
    public void render(WraithEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
