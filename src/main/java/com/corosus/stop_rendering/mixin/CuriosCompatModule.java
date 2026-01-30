package com.corosus.stop_rendering.mixin;

import com.corosus.stop_rendering.StopRendering;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

/*@Mixin(blusunrize.immersiveengineering.common.util.compat.CuriosCompatModule.class)
public abstract class CuriosCompatModule<T extends LivingEntity, M extends EntityModel<T>> {

    //TODO: FIX REMAP
    @Inject(method = "getCuriosIfVisible", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void getCuriosIfVisible(LivingEntity living, top.theillusivec4.curios.api.SlotTypePreset slot, Predicate<ItemStack> predicate, CallbackInfoReturnable<ItemStack> cir) {
        StopRendering.test();
        if (!StopRendering.canProcessEntity(living.getType())) {
            cir.setReturnValue(ItemStack.EMPTY);
        }
    }
}*/
