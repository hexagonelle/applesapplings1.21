package net.hexagonelle.applesaplings.datagen.lang;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.HashMap;
import java.util.Map;

import static net.hexagonelle.applesaplings.datagen.models.ModBlockStateProvider.blockFromId;

public class ModLanguageProvider extends LanguageProvider {
	public ModLanguageProvider(PackOutput output, String modid, String locale) {
		super(output, modid, locale);
	}

	public static final Map<String, String> blockLangMap = new HashMap<>();
	public static final Map<String, String> creativeTabLangMap = new HashMap<>();


	public static void assignBlockNames(
		String blockId,
		String name
	) {
		blockLangMap.put(blockId, name);
	}
	// registers a block and places the corresponding DeferredBlock in the HashMap by blockId
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
			creativeTabLangMap.entrySet()){
			String tabId = set.getKey();
			String name = set.getValue();
			tabName(tabId, name);
		}

	}
}
