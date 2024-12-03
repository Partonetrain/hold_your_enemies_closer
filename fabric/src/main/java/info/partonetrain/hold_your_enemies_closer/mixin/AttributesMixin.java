package info.partonetrain.hold_your_enemies_closer.mixin;

import info.partonetrain.hold_your_enemies_closer.Constants;
import info.partonetrain.hold_your_enemies_closer.HYECModFabric;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Attributes.class)
public class AttributesMixin {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void hold_your_enemies_closer$addSwimSpeedAttrToFabric(CallbackInfo ci){
        HYECModFabric.SWIM_SPEED = Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "swim_speed"), new RangedAttribute("attribute.name.hold_your_enemies_closer.swimming_speed", 1.0, 0.0, 1024.0).setSyncable(true));
    }
}
