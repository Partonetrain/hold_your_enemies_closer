package info.partonetrain.hold_your_enemies_closer.mixin;

import info.partonetrain.hold_your_enemies_closer.Constants;
import info.partonetrain.hold_your_enemies_closer.IFrostArrow;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin implements IFrostArrow {

    @Unique
    public int hold_your_enemies_closer$freezeTicks = 0;

    @Shadow public abstract ItemStack getWeaponItem();

    @Inject(method = "doKnockback", at=@At(value = "HEAD"), cancellable = true)
    public void hold_your_enemies_closer$projectileDealsBackwardsKnockback(LivingEntity entity, DamageSource damageSource, CallbackInfo ci){
        AbstractArrow self = (AbstractArrow)(Object)this;
        double d0 = (double)(
                self.getWeaponItem() != null && self.level() instanceof ServerLevel serverlevel
                        ? EnchantmentHelper.modifyKnockback(serverlevel, this.getWeaponItem(), entity, damageSource, 0.0F)
                        : 0.0F
        );
        if (d0 < 0.0) {
            double d1 = Math.max(0.0, 1.0 - entity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
            Vec3 vec3 = self.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale(d0 * 0.6 * d1);
            if (vec3.lengthSqr() > 0.0) {
                entity.push(vec3.x, 0.1, vec3.z);
            }
        }
        ci.cancel();
    }

    @Unique
    @Override
    public void hold_your_enemies_closer$setFreezeTicks(int freezeTicks){
        this.hold_your_enemies_closer$freezeTicks = freezeTicks;
    }

    @Inject(method = "doPostHurtEffects", at = @At("HEAD"))
    public void hold_your_enemies_closer$addFrozenTicks(LivingEntity target, CallbackInfo ci){
        if(hold_your_enemies_closer$freezeTicks > 0  && target.canFreeze()){
            target.setTicksFrozen(target.getTicksFrozen() + hold_your_enemies_closer$freezeTicks);
        }
    }
}
