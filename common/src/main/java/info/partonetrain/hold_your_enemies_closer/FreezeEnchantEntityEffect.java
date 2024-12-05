package info.partonetrain.hold_your_enemies_closer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public record FreezeEnchantEntityEffect(LevelBasedValue duration) implements EnchantmentEntityEffect {
    public static final MapCodec<FreezeEnchantEntityEffect> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(LevelBasedValue.CODEC.fieldOf("duration").forGetter((freeze) -> {
            return freeze.duration;
        })).apply(instance, FreezeEnchantEntityEffect::new);
    });

    public FreezeEnchantEntityEffect(LevelBasedValue duration) {
        this.duration = duration;
    }

    //the duration in JSON is of seconds; duration in ticks is multiplied by 20
    public void apply(@NotNull ServerLevel level, int enchantmentLevel, @NotNull EnchantedItemInUse item, @NotNull Entity entity, @NotNull Vec3 origin) {
        int durationAdded = (int) (this.duration.calculate(enchantmentLevel) * level.tickRateManager().tickrate());
        if(entity instanceof LivingEntity le){
            le.setTicksFrozen(le.getTicksFrozen() + durationAdded);
            //Constants.LOG.info(String.valueOf(le.getTicksFrozen()));
        }
        else if (entity instanceof AbstractArrow arrow){
            IFrostArrow ifa = (IFrostArrow) arrow;
            ifa.hold_your_enemies_closer$setFreezeTicks(durationAdded);
        }
    }

    public @NotNull MapCodec<FreezeEnchantEntityEffect> codec() {
        return CODEC;
    }

    public LevelBasedValue duration() {
        return this.duration;
    }
}
