package net.hexagonelle.applesaplings.datagen.providers;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.HashMap;
import java.util.Map;

import static net.hexagonelle.applesaplings.datagen.providers.ModBlockStateProvider.blockFromId;
import static net.hexagonelle.applesaplings.datagen.providers.ModItemModelProvider.itemFromId;

public class ModLanguageProvider extends LanguageProvider {
	public ModLanguageProvider(PackOutput output, String locale) {
		super(output, AppleSaplings.MODID, locale);
	}

	public static final Map<String, String> blockLangMap = new HashMap<>();
	public static final Map<String, String> itemLangMap = new HashMap<>();
	public static final Map<String, String> creativeTabLangMap = new HashMap<>();

	public static void assignBlockNames(
		String blockId,
		String name
	) {
		blockLangMap.put(blockId, name);
	}

	public static void assignItemNames(
		String itemId,
		String name
	) {
		itemLangMap.put(itemId, name);
	}


	public static void assignCreativeTabNames(
		String creativeTabId,
		String name
	) {
		creativeTabLangMap.put(creativeTabId, name);
	}

	public void blockName(String blockId, String name){
		Block block = blockFromId(blockId);
		addBlock(()->block,name);
	}

	public void itemName(String itemId, String name){
		Item item = itemFromId(itemId);
		addItem(()->item,name);
	}

	public void tabName(String tabId, String name){
		String fullTabId = "creativetab." + AppleSaplings.MODID + "." + tabId;
		add(fullTabId, name);
	}

	@Override
	protected void addTranslations() {
		for (Map.Entry<String, String> set :
			blockLangMap.entrySet()){
			String blockId = set.getKey();
			String name = set.getValue();
			blockName(blockId, name);
		}

		for (Map.Entry<String, String> set :
			itemLangMap.entrySet()){
			String itemId = set.getKey();
			String name = set.getValue();
			itemName(itemId, name);
		}

		for (Map.Entry<String, String> set :
			creativeTabLangMap.entrySet()){
			String tabId = set.getKey();
			String name = set.getValue();
			tabName(tabId, name);
		}

	}
}
