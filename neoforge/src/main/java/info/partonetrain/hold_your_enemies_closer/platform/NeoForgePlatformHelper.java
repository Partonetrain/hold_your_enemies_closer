package info.partonetrain.hold_your_enemies_closer.platform;

import info.partonetrain.hold_your_enemies_closer.platform.services.IPlatformHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

import java.util.Optional;

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

    @Override
    public ItemStack getSmeltRecipeResult(Optional<RecipeHolder<SmeltingRecipe>> recipe) {
        return recipe.get().value().result;
    }
}