package net.hexagonelle.applesaplings.blocks;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.datagen.models.BlockStateMethodArgPair;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.hexagonelle.applesaplings.blocks.BlockSuppliers.*;
import static net.hexagonelle.applesaplings.creativetabs.CreativeTabRegistry.*;
import static net.hexagonelle.applesaplings.datagen.lang.ModLanguageProvider.assignBlockNames;
import static net.hexagonelle.applesaplings.datagen.models.ModBlockStateProvider.*;
import static net.hexagonelle.applesaplings.items.ItemRegistry.*;

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
		registerBlockItem(blockId);
	}

	public static void registerBlockWithItem(
		String blockId,
		Supplier<Block> blockSupplier,
		BlockStateMethodArgPair methodArgPair
	) {
		registerBlock(blockId, blockSupplier);
		registerBlockItem(blockId);
		assignBlockstate(blockId, methodArgPair);
	}

	public static void registerBlockWithItem(
		String blockId,
		String name,
		Supplier<Block> blockSupplier,
		BlockStateMethodArgPair methodArgPair
	) {
		registerBlock(blockId, blockSupplier);
		assignBlockNames(blockId,name);
		registerBlockItem(blockId);
		assignBlockstate(blockId, methodArgPair);
	}

	public static void registerBlockWithItem(
		String blockId,
		String name,
		Supplier<Block> blockSupplier,
		BlockStateMethodArgPair methodArgPair,
		String creativeTabKey
	) {
		registerBlock(blockId, blockSupplier);
		registerBlockItem(blockId);
		assignBlockNames(blockId,name);
		assignBlockstate(blockId, methodArgPair);
		ITEM_MODTAB_MAP.put(ITEM_MAP.get(blockId),creativeTabKey);
	}

	public static void registerWoodSet(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		String strippedLogId = "stripped_" + woodTypeId + "_log";
		String strippedWoodId = "stripped_" + woodTypeId + "_wood";

		registerBlockWithItem(
			strippedLogId,
			"Stripped " + woodTypeName + " Log",
			BlockSuppliers::createStrippedLogBlock,
			BlockStateMethodArgPair.storeStrippedLogBlockArgs(woodTypeId),
			creativeTabId
		);
		registerBlockWithItem(
			strippedWoodId,
			"Stripped " + woodTypeName + " Wood",
			BlockSuppliers::createStrippedWoodBlock,
			BlockStateMethodArgPair.storeStrippedWoodBlockArgs(woodTypeId),
			creativeTabId
		);
		registerBlockWithItem(
			woodTypeId + "_log",
			woodTypeName + " Log",
			() -> BlockSuppliers.createLogBlock(strippedLogId),
			BlockStateMethodArgPair.storeLogBlockArgs(woodTypeId),
			creativeTabId
		);
		registerBlockWithItem(
			woodTypeId + "_wood",
			woodTypeName + " Wood",
			() -> BlockSuppliers.createWoodBlock(strippedWoodId),
			BlockStateMethodArgPair.storeWoodBlockArgs(woodTypeId),
			creativeTabId
		);
		registerBlockWithItem(
			woodTypeId + "_planks",
			woodTypeName + " Planks",
			BlockSuppliers::createPlanks,
			BlockStateMethodArgPair.storePlanksBlockArgs(woodTypeId),
			creativeTabId
		);
		registerBlockWithItem(
			woodTypeId + "_leaves",
			woodTypeName + " Leaves",
			BlockSuppliers::createLeaves,
			BlockStateMethodArgPair.storeLeavesBlockArgs(woodTypeId),
			creativeTabId
		);
//		registerBlockWithItem(
//			woodTypeId + "_stairs",
//			woodTypeName + " Stairs",
//			() -> createWoodStairBlock(woodTypeId),
//			WOOD_STAIRS,
//			creativeTabId
//		);
//		registerBlockWithItem(
//			woodTypeId + "_slab",
//			woodTypeName + " Slab",
//			BlockSuppliers::createWoodSlabBlock,
//			WOOD_SLAB,
//			creativeTabId
//		);
//		registerBlockWithItem(
//			woodTypeId + "_fence",
//			woodTypeName + " Fence",
//			BlockSuppliers::createWoodFenceBlock,
//			FENCE,
//			creativeTabId
//		);
//		registerBlockWithItem(
//			woodTypeId + "_fence_gate",
//			woodTypeName + " Fence Gate",
//			BlockSuppliers::createWoodFenceGateBlock,
//			FENCE_GATE,
//			creativeTabId
//		);
	}
}
