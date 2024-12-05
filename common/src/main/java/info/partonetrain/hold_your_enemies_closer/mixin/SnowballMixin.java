package info.partonetrain.hold_your_enemies_closer.mixin;

import info.partonetrain.hold_your_enemies_closer.Constants;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Snowball.class)
public class SnowballMixin {
    @Inject(method = "onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V", at=@At("RETURN"))
    public void hold_your_enemies_closer$snowballFreeze(EntityHitResult result, CallbackInfo ci){
        int f = result.getEntity().getTicksFrozen();
        if(f <= 500){
            result.getEntity().setTicksFrozen(Math.min(f + 20, 500));
        }
        Constants.LOG.info(String.valueOf((result.getEntity().getTicksFrozen())));
    }
}
