package net.hexagonelle.applesaplings.content.registers;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.content.ModCreativeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.hexagonelle.applesaplings.datagen.lang.ModLanguageProvider.assignCreativeTabNames;

public class CreativeTabRegistry {

	// Creates the register that holds the creative tabs
	public static final DeferredRegister<CreativeModeTab> CREATIVETABS =
		DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AppleSaplings.MODID);

	// A method that will register the DeferredRegister<CreativeModeTab> to the mod event bus
	public static void register(IEventBus eventBus){CREATIVETABS.register(eventBus);}

	// Creates a hashmap so that we can refer to a RegistryObject by a string
	public static final HashMap<String, Supplier<CreativeModeTab>> CREATIVETAB_MAP = new HashMap<>();

	// This method will register all the blocks listed in ModBlocks
	static {
		ModCreativeTabs.init();
	}

	// registers a creative tab and places the corresponding RegistryObject in the HashMap by itemId
	public static void registerCreativeTab(
		String creativeTabId,
		Supplier<CreativeModeTab> creativeTabSupplier
	){
		CREATIVETAB_MAP.put(creativeTabId, CREATIVETABS.register(creativeTabId,creativeTabSupplier));
	}

	// registers a creative tab and places the corresponding RegistryObject in the HashMap by itemId
	public static void registerCreativeTab(
		String creativeTabId,
		String creativeTabName,
		Supplier<CreativeModeTab> creativeTabSupplier
	){
		registerCreativeTab(creativeTabId,creativeTabSupplier);
		assignCreativeTabNames(creativeTabId,creativeTabName);
	}

	// Map for items and their corresponding mod creative tabs
	public static final HashMap<DeferredItem<Item>,String> ITEM_MODTAB_MAP = new HashMap<>();

}
