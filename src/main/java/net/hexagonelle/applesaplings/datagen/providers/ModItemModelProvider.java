package net.hexagonelle.applesaplings.datagen.providers;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.datagen.util.ItemModelMethodArgPair;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.hexagonelle.applesaplings.datagen.providers.ModBlockStateProvider.blockFromId;
import static net.hexagonelle.applesaplings.content.registers.ItemRegistry.ITEM_MAP;
import static net.minecraft.resources.ResourceLocation.withDefaultNamespace;

public class ModItemModelProvider extends ItemModelProvider {
	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, AppleSaplings.MODID, existingFileHelper);
	}

	public static final Map<String, ItemModelMethodArgPair> itemItemModelMap = new HashMap<>();

	public static Item itemFromId(String blockId){
		return ITEM_MAP.get(blockId).get();
	}

	// registers a block and places the corresponding DeferredBlock in the HashMap by blockId
	public static void assignItemModel(
		String itemId,
		ItemModelMethodArgPair methodArgPair
	) {
		itemItemModelMap.put(itemId, methodArgPair);
	}

	private ResourceLocation blockResource(String blockId){
		return modLoc("block/" + blockId);
	}

	// provides a resource location for an item with namespace equal to the given namespace
	// and location equal to the itemPath of the provided itemRegistryObject
	private ResourceLocation itemResource(String itemId){
		return modLoc("item/" + itemId);
	}
	// provides a resource location for an item with a block parent,
	// with namespace equal to the given namespace,
	// and location equal to the itemPath of the provided blockRegistryObject
	private ResourceLocation blockItemResource(String blockId){
		return modLoc("item/" + blockId);
	}

	public ItemModelBuilder flatItemModel(
		String name,
		ResourceLocation texture
	){
		return singleTexture(name, withDefaultNamespace("item/generated"), "layer0", texture);
	}

	// generates a generic item model with the name of the item,
	// and a texture located at modid/textures/item/"itemName".png
	private ItemModelBuilder simpleItem(String itemId){
		return flatItemModel(itemId, itemResource(itemId));
	}

	// generates a flat item form of a block with the texture taken from the block texture
	public void blockItemWithFlatBlockTexture(String blockId) {
		flatItemModel(blockId, blockResource(blockId));
	}

	private ItemModelBuilder mcItemModel(
		String name,
		String mcModelName,
		ResourceLocation texture
	){
		return singleTexture(name,mcLoc(mcModelName),"texture",texture);
	}
	// generates a generic item model with the name of the corresponding block,
	// and a texture located at modid/textures/item/"itemName".png
	private ItemModelBuilder blockItemWithItemTexture(String blockId){
		return flatItemModel(blockId, blockItemResource(blockId));
	}

	// generates an item form of a standard block with the texture taken from the block texture
	public void blockItemWithBlockTexture(String blockId) {
		simpleBlockItem(blockFromId(blockId));
	}


	// ITEM MODELS FOR SPECIFIC BLOCKS //

	public void saplingItem(String saplingIdPrefix){
		blockItemWithFlatBlockTexture(saplingIdPrefix + "_sapling");
	}

	public void woodStairsItem(String woodType){
		blockItemWithBlockTexture(woodType + "_stairs");
	}

	public void woodSlabItem(String woodType){
		blockItemWithBlockTexture(woodType + "_slab");
	}

	// use minecraft's built-in fence item model, with the texture of its base block
	public void woodFenceItem(String woodType) {
		this.mcItemModel(
			woodType + "_fence",
			"block/fence_inventory",
			blockResource(woodType + "_planks")
		);
	}

	public void woodFenceGateItem(String woodType){
		blockItemWithBlockTexture(woodType + "_fence_gate");
	}

	public void woodDoorItem(String woodType){
		blockItemWithItemTexture(woodType + "_door");
	}

	// The method to generate a trapdoor's block model creates a file
	// modid/models/block/"trapdoorName"_bottom.json
	// which also functions as the trapdoor's item texture.
	public void woodTrapdoorItem(String woodType) {
		String blockId = woodType + "_trapdoor";
		this.withExistingParent(
			blockId,
			modLoc("block/" + blockId + "_bottom")
		);
	}

	// use minecraft's built-in button item model, with the texture of its base block
	public void woodButtonItem(String woodType) {
		this.mcItemModel(
			woodType + "_button",
			"block/button_inventory",
			blockResource(woodType + "_planks")
		);
	}

	public void woodPressurePlateItem(String woodType){
		blockItemWithBlockTexture(woodType + "_pressure_plate");
	}

	public void signItem(String woodType){
		simpleItem(woodType + "_sign");
	}

	public void hangingSignItem(String woodType){
		simpleItem(woodType + "_hanging_sign");
	}

	public void boatItem(String woodType){
		simpleItem(woodType + "_boat");
	}

	public void chestBoatItem(String woodType){
		simpleItem(woodType + "_chest_boat");
	}

	@Override
	protected void registerModels() {
		for (Map.Entry<String, ItemModelMethodArgPair> set :
			itemItemModelMap.entrySet()){
			ItemModelMethodArgPair methodArgPair = set.getValue();
			ItemModelMethodArgPair.itemModelMethods method = methodArgPair.getMethod();
			List<String> argsList = methodArgPair.getArguments();

			switch (method) {
				case SAPLING_ITEM:
					saplingItem(argsList.getFirst());
					break;
				case WOOD_STAIRS:
					woodStairsItem(argsList.getFirst());
					break;
				case WOOD_SLAB:
					woodSlabItem(argsList.getFirst());
					break;
				case WOOD_FENCE:
					woodFenceItem(argsList.getFirst());
					break;
				case WOOD_FENCE_GATE:
					woodFenceGateItem(argsList.getFirst());
					break;
				case WOOD_DOOR:
					woodDoorItem(argsList.getFirst());
					break;
				case WOOD_TRAPDOOR:
					woodTrapdoorItem(argsList.getFirst());
					break;
				case WOOD_BUTTON:
					woodButtonItem(argsList.getFirst());
					break;
				case WOOD_PRESSURE_PLATE:
					woodPressurePlateItem(argsList.getFirst());
					break;
				case SIGN_ITEM:
					signItem(argsList.getFirst());
					break;
				case HANGING_SIGN_ITEM:
					hangingSignItem(argsList.getFirst());
					break;
				case BOAT_ITEM:
					boatItem(argsList.getFirst());
					break;
				case CHEST_BOAT_ITEM:
					chestBoatItem(argsList.getFirst());
					break;
				default:
					simpleItem(argsList.getFirst());
			}
		}
	}
}
