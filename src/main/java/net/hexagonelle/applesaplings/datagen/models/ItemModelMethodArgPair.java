package net.hexagonelle.applesaplings.datagen.models;

import java.util.List;

public class ItemModelMethodArgPair {

	private final ItemModelMethodArgPair.itemModelMethods method;
	private final List<String> arguments;

	private ItemModelMethodArgPair(ItemModelMethodArgPair.itemModelMethods method, List<String> arguments) {
		this.method = method;
		this.arguments = arguments;
	}

	public ItemModelMethodArgPair.itemModelMethods getMethod() {
		return method;
	}

	public List<String> getArguments() {
		return arguments;
	}

	public enum itemModelMethods{
		SIMPLE_ITEM,
		SAPLING_ITEM,
		WOOD_STAIRS,
		WOOD_SLAB,
		WOOD_FENCE,
		WOOD_FENCE_GATE,
		WOOD_DOOR,
		WOOD_TRAPDOOR,
		WOOD_BUTTON,
		WOOD_PRESSURE_PLATE,
		SIGN_ITEM,
		HANGING_SIGN_ITEM,
		BOAT_ITEM,
		CHEST_BOAT_ITEM
	}

	public static ItemModelMethodArgPair storeSaplingItemArgs(String saplingPrefixId){
		return new ItemModelMethodArgPair(itemModelMethods.SAPLING_ITEM,List.of(saplingPrefixId));
	}
	public static ItemModelMethodArgPair storeWoodStairsItemArgs(String woodType){
		return new ItemModelMethodArgPair(itemModelMethods.WOOD_STAIRS,List.of(woodType));
	}
	public static ItemModelMethodArgPair storeWoodSlabItemArgs(String woodType){
		return new ItemModelMethodArgPair(itemModelMethods.WOOD_SLAB,List.of(woodType));
	}
	public static ItemModelMethodArgPair storeWoodFenceItemArgs(String woodType){
		return new ItemModelMethodArgPair(itemModelMethods.WOOD_FENCE,List.of(woodType));
	}
	public static ItemModelMethodArgPair storeWoodFenceGateItemArgs(String woodType){
		return new ItemModelMethodArgPair(itemModelMethods.WOOD_FENCE_GATE,List.of(woodType));
	}
	public static ItemModelMethodArgPair storeWoodDoorItemArgs(String woodType){
		return new ItemModelMethodArgPair(itemModelMethods.WOOD_DOOR,List.of(woodType));
	}
	public static ItemModelMethodArgPair storeWoodTrapdoorItemArgs(String woodType){
		return new ItemModelMethodArgPair(itemModelMethods.WOOD_TRAPDOOR,List.of(woodType));
	}
	public static ItemModelMethodArgPair storeWoodPressurePlateArgs(String woodType){
		return new ItemModelMethodArgPair(itemModelMethods.WOOD_PRESSURE_PLATE,List.of(woodType));
	}
	public static ItemModelMethodArgPair storeWoodButtonArgs(String woodType){
		return new ItemModelMethodArgPair(itemModelMethods.WOOD_BUTTON,List.of(woodType));
	}
	public static ItemModelMethodArgPair storeSignItemArgs(String woodType){
		return new ItemModelMethodArgPair(itemModelMethods.SIGN_ITEM,List.of(woodType));
	}
	public static ItemModelMethodArgPair storeHangingItemArgs(String woodType){
		return new ItemModelMethodArgPair(itemModelMethods.HANGING_SIGN_ITEM,List.of(woodType));
	}
	public static ItemModelMethodArgPair storeBoatItemArgs(String woodType){
		return new ItemModelMethodArgPair(itemModelMethods.BOAT_ITEM,List.of(woodType));
	}
	public static ItemModelMethodArgPair storeChestBoatItemArgs(String woodType){
		return new ItemModelMethodArgPair(itemModelMethods.CHEST_BOAT_ITEM,List.of(woodType));
	}
}
