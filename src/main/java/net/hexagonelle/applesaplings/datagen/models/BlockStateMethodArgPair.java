package net.hexagonelle.applesaplings.datagen.models;

import java.util.List;

public class BlockStateMethodArgPair {

	private final blockStateMethods method;
	private final List<String> arguments;

	private BlockStateMethodArgPair(blockStateMethods method, List<String> arguments) {
		this.method = method;
		this.arguments = arguments;
	}

	public blockStateMethods getMethod() {
		return method;
	}

	public List<String> getArguments() {
		return arguments;
	}

	public enum blockStateMethods{
		BLOCK_ITEM,
		CUBE_BLOCK_ITEM,
		BLOCK_WITH_ITEM,
		SAPLING_BLOCK,
		LEAVES,
		STRIPPED_LOG,
		STRIPPED_WOOD,
		LOG,
		WOOD,
		PLANKS,
		WOOD_STAIRS,
		WOOD_SLAB,
		WOOD_FENCE,
		WOOD_FENCE_GATE,
		WOOD_DOOR,
		WOOD_TRAPDOOR,
		WOOD_PRESSURE_PLATE,
		WOOD_BUTTON,
		SIGN,
		HANGING_SIGN
	}

	public static BlockStateMethodArgPair storeBlockItemArgs(String blockId){
		return new BlockStateMethodArgPair(blockStateMethods.BLOCK_ITEM,List.of(blockId));
	}
	public static BlockStateMethodArgPair storeCubeBlockItemArgs(String blockId){
		return new BlockStateMethodArgPair(blockStateMethods.CUBE_BLOCK_ITEM,List.of(blockId));
	}
	public static BlockStateMethodArgPair storeBlockWithItemArgs(String blockId){
		return new BlockStateMethodArgPair(blockStateMethods.BLOCK_WITH_ITEM,List.of(blockId));
	}
	public static BlockStateMethodArgPair storeSaplingBlockArgs(String blockId){
		return new BlockStateMethodArgPair(blockStateMethods.SAPLING_BLOCK,List.of(blockId));
	}
	public static BlockStateMethodArgPair storeLeavesBlockArgs(String leavesIdPrefix){
		return new BlockStateMethodArgPair(blockStateMethods.LEAVES,List.of(leavesIdPrefix));
	}
	public static BlockStateMethodArgPair storeStrippedLogBlockArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.STRIPPED_LOG,List.of(woodType));
	}
	public static BlockStateMethodArgPair storeStrippedWoodBlockArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.STRIPPED_WOOD,List.of(woodType));
	}
	public static BlockStateMethodArgPair storeLogBlockArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.LOG,List.of(woodType));
	}
	public static BlockStateMethodArgPair storeWoodBlockArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.WOOD,List.of(woodType));
	}
	public static BlockStateMethodArgPair storePlanksBlockArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.PLANKS,List.of(woodType));
	}
	public static BlockStateMethodArgPair storeWoodStairsBlockArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.WOOD_STAIRS,List.of(woodType));
	}
	public static BlockStateMethodArgPair storeWoodSlabBlockArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.WOOD_SLAB,List.of(woodType));
	}
	public static BlockStateMethodArgPair storeWoodFenceBlockArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.WOOD_FENCE,List.of(woodType));
	}
	public static BlockStateMethodArgPair storeWoodFenceGateBlockArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.WOOD_FENCE_GATE,List.of(woodType));
	}
	public static BlockStateMethodArgPair storeWoodDoorBlockArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.WOOD_DOOR,List.of(woodType));
	}
	public static BlockStateMethodArgPair storeWoodTrapdoorBlockArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.WOOD_TRAPDOOR,List.of(woodType));
	}
	public static BlockStateMethodArgPair storeWoodPressurePlateArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.WOOD_PRESSURE_PLATE,List.of(woodType));
	}
	public static BlockStateMethodArgPair storeWoodButtonArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.WOOD_BUTTON,List.of(woodType));
	}
	public static BlockStateMethodArgPair storeSignBlockArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.SIGN,List.of(woodType));
	}
	public static BlockStateMethodArgPair storeHangingBlockArgs(String woodType){
		return new BlockStateMethodArgPair(blockStateMethods.HANGING_SIGN,List.of(woodType));
	}
}
