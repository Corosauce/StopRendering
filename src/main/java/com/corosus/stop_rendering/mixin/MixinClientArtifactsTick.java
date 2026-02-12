package com.corosus.stop_rendering.mixin;

import artifacts.forge.event.ArtifactEventsForge;
import com.corosus.stop_rendering.StopRendering;
import com.corosus.stop_rendering.config.ConfigFeatures;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "artifacts.forge.event.ArtifactEventsForge")
//@Mixin(ArtifactEventsForge.class)
public abstract class MixinClientArtifactsTick {

    @Inject(method = "onLivingUpdate", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void tick(LivingEvent.LivingTickEvent evt, CallbackInfo ci) {
        if (!ConfigFeatures.modActive) return;
        if (ConfigFeatures.mod_Artifacts_disableOnNonPlayers && evt.getEntity().level().isClientSide() && !(evt.getEntity() instanceof Player)) {
            //StopRendering.LOGGER.error("cancel Artifact for non player " + evt.getEntity().getName().getString());
            if (!StopRendering.canProcessEntity(evt.getEntity().getType())) {
                ci.cancel();
            }
        }
    }
}
