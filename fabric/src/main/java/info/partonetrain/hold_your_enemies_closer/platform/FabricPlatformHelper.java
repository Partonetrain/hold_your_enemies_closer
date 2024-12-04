package info.partonetrain.hold_your_enemies_closer.platform;

import info.partonetrain.hold_your_enemies_closer.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SmeltingRecipe;

import java.util.Optional;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
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
