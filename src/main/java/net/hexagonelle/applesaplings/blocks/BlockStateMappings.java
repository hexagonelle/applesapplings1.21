package net.hexagonelle.applesaplings.blocks;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BlockStateMappings {

	public enum blockStateMethods{
		BLOCK_ITEM,
		BLOCK_WITH_ITEM,
		SAPLING_BLOCK
	}

	public static final Map<String, blockStateMethods> blockBlockstateMap = new HashMap<>();

	// registers a block and places the corresponding DeferredBlock in the HashMap by blockId
	public static void assignBlockstate(
		String blockId,
		blockStateMethods method
	) {
		blockBlockstateMap.put(blockId, method);
	}
}
