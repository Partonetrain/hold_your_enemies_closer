package info.partonetrain.hold_your_enemies_closer.mixin;

import info.partonetrain.hold_your_enemies_closer.compat.CombatNouveauCompatHandler;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "attack", at= @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/entity/player/Player;getKnockback(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;)F"))
    public void hold_your_enemies_closer$plareDealsBackwardsKnockback(Entity target, CallbackInfo ci){
        Player self = (Player)(Object)this;
        DamageSource damagesource = self.damageSources().playerAttack(self);
        boolean applySprintingBonus = self.isSprinting() && (self.getAttackStrengthScale(0.5F) > 0.9F);
        float kb = self.getKnockback(target, damagesource) + (applySprintingBonus ? 1.0F : 0.0F);
        if (kb < 0.0F) {
            if (target instanceof LivingEntity livingentity1) {
                livingentity1.knockback(
                        (double)(kb * 0.5F),
                        (double) Mth.sin(self.getYRot() * (float) (Math.PI / 180.0)),
                        (double)(-Mth.cos(self.getYRot() * (float) (Math.PI / 180.0)))
                );
            } else {
                target.push(
                        (double)(-Mth.sin(self.getYRot() * (float) (Math.PI / 180.0)) * kb * 0.5F),
                        0.1,
                        (double)(Mth.cos(self.getYRot() * (float) (Math.PI / 180.0)) * kb * 0.5F)
                );
            }

            self.setDeltaMovement(self.getDeltaMovement().multiply(0.6, 1.0, 0.6));
            //combat-nouveau has a config option that disable vanilla sprint stop after hit.
            //https://github.com/Fuzss/combatnouveau/blob/main/1.21.1/Common/src/main/java/fuzs/combatnouveau/mixin/PlayerMixin.java#L61-L68
            if(CombatNouveauCompatHandler.attackingStopsSprinting){
                self.setSprinting(false);
            }

        }
    }
}
