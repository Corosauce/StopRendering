package com.corosus.stop_rendering.mixin;

import com.corosus.stop_rendering.config.ConfigFeatures;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "top.theillusivec4.curios.common.event.CuriosEventHandler")
//@Mixin(CuriosEventHandler.class)
public abstract class MixinClientCuriosTick {

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Ltop/theillusivec4/curios/api/CuriosApi;getCuriosInventory(Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraftforge/common/util/LazyOptional;"), cancellable = true, remap = false)
    private void tick(LivingEvent.LivingTickEvent evt, CallbackInfo ci) {
        if (ConfigFeatures.mod_Curios_disableOnNonPlayers && evt.getEntity().level().isClientSide() && !(evt.getEntity() instanceof Player)) {
            //StopRendering.LOGGER.error("cancel for non player " + evt.getEntity().getName().getString());
            ci.cancel();
        }
    }
}
