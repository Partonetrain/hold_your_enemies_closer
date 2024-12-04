package info.partonetrain.hold_your_enemies_closer.mixin;

import info.partonetrain.hold_your_enemies_closer.Constants;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

//This is here if, for some reason, you want to generate loot tables with actual support for auto_smelt
//auto smelt automatically works by platform-dependent mixins
@Mixin(BlockLootSubProvider.class)
public class BlockLootSubProviderMixin {
    /*
    @Unique
    public final LootItemCondition.Builder hold_your_enemies_closer$shouldSmeltLoot() {
        BlockLootSubProvider self = (BlockLootSubProvider)(Object)(this);
        HolderLookup.RegistryLookup<Enchantment> registrylookup = self.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return MatchTool.toolMatches(
                ItemPredicate.Builder.item()
                        .withSubPredicate(
                                ItemSubPredicates.ENCHANTMENTS,
                                ItemEnchantmentsPredicate.enchantments(
                                        List.of(new EnchantmentPredicate(registrylookup.getOrThrow(Constants.AUTO_SMELT), MinMaxBounds.Ints.atLeast(1)))
                                )
                        )
        );
    }
     */
}
