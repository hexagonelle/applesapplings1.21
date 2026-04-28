package net.hexagonelle.applesaplings.items;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.function.Supplier;

public class ItemRegistry {

	// CREATE ITEM REGISTRY //

	// Creates the register that holds the items
	public static final DeferredRegister.Items ITEMS =
		DeferredRegister.createItems(AppleSaplings.MODID);

	// A method that will register the DeferredRegister.Item to the mod event bus
	public static void register(IEventBus eventBus){ITEMS.register(eventBus);}

	// Creates a hashmap so that we can refer to a RegistryObject by a string
	public static final HashMap<String, DeferredItem<Item>> ITEM_MAP = new HashMap<>();

	// METHODS TO REGISTER ITEMS //

	// registers an item and places the corresponding DeferredItem in the HashMap by itemId
	public static void registerItem(
		String itemId,
		Supplier<Item> itemSupplier
	){
		DeferredItem<Item> deferredItem = ITEMS.register(itemId,itemSupplier);
		ITEM_MAP.put(itemId, deferredItem);
	}

	public static void registerBlockItem(
		String itemId
	){
		registerItem(itemId,()-> ItemSuppliers.createBlockItem(itemId));
	}

}
