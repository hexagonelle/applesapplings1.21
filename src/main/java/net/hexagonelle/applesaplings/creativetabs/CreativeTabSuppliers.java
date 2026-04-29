package net.hexagonelle.applesaplings.creativetabs;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.items.ItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class CreativeTabSuppliers {

	public static CreativeModeTab createCreativeTab(
		String tabId,
		String iconItemId
	){
		String fullTabId = "creativetab." + AppleSaplings.MODID + "." + tabId;
		Item iconItem = ItemRegistry.ITEM_MAP.get(iconItemId).get();
		return CreativeModeTab.builder()
			.icon(() -> new ItemStack(iconItem))
			.title(Component.translatable(fullTabId))
			.displayItems(
				(parameters, output) -> CreativeTabRegistry.ITEM_MODTAB_MAP.forEach(
					(itemRegistryObject, creativeTabKey) -> {
						if (Objects.equals(creativeTabKey, tabId))
							output.accept(itemRegistryObject.get());
					}
				)
			).build();
	}

}
