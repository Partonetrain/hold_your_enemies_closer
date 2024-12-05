package info.partonetrain.hold_your_enemies_closer;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Constants.MOD_ID)
public class HYECModNeoforge {

    private static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Constants.MOD_ID);
    public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<FreezeEnchantEntityEffect>> FREEZE =
            ENTITY_ENCHANTMENT_EFFECTS.register("freeze", () -> FreezeEnchantEntityEffect.CODEC);
    //wew that's a lotta work for something that takes 1 line in fabric
    //I'm sure there's a good reason for (neo)forge freezing the vanilla registry but idk what that is

    private static final DeferredRegister<Attribute> ATTRIBUTES =
            DeferredRegister.create(Registries.ATTRIBUTE, Constants.MOD_ID);
    public static final DeferredHolder<Attribute, Attribute> FREEZING_TIME =
        ATTRIBUTES.register("freezing_time", () -> Constants.FREEZING_TIME);

    public HYECModNeoforge(IEventBus eventBus) {
        CommonClass.init();
        NeoForge.EVENT_BUS.addListener(HYECEvents::onBlockDrops);

        CommonClass.freezingTimeHolder = FREEZING_TIME;

        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
        ATTRIBUTES.register(eventBus);
    }

}