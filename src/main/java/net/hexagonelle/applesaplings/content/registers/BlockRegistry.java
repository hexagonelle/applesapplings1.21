package net.hexagonelle.applesaplings.content.registers;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.content.ModBlocks;
import net.hexagonelle.applesaplings.datagen.blockmodels.BlockStateMethodArgPair;
import net.hexagonelle.applesaplings.datagen.itemmodels.ItemModelMethodArgPair;
import net.hexagonelle.applesaplings.content.suppliers.BlockSuppliers;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import static net.hexagonelle.applesaplings.content.suppliers.BlockSuppliers.*;
import static net.hexagonelle.applesaplings.content.registers.CreativeTabRegistry.*;
import static net.hexagonelle.applesaplings.datagen.lang.ModLanguageProvider.assignBlockNames;
import static net.hexagonelle.applesaplings.datagen.blockmodels.ModBlockStateProvider.*;
import static net.hexagonelle.applesaplings.datagen.itemmodels.ModItemModelProvider.assignItemModel;
import static net.hexagonelle.applesaplings.content.registers.ItemRegistry.*;

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

	// Create a list of blocks that are valid as a mod sign block entity:
	public static final List<String> MOD_SIGN_BLOCKS = new ArrayList<>();

	// Create a list of blocks that are valid as a mod hanging sign block entity:
	public static final List<String> MOD_HANGING_SIGN_BLOCKS = new ArrayList<>();

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

	public static void registerBlock(
		String blockId,
		Supplier<Block> blockSupplier,
		BlockStateMethodArgPair methodArgPair
	) {
		assignBlockstate(blockId, methodArgPair);
		registerBlock(blockId, blockSupplier);
	}

	public static void registerBlock(
		String blockId,
		String name,
		Supplier<Block> blockSupplier
	) {
		assignBlockNames(blockId,name);
		registerBlock(blockId, blockSupplier);
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

	public static void registerBlockWithItem(
		String blockId,
		String name,
		Supplier<Block> blockSupplier,
		BlockStateMethodArgPair blockStateMethodArgPair,
		ItemModelMethodArgPair itemModelMethodArgPair,
		String creativeTabKey
	) {
		registerBlock(blockId, blockSupplier);
		registerBlockItem(blockId);
		assignBlockNames(blockId,name);
		assignBlockstate(blockId, blockStateMethodArgPair);
		assignItemModel(blockId, itemModelMethodArgPair);
		ITEM_MODTAB_MAP.put(ITEM_MAP.get(blockId),creativeTabKey);
	}

	// SPECIFIC BLOCK METHODS

	public static void registerStrippedLogBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		registerBlockWithItem(
			"stripped_" + woodTypeId + "_log",
			"Stripped " + woodTypeName + " Log",
			BlockSuppliers::createStrippedLogBlock,
			BlockStateMethodArgPair.storeStrippedLogBlockArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerStrippedWoodBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		registerBlockWithItem(
			"stripped_" + woodTypeId + "_wood",
			"Stripped " + woodTypeName + " Wood",
			BlockSuppliers::createStrippedWoodBlock,
			BlockStateMethodArgPair.storeStrippedWoodBlockArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerLogBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		registerBlockWithItem(
			woodTypeId + "_log",
			woodTypeName + " Log",
			() -> BlockSuppliers.createLogBlock("stripped_" + woodTypeId + "_log"),
			BlockStateMethodArgPair.storeLogBlockArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerWoodBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		registerBlockWithItem(
			woodTypeId + "_wood",
			woodTypeName + " Wood",
			() -> BlockSuppliers.createWoodBlock("stripped_" + woodTypeId + "_wood"),
			BlockStateMethodArgPair.storeWoodBlockArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerPlanksBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		registerBlockWithItem(
			woodTypeId + "_planks",
			woodTypeName + " Planks",
			BlockSuppliers::createPlanks,
			BlockStateMethodArgPair.storePlanksBlockArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerLeavesBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		registerBlockWithItem(
			woodTypeId + "_leaves",
			woodTypeName + " Leaves",
			BlockSuppliers::createLeaves,
			BlockStateMethodArgPair.storeLeavesBlockArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerFloweringLeavesBlock(
		String woodTypeId,
		String woodTypeName,
		Item fruit,
		String creativeTabId
	){

		registerBlockWithItem(
			"flowering_" + woodTypeId + "_leaves",
			"Flowering " + woodTypeName + " Leaves",
			() -> createFloweringLeaves(fruit),
			BlockStateMethodArgPair.storeFloweringLeavesBlockArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerWoodStairsBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		registerBlockWithItem(
			woodTypeId + "_stairs",
			woodTypeName + " Stairs",
			() -> createWoodStairBlock(woodTypeId + "_planks"),
			BlockStateMethodArgPair.storeWoodStairsBlockArgs(woodTypeId),
			ItemModelMethodArgPair.storeWoodStairsItemArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerWoodSlabBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		registerBlockWithItem(
			woodTypeId + "_slab",
			woodTypeName + " Slab",
			BlockSuppliers::createWoodSlabBlock,
			BlockStateMethodArgPair.storeWoodSlabBlockArgs(woodTypeId),
			ItemModelMethodArgPair.storeWoodSlabItemArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerWoodFenceBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		registerBlockWithItem(
			woodTypeId + "_fence",
			woodTypeName + " Fence",
			BlockSuppliers::createWoodFenceBlock,
			BlockStateMethodArgPair.storeWoodFenceBlockArgs(woodTypeId),
			ItemModelMethodArgPair.storeWoodFenceItemArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerWoodFenceGateBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		registerBlockWithItem(
			woodTypeId + "_fence_gate",
			woodTypeName + " Fence Gate",
			BlockSuppliers::createWoodFenceGateBlock,
			BlockStateMethodArgPair.storeWoodFenceGateBlockArgs(woodTypeId),
			ItemModelMethodArgPair.storeWoodFenceGateItemArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerWoodDoorBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		registerBlockWithItem(
			woodTypeId + "_door",
			woodTypeName + " Door",
			BlockSuppliers::createWoodDoorBlock,
			BlockStateMethodArgPair.storeWoodDoorBlockArgs(woodTypeId),
			ItemModelMethodArgPair.storeWoodDoorItemArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerWoodTrapdoorBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		registerBlockWithItem(
			woodTypeId + "_trapdoor",
			woodTypeName + " Trapdoor",
			BlockSuppliers::createWoodTrapDoorBlock,
			BlockStateMethodArgPair.storeWoodTrapdoorBlockArgs(woodTypeId),
			ItemModelMethodArgPair.storeWoodTrapdoorItemArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerWoodButtonBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		registerBlockWithItem(
			woodTypeId + "_button",
			woodTypeName + " Button",
			BlockSuppliers::createWoodButton,
			BlockStateMethodArgPair.storeWoodButtonArgs(woodTypeId),
			ItemModelMethodArgPair.storeWoodButtonArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerWoodPressurePlateBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		registerBlockWithItem(
			woodTypeId + "_pressure_plate",
			woodTypeName + " Pressure Plate",
			BlockSuppliers::createWoodPressurePlate,
			BlockStateMethodArgPair.storeWoodPressurePlateArgs(woodTypeId),
			ItemModelMethodArgPair.storeWoodPressurePlateArgs(woodTypeId),
			creativeTabId
		);
	}

	public static void registerSignBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		String standingSignId = woodTypeId + "_sign";
		String wallSignId = woodTypeId + "_wall_sign";

		registerBlock(
			standingSignId,
			() -> createStandingSign(woodTypeId),
			BlockStateMethodArgPair.storeSignBlockArgs(woodTypeId)
		);

		registerBlock(
			wallSignId,
			woodTypeName + " Sign",
			() -> createWallSign(woodTypeId)
		);

		registerSignItem(woodTypeId,creativeTabId);

		MOD_SIGN_BLOCKS.add(standingSignId);
		MOD_SIGN_BLOCKS.add(wallSignId);
	}

	public static void registerHangingSignBlock(
		String woodTypeId,
		String woodTypeName,
		String creativeTabId
	){

		String ceilingHangingSignId = woodTypeId + "_hanging_sign";
		String wallHangingSignId = woodTypeId + "_wall_hanging_sign";

		registerBlock(
			ceilingHangingSignId,
			()->createCeilingHangingSign(woodTypeId),
			BlockStateMethodArgPair.storeHangingSignBlockArgs(woodTypeId)
		);

		registerBlock(
			wallHangingSignId,
			woodTypeName + " Hanging Sign",
			()->createWallHangingSign(woodTypeId)
		);

		registerHangingSignItem(woodTypeId,creativeTabId);

		MOD_HANGING_SIGN_BLOCKS.add(ceilingHangingSignId);
		MOD_HANGING_SIGN_BLOCKS.add(wallHangingSignId);
	}

	// BLOCK SET METHODS
	public static void registerWoodSet(
		String woodTypeId,
		String woodTypeName,
		Item fruit,
		String creativeTabId
	){

		registerStrippedLogBlock(woodTypeId,woodTypeName,creativeTabId);
		registerStrippedWoodBlock(woodTypeId,woodTypeName,creativeTabId);
		registerLogBlock(woodTypeId,woodTypeName,creativeTabId);
		registerWoodBlock(woodTypeId,woodTypeName,creativeTabId);
		registerPlanksBlock(woodTypeId,woodTypeName,creativeTabId);
		registerLeavesBlock(woodTypeId,woodTypeName,creativeTabId);
		registerFloweringLeavesBlock(woodTypeId,woodTypeName,fruit,creativeTabId);
		registerWoodStairsBlock(woodTypeId,woodTypeName,creativeTabId);
		registerWoodSlabBlock(woodTypeId,woodTypeName,creativeTabId);
		registerWoodFenceBlock(woodTypeId,woodTypeName,creativeTabId);
		registerWoodFenceGateBlock(woodTypeId,woodTypeName,creativeTabId);
		registerWoodDoorBlock(woodTypeId,woodTypeName,creativeTabId);
		registerWoodTrapdoorBlock(woodTypeId,woodTypeName,creativeTabId);
		registerWoodButtonBlock(woodTypeId,woodTypeName,creativeTabId);
		registerWoodPressurePlateBlock(woodTypeId,woodTypeName,creativeTabId);
		registerSignBlock(woodTypeId,woodTypeName,creativeTabId);
		registerHangingSignBlock(woodTypeId,woodTypeName,creativeTabId);
	}
}
