package info.partonetrain.hold_your_enemies_closer;

import info.partonetrain.hold_your_enemies_closer.compat.CombatNouveauCompatHandler;
import info.partonetrain.hold_your_enemies_closer.platform.Services;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CommonClass {

    public static Holder<Attribute> freezingTimeHolder;

    public static void init() {
        if(Services.PLATFORM.isModLoaded("combatnouveau")){
            CombatNouveauCompatHandler.checkConfig();
        }
    }

    public static AutosmeltOutcome autoSmelt(ItemStack original, ItemStack tool, @Nullable Entity entity, @Nullable BlockState state) {
        MutableInt xp = new MutableInt(0);
        boolean hasEnchant = EnchantmentHelper.hasTag(tool, EnchantmentTags.SMELTS_LOOT);

        if (entity instanceof LivingEntity livingEntity
                && livingEntity.level() instanceof ServerLevel sl
                && hasEnchant
                && state != null)
        {
            Optional<RecipeHolder<SmeltingRecipe>> recipe = sl.getRecipeManager()
                    .getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(original), livingEntity.level());
            if (recipe.isPresent()) {
                ItemStack smeltingResult = Services.PLATFORM.getSmeltRecipeResult(recipe);
                if (!smeltingResult.isEmpty()) {
                    int xpToReward = 0;
                    float xpFromRecipe = recipe.get().value().experience;
                    //recipes like smelting Iron Ore "drop" 0.7xp when smelted in a furnace.
                    //since in this case there would be a 70% chance to drop 1 xp
                    //there should be a chance that it drops 1 point when autosmelted, too.
                    if(xpFromRecipe < 1.0F){
                        float chance = livingEntity.getRandom().nextFloat();
                        if(chance < xpFromRecipe){
                            xpToReward = 1;
                        }
                    }
                    else{
                        xpToReward = (int) xpFromRecipe;
                    }
                    //technically this means that any recipe that drops more than 1 xp just gets rounded
                    //down, but... meh. Consider it a balance thing.

                    xp.setValue(xpToReward);
                    ItemStack newStack = smeltingResult.copyWithCount(smeltingResult.getCount() * original.getCount());
                    return new AutosmeltOutcome(newStack, xp);
                }
            }

        }
        return new AutosmeltOutcome(original, new MutableInt(0));
    }

}