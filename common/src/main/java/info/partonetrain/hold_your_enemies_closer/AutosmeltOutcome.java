package info.partonetrain.hold_your_enemies_closer;

import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.mutable.MutableInt;

public class AutosmeltOutcome {
    public ItemStack stack;
    public MutableInt xp = new MutableInt(0);

    AutosmeltOutcome(ItemStack stack, MutableInt xp){
        this.stack = stack;
        this.xp = xp;
    }
}
