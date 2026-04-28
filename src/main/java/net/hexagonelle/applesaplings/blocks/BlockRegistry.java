package net.hexagonelle.applesaplings.blocks;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.items.ItemRegistry;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.function.Supplier;

public class BlockRegistry {

	// CREATE BLOCK REGISTRY //

	// Creates the register that holds the blocks
	public static final DeferredRegister.Blocks BLOCKS =
		DeferredRegister.createBlocks(AppleSaplings.MODID);

	// A method that will register the DeferredRegister<Block> to the mod event bus
	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}

	// Creates a hashmap so that we can refer to a RegistryObject by a string
	public static final HashMap<String, DeferredBlock<Block>> BLOCK_MAP = new HashMap<>();

	// This method will register all the blocks listed in ModBlocks
	static {
		ModBlocks.init();
	}

	// METHODS TO REGISTER BLOCKS //

	// registers a block and places the corresponding DeferredBlock in the HashMap by blockId
	public static void registerBlock(
		String blockId,
		Supplier<Block> blockSupplier
	) {
		DeferredBlock<Block> deferredBlock = BLOCKS.register(blockId, blockSupplier);
		BLOCK_MAP.put(blockId, deferredBlock);
	}

	public static void registerBlockWithItem(
		String blockId,
		Supplier<Block> blockSupplier
	) {
		registerBlock(blockId, blockSupplier);
		ItemRegistry.registerBlockItem(blockId);
	}

	public static void registerBlockWithItem(
		String blockId,
		Supplier<Block> blockSupplier,
		BlockStateMappings.blockStateMethods method
	) {
		registerBlock(blockId, blockSupplier);
		ItemRegistry.registerBlockItem(blockId);
		BlockStateMappings.assignBlockstate(blockId, method);
	}
}
