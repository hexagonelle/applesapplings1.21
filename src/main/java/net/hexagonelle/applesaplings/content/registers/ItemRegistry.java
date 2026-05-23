package net.hexagonelle.applesaplings.content.registers;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.content.ModBoat;
import net.hexagonelle.applesaplings.datagen.util.ItemModelMethodArgPair;
import net.hexagonelle.applesaplings.content.ModItems;
import net.hexagonelle.applesaplings.content.suppliers.ItemSuppliers;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.hexagonelle.applesaplings.content.registers.CreativeTabRegistry.ITEM_MODTAB_MAP;
import static net.hexagonelle.applesaplings.datagen.providers.ModLanguageProvider.assignItemNames;
import static net.hexagonelle.applesaplings.datagen.providers.ModItemModelProvider.assignItemModel;

public class ItemRegistry {

	// CREATE ITEM REGISTRY //

	// Creates the register that holds the items
	public static final DeferredRegister.Items ITEMS =
		DeferredRegister.createItems(AppleSaplings.MODID);

	// A method that will register the DeferredRegister.Item to the mod event bus
	public static void register(IEventBus eventBus){ITEMS.register(eventBus);}


//	public static DeferredItem<Item> ORANGE = ITEMS.register("orange", ItemSuppliers::createFruitItem);

	// Creates a hashmap so that we can refer to a RegistryObject by a string
	public static final HashMap<String, DeferredItem<Item>> ITEM_MAP = new HashMap<>();

	// This method will register all the items listed in ModItems
	static {
		ModItems.init();
	}

	// METHODS TO REGISTER ITEMS //

	// registers an item and places the corresponding DeferredItem in the HashMap by itemId
	public static void registerItem(
		String itemId,
		Supplier<Item> itemSupplier
	){
		DeferredItem<Item> deferredItem = ITEMS.register(itemId,itemSupplier);
		ITEM_MAP.put(itemId, deferredItem);
	}

	public static void registerItem(
		String itemId,
		Supplier<Item> itemSupplier,
		ItemModelMethodArgPair itemModelMethodArgPair,
		String creativeTabId
	){
		DeferredItem<Item> deferredItem = ITEMS.register(itemId,itemSupplier);
		ITEM_MAP.put(itemId, deferredItem);
		assignItemModel(itemId, itemModelMethodArgPair);
		ITEM_MODTAB_MAP.put(ITEM_MAP.get(itemId),creativeTabId);
	}

	public static void registerItem(
		String itemId,
		String name,
		Supplier<Item> itemSupplier,
		ItemModelMethodArgPair itemModelMethodArgPair,
		String creativeTabId
	){
		DeferredItem<Item> deferredItem = ITEMS.register(itemId,itemSupplier);
		ITEM_MAP.put(itemId, deferredItem);
		assignItemModel(itemId, itemModelMethodArgPair);
		assignItemNames(itemId,name);
		ITEM_MODTAB_MAP.put(ITEM_MAP.get(itemId),creativeTabId);
	}

	public static void registerBlockItem(String blockId){
		registerItem(blockId,()-> ItemSuppliers.createBlockItem(blockId));
	}

	public static void registerFruitItem(
		String fruitId,
		String fruitName,
		String creativeTabId
	){
		registerItem(
			fruitId,
			fruitName,
			ItemSuppliers::createFruitItem,
			ItemModelMethodArgPair.storeSimpleItemArgs(fruitId),
			creativeTabId
		);
	}

	public static void registerSignItem(
		String woodType,
		String creativeTabId
	){
		registerItem(
			woodType + "_sign",
			() -> ItemSuppliers.createSignItem(woodType),
			ItemModelMethodArgPair.storeSignItemArgs(woodType),
			creativeTabId
		);
	}

	public static void registerHangingSignItem(
		String woodType,
		String creativeTabId
	){
		registerItem(
			woodType + "_hanging_sign",
			() -> ItemSuppliers.createHangingSignItem(woodType),
			ItemModelMethodArgPair.storeHangingSignItemArgs(woodType),
			creativeTabId
		);
	}

	public static void registerBoatItem(
		String woodType,
		String woodTypeName,
		ModBoat.Type boatType,
		String creativeTabId
	){
		String itemId = woodType + "_boat";
		registerItem(
			itemId,
			() -> ItemSuppliers.createBoatItem(boatType)
		);
		assignItemNames(itemId,woodTypeName + " Boat");
		ITEM_MODTAB_MAP.put(ITEM_MAP.get(itemId),creativeTabId);
		assignItemModel(itemId, ItemModelMethodArgPair.storeBoatItemArgs(woodType));
	}

	public static void registerChestBoatItem(
		String woodType,
		String woodTypeName,
		ModBoat.Type boatType,
		String creativeTabId
	){
		String itemId = woodType + "_chest_boat";
		registerItem(
			itemId,
			() -> ItemSuppliers.createChestBoatItem(boatType)
		);
		assignItemNames(itemId,woodTypeName + " Chest Boat");
		ITEM_MODTAB_MAP.put(ITEM_MAP.get(itemId),creativeTabId);
		assignItemModel(itemId, ItemModelMethodArgPair.storeChestBoatItemArgs(woodType));
	}

}
