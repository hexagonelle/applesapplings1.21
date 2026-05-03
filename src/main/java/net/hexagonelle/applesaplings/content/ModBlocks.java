package net.hexagonelle.applesaplings.content;

import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.*;

public class ModBlocks {

	// REGISTER THE NEW BLOCKS //

	public static void init() {
//		registerBlockWithItem(
//			"applewood_planks",
//			"Applewood Planks",
//			BlockSuppliers::createPlanks,
//			BLOCK_WITH_ITEM,
//			"applesaplings_tab"
//		);

		registerWoodSet(
			"applewood",
			"Applewood",
			"applesaplings_tab"
		);
	}

}
