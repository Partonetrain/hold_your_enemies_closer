package info.partonetrain.hold_your_enemies_closer.mixin;

import info.partonetrain.hold_your_enemies_closer.CommonClass;
import info.partonetrain.hold_your_enemies_closer.Constants;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderedSnowBlockMixin {
    @Inject(method = "canEntityWalkOnPowderSnow", at=@At("HEAD"), cancellable = true)
    private static void hold_your_enemies_closer$iceProtectionWalksOnPowder(Entity entity, CallbackInfoReturnable<Boolean> cir){
        if(entity instanceof LivingEntity le){
            //check frost protection
            double freezingTime = le.getAttributeValue(CommonClass.freezingTimeHolder);
            if(freezingTime <= 0.5F){
                cir.setReturnValue(true);
                cir.cancel();
            }

            //if not, check if any item has enchantment that is tagged
            for(ItemStack stack : le.getArmorSlots()){
                if(EnchantmentHelper.hasTag(stack, Constants.ALLOWS_POWDER_SNOW_WALKING)){
                    cir.setReturnValue(true);
                    cir.cancel();
                }
            }
        }
    }
}
