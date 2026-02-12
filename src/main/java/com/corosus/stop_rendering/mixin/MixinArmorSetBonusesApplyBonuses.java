package com.corosus.stop_rendering.mixin;

import com.corosus.stop_rendering.StopRendering;
import com.corosus.stop_rendering.config.ConfigFeatures;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.eventbus.api.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.dotcode.asb.ModUtils;

@Mixin(targets = "uk.co.dotcode.asb.ModUtils")
//@Mixin(ModUtils.class)
public abstract class MixinArmorSetBonusesApplyBonuses {

    @Inject(method = "checkAndApplyBonusesTo", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void execute(LivingEntity source, LivingEntity target, String interactionType, CallbackInfo ci) {
        if (!ConfigFeatures.modActive) return;
        if (ConfigFeatures.mod_ArmorSetBonuses_disableClientBonusApplyingOnNonPlayers && source.level().isClientSide() && !(source instanceof Player)) {
            if (!StopRendering.canProcessEntity(source.getType())) {
                ci.cancel();
            }
        }
    }
}
