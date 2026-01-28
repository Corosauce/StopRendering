package com.corosus.stop_rendering.mixin;

import com.corosus.stop_rendering.StopRendering;
import com.corosus.stop_rendering.config.ConfigFeatures;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer<T extends LivingEntity, M extends EntityModel<T>> {

    @Redirect(
        method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/entity/layers/RenderLayer;render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/Entity;FFFFFF)V"
        )
    )
    public void redirectLayerRender(RenderLayer renderLayer, PoseStack poseStack,
                                     MultiBufferSource bufferSource,
                                     int packedLight,
                                     Entity entity,
                                     float limbSwing,
                                     float limbSwingAmount,
                                     float partialTicks,
                                     float ageInTicks,
                                     float netHeadYaw,
                                     float headPitch) {
        if (renderLayer.getClass().getCanonicalName().equals(ConfigFeatures.curiosClassPath)) {
            if (!StopRendering.canProcessEntity(entity.getType())) {
                return;
            }
        }
        //(LivingEntity)entity is NOT REDUNDANT, if removed it crashes
        //T erasure into Entity / LivingEntity, mixin time it expects Entity class in the @Redirect, runtime it expects LivingEntity, this satisfies both scenarios,
        renderLayer.render(poseStack, bufferSource, packedLight, (LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
    }
}
