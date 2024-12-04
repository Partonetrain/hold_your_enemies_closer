package info.partonetrain.hold_your_enemies_closer;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Constants {

	public static final String MOD_ID = "hold_your_enemies_closer";
	public static final String MOD_NAME = "Hold Your Enemies Closer";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

	public static final ResourceKey<Enchantment> AUTO_SMELT = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MOD_ID, "auto_smelt"));

	public static List<ResourceKey<LootTable>> InjectedLootTables = List.of(
			BuiltInLootTables.SHIPWRECK_TREASURE,
			BuiltInLootTables.BURIED_TREASURE,
			BuiltInLootTables.BASTION_TREASURE
	);
}