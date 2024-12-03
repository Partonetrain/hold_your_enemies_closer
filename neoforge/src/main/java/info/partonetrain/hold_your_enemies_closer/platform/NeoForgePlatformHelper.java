package info.partonetrain.hold_your_enemies_closer.platform;

import info.partonetrain.hold_your_enemies_closer.platform.services.IPlatformHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public float widenedGetKnockback(LivingEntity entity, Entity attacker, DamageSource damageSource) {
        return entity.getKnockback(attacker, damageSource);
    }
}