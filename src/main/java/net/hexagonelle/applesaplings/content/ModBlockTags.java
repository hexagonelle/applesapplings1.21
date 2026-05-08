package net.hexagonelle.applesaplings.content;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {

	public static final TagKey<Block> APPLE_LOGS = createCommonBlockTag("apple_logs");
	public static final TagKey<Block> ORANGE_LOGS = createCommonBlockTag("orange_logs");


	private static TagKey<Block> createCommonBlockTag(String name) {
		return BlockTags.create(ResourceLocation.fromNamespaceAndPath("common", name));
	}
	public static final TagKey<Block> STRIPPED_LOGS = createCommonBlockTag("stripped_logs");

}
