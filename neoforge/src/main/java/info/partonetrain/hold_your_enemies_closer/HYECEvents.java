package info.partonetrain.hold_your_enemies_closer;

import net.minecraft.world.entity.item.ItemEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.List;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class HYECEvents {

    public static void onBlockDrops(BlockDropsEvent event) {
        List<ItemEntity> drops = event.getDrops();
        MutableInt experience = new MutableInt(0);
        for(ItemEntity ie : drops){
            AutosmeltOutcome outcome = CommonClass.autoSmelt(ie.getItem(),
                    event.getTool(),
                    event.getBreaker(),
                    event.getState());

            ie.setItem(outcome.stack);
            experience.setValue(outcome.xp);
        }
        event.setDroppedExperience(event.getDroppedExperience() + experience.getValue());
    }

    @SubscribeEvent
    public static void onEntityAttributeModification(EntityAttributeModificationEvent event){
        event.getTypes().forEach(e -> event.add(e, CommonClass.freezingTimeHolder));
    }
}
