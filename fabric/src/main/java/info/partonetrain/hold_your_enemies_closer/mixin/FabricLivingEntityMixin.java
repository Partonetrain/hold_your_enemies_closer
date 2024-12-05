package info.partonetrain.hold_your_enemies_closer.mixin;

import info.partonetrain.hold_your_enemies_closer.HYECModFabric;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class FabricLivingEntityMixin {

    //add custom attributes
    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void hold_your_enemies_closer$addSwimSpeedAttribute(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue().add(HYECModFabric.SWIM_SPEED);
        cir.getReturnValue().add(HYECModFabric.FREEZING_TIME);
    }

    //make swim speed attribute work
    @ModifyArg(method = "travelInFluid", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;moveRelative(FLnet/minecraft/world/phys/Vec3;)V"), index = 0)
    private float hold_your_enemies_closer$implSwimSpeedAttribute(float original){
        LivingEntity self = (LivingEntity)(Object)(this);
        double modifier = 1.0F;
        AttributeInstance instance = self.getAttribute(HYECModFabric.SWIM_SPEED);
        if (instance == null) {
            return original;
        }
        else{
            modifier = self.getAttributeValue(HYECModFabric.SWIM_SPEED);
        }

        original = (float) (original * modifier);
        return original;
    }

}
