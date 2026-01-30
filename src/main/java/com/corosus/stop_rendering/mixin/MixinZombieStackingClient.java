package com.corosus.stop_rendering.mixin;

import com.corosus.stop_rendering.HordeTickProcedure;
import com.corosus.stop_rendering.StopRendering;
import com.corosus.stop_rendering.config.ConfigFeatures;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.eventbus.api.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

//@Mixin(targets = "com.corosus.stop_rendering.HordeTickProcedure")
@Mixin(targets = "net.mcreator.horde_hoard.procedures.HordeTickProcedure")
//@Mixin(HordeTickProcedure.class)
public abstract class MixinZombieStackingClient/* implements IMixinConfigPlugin*/ {

    /*@Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.contains("curios")) {
            // Check if the target class actually exists
            try {
                Class.forName(targetClassName, false, getClass().getClassLoader());
                return true;
            } catch (ClassNotFoundException e) {
                return false; // Don't apply mixin if target doesn't exist
            }
        }
        return false;
    }*/

    //@Inject(method = "Lcom/corosus/stop_rendering/HordeTickProcedure;execute(Lnet/minecraftforge/eventbus/api/Event;Lnet/minecraft/world/level/LevelAccessor;DDDLnet/minecraft/world/entity/Entity;)V", at = @At(value = "HEAD"), cancellable = true, remap = false)
    @Inject(method = "Lnet/mcreator/horde_hoard/procedures/HordeTickProcedure;execute(Lnet/minecraftforge/eventbus/api/Event;Lnet/minecraft/world/level/LevelAccessor;DDDLnet/minecraft/world/entity/Entity;)V", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void execute(Event event, LevelAccessor world, double x, double y, double z, Entity entity, CallbackInfo ci) {
        StopRendering.test();
        if (ConfigFeatures.test1 && world.isClientSide()) {
            StopRendering.cancel();
            ci.cancel();
        }
    }
}
