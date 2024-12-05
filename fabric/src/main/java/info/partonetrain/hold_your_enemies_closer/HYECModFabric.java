package info.partonetrain.hold_your_enemies_closer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

public class HYECModFabric implements ModInitializer {

    public static Holder<Attribute> SWIM_SPEED;
    public static Holder<Attribute> FREEZING_TIME;

    @Override
    public void onInitialize() {
        CommonClass.init();

        Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "freeze"), FreezeEnchantEntityEffect.CODEC);
        LootTableEvents.MODIFY.register((id, supplier, source, provider) -> {
                if (source.isBuiltin() && Constants.InjectedLootTables.contains(id)) {
                    //determine resourcelocation of hyec inject loottable from id
                    ResourceLocation injectId = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "inject/" + id.location().getPath());
                    ResourceKey<LootTable> key = ResourceKey.create(Registries.LOOT_TABLE, injectId);
                    //add a pool with the hyec loottable
                    supplier.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(key)));
                }
            }
        );
    }


}
