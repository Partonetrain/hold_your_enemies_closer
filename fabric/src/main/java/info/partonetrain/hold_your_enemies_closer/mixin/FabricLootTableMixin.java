package info.partonetrain.hold_your_enemies_closer.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import info.partonetrain.hold_your_enemies_closer.AutosmeltOutcome;
import info.partonetrain.hold_your_enemies_closer.CommonClass;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.mutable.MutableInt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LootTable.class)
public class FabricLootTableMixin {
    @ModifyReturnValue(method = "getRandomItems(Lnet/minecraft/world/level/storage/loot/LootContext;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;", at = @At("RETURN"))
    public ObjectArrayList<ItemStack> hold_your_enemies_closer$modifyBlockDrops(ObjectArrayList<ItemStack> original, LootContext context) {
        if (context.hasParameter(LootContextParams.TOOL) && context.hasParameter(LootContextParams.BLOCK_STATE) && context.hasParameter(LootContextParams.ORIGIN)) {
            Vec3 origin = context.getParameter(LootContextParams.ORIGIN);
            MutableInt xp = new MutableInt(0);
            ObjectArrayList<ItemStack> result = new ObjectArrayList<>(original.size());
            for (ItemStack stack : original) {
                AutosmeltOutcome outcome = CommonClass.autoSmelt(stack,
                                context.getParameter(LootContextParams.TOOL),
                                context.getParameter(LootContextParams.THIS_ENTITY),
                                context.getParameter(LootContextParams.BLOCK_STATE));
                result.add(outcome.stack);
                xp.setValue(outcome.xp);
            }
            ExperienceOrb.award(context.getLevel(), origin, xp.intValue());
            return result;
        }
        return original;
    }
}
