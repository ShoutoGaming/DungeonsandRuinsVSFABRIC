package net.kaupenjoe.tutorialmod.entity.client;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.entity.custom.GreatHunterEntity;
import net.kaupenjoe.tutorialmod.entity.custom.LivingTableEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LivingTableRenderer extends GeoEntityRenderer<LivingTableEntity> {
    public LivingTableRenderer(EntityRendererFactory.Context renderManager) {super(renderManager, new LivingTableModel());}

    @Override
    public Identifier getTextureLocation(LivingTableEntity animatable) {
        return new Identifier(TutorialMod.MOD_ID, "textures/entity/livingtable.png");
    }

    @Override
    public void render(LivingTableEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
