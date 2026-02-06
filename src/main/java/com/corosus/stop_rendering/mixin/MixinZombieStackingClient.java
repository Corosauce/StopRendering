package com.corosus.stop_rendering.mixin;

import com.corosus.stop_rendering.config.ConfigFeatures;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.eventbus.api.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//@Mixin(targets = "com.corosus.stop_rendering.HordeTickProcedure")
@Mixin(targets = "net.mcreator.horde_hoard.procedures.HordeTickProcedure")
//@Mixin(HordeTickProcedure.class)
public abstract class MixinZombieStackingClient {

    @Inject(method = "Lnet/mcreator/horde_hoard/procedures/HordeTickProcedure;execute(Lnet/minecraftforge/eventbus/api/Event;Lnet/minecraft/world/level/LevelAccessor;DDDLnet/minecraft/world/entity/Entity;)V", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void execute(Event event, LevelAccessor world, double x, double y, double z, Entity entity, CallbackInfo ci) {
        if (ConfigFeatures.mod_EnhancedHordes_disableClientHordeTickProcedure && world.isClientSide()) {
            ci.cancel();
        }
    }
}
