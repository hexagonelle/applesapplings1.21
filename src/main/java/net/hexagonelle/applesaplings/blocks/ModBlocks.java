package net.hexagonelle.applesaplings.blocks;

import static net.hexagonelle.applesaplings.blocks.BlockRegistry.*;

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
