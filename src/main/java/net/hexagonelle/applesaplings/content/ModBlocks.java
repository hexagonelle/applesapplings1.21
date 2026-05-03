package net.hexagonelle.applesaplings.content;

import net.minecraft.world.item.Items;

import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.*;

public class ModBlocks {

	// REGISTER THE NEW BLOCKS //

	public static void init() {

		registerWoodSet(
			"applewood",
			"Applewood",
			Items.APPLE,
			"applesaplings_tab"
		);
	}

}
