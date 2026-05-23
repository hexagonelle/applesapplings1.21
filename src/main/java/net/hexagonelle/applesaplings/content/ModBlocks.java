package net.hexagonelle.applesaplings.content;

import net.hexagonelle.applesaplings.content.registers.ItemRegistry;
import net.minecraft.world.item.Items;

import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.*;
import static net.hexagonelle.applesaplings.content.registers.ItemRegistry.ITEM_MAP;

public class ModBlocks {

	// REGISTER THE NEW BLOCKS //

	public static void init() {

		registerWoodSet(
			"apple",
			"Apple",
			ModTreeGrower.APPLE,
			true,
			"applesaplings_tab"
		);
//		System.out.println(ITEM_MAP.get("orange").get());
		registerWoodSet(
			"orange",
			"Orange",
			ModTreeGrower.ORANGE,
			false,
			"applesaplings_tab"
		);
	}

}
