package net.hexagonelle.applesaplings.content;

import net.hexagonelle.applesaplings.content.suppliers.ItemSuppliers;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import static net.hexagonelle.applesaplings.content.registers.ItemRegistry.*;

public class ModItems {
	public static void init() {
		registerBoatItem("apple","Apple", ModBoat.Type.APPLE,"applesaplings_tab");
		registerChestBoatItem("apple","Apple",ModBoat.Type.APPLE,"applesaplings_tab");

		registerFruitItem("orange","Orange","applesaplings_tab");
		registerBoatItem("orange","Orange", ModBoat.Type.ORANGE,"applesaplings_tab");
		registerChestBoatItem("orange","Orange", ModBoat.Type.ORANGE,"applesaplings_tab");
	}
}
