package net.hexagonelle.applesaplings.content;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

import java.util.List;

import static net.hexagonelle.applesaplings.content.registers.ItemRegistry.ITEM_MAP;

@EventBusSubscriber(modid = AppleSaplings.MODID)
public class ModEvents {

	@SubscribeEvent
	public static void addWanderingTrades(WandererTradesEvent event) {
		List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();

		genericTrades.add(
			(entity, randomSource) -> new MerchantOffer(
				new ItemCost(Items.EMERALD, 1),
				new ItemStack(ITEM_MAP.get("orange_sapling").get(), 1),
				1, 10, 0.2f)
		);
	}

}
