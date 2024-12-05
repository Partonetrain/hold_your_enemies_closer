package info.partonetrain.hold_your_enemies_closer.mixin;

import info.partonetrain.hold_your_enemies_closer.CommonClass;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method= "knockback(DDD)V", at= @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/entity/LivingEntity;getAttributeValue(Lnet/minecraft/core/Holder;)D"))
    public void hold_your_enemies_closer$livingTakesBackwardsKnockback(double strength, double x, double z, CallbackInfo ci){
        LivingEntity self = (LivingEntity)(Object)(this);
        strength *= 1.0 - self.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
        if (strength <= 0.0) {
            self.hasImpulse = true;
            Vec3 vec3 = self.getDeltaMovement();

            while (x * x + z * z < 1.0E-5F) {
                x = (Math.random() - Math.random()) * 0.01;
                z = (Math.random() - Math.random()) * 0.01;
            }

            Vec3 vec31 = new Vec3(x, 0.0, z).normalize().scale(strength);
            self.setDeltaMovement(vec3.x / 2.0 - vec31.x, self.onGround() ? Math.min(0.4, vec3.y / 2.0 + strength) : vec3.y, vec3.z / 2.0 - vec31.z);
        }
    }

    @Inject(method = "aiStep()V", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setTicksFrozen(I)V", ordinal = 1))
    public void hold_your_enemies_closer$unfreezeFaster(CallbackInfo ci){
        LivingEntity self = (LivingEntity)(Object)(this);
        double freezingTime = self.getAttributeValue(CommonClass.freezingTimeHolder);
        int ticksFrozen = self.getTicksFrozen();

        if(ticksFrozen > 0 && freezingTime < 1){
            ticksFrozen = (int) (ticksFrozen * freezingTime);
            self.setTicksFrozen(ticksFrozen);
        }
    }

    @ModifyArg(method = "aiStep()V", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setTicksFrozen(I)V", ordinal = 1))
    public int hold_your_enemies_closer$useNewFrozenTicks(int original){
        LivingEntity self = (LivingEntity)(Object)(this);
        double freezingTime = self.getAttributeValue(CommonClass.freezingTimeHolder);
        if(freezingTime < 1){
            return self.getTicksFrozen();
        }
        else{
            return original;
        }

    }

}
