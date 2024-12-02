package info.partonetrain.hold_your_enemies_closer.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(Mob.class)
public class MobMixin {
    @Inject(method = "doHurtTarget", at= @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/entity/Mob;getKnockback(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;)F"))
    public void hold_your_enemies_closer$backwardsKnockback(ServerLevel serverLevel, Entity entity, CallbackInfoReturnable<Boolean> cir){
        Mob self = (Mob)(Object)this;
        ItemStack itemstack = self.getWeaponItem();
        DamageSource damagesource = Optional.ofNullable(itemstack.getItem().getDamageSource(self)).orElse(self.damageSources().mobAttack(self));

        float magnitude = self.getKnockback(entity, damagesource);
        if (magnitude < 0.0F && entity instanceof LivingEntity livingentity) {
            livingentity.knockback(
                    (double)(magnitude * -0.5F),
                    (double) Mth.sin(self.getYRot() * (float) (Math.PI / 180.0)),
                    (double)(-Mth.cos(self.getYRot() * (float) (Math.PI / 180.0)))
            );

            self.setDeltaMovement(self.getDeltaMovement().multiply(0.6, 1.0, 0.6));
        }
    }
}
