package net.hexagonelle.applesaplings.content;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {

	public static final TagKey<Item> APPLE_LOGS = createCommonItemTag("apple_logs");
	public static final TagKey<Item> ORANGE_LOGS = createCommonItemTag("orange_logs");

	private static TagKey<Item> createCommonItemTag(String name) {
		return ItemTags.create(ResourceLocation.fromNamespaceAndPath("common", name));
	}

	public static final TagKey<Item> STRIPPED_LOGS = createCommonItemTag("stripped_logs");

}
