package net.hexagonelle.applesaplings.blocks;

import static net.hexagonelle.applesaplings.blocks.BlockRegistry.registerBlockWithItem;
import static net.hexagonelle.applesaplings.blocks.BlockStateMappings.blockStateMethods.*;

public class ModBlocks {

	// REGISTER THE NEW BLOCKS //

	public static void init() {
		registerBlockWithItem(
			"applewood_planks",
			BlockSuppliers::createPlanks,
			BLOCK_WITH_ITEM
		);
	}

}
