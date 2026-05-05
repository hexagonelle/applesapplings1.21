package net.hexagonelle.applesaplings.datagen.tags;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.BLOCK_MAP;
import static net.minecraft.tags.BlockTags.*;

public class ModBlockTagsProvider extends BlockTagsProvider {

	public ModBlockTagsProvider(
		PackOutput output,
		CompletableFuture<HolderLookup.Provider> lookupProvider,
		@Nullable ExistingFileHelper existingFileHelper
	) {
		super(output, lookupProvider, AppleSaplings.MODID, existingFileHelper);
	}

	@SafeVarargs
	private void assignTags(TagKey<Block> tagKey, DeferredBlock<Block>... blockRegistryObjects){
		Stream.of(blockRegistryObjects).forEach(block -> this.tag(tagKey).add(block.get()));
	}

	private DeferredBlock<Block> blockFromId(String blockId){
		return BLOCK_MAP.get(blockId);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {

		assignTags(SAPLINGS,
			blockFromId("apple_sapling")
		);
		assignTags(LEAVES,
			blockFromId("flowering_apple_leaves"),
			blockFromId("apple_leaves")
		);
//		assignTags(LOGS,
//			blockFromId("apple_log"),
//			blockFromId("apple_wood"),
//			blockFromId("stripped_apple_log"),
//			blockFromId("stripped_apple_wood")
//		);
		assignTags(ModBlockTags.STRIPPED_LOGS,
			blockFromId("stripped_apple_log"),
			blockFromId("stripped_apple_wood")
		);
		assignTags(LOGS_THAT_BURN,
			blockFromId("apple_log"),
			blockFromId("apple_wood"),
			blockFromId("stripped_apple_log"),
			blockFromId("stripped_apple_wood")
		);
		assignTags(PLANKS,
			blockFromId("apple_planks"));
		assignTags(SIGNS,
			blockFromId("apple_sign"),
			blockFromId("apple_wall_sign")
		);
		assignTags(ALL_HANGING_SIGNS,
			blockFromId("apple_hanging_sign"),
			blockFromId("apple_wall_hanging_sign")
		);
		assignTags(STANDING_SIGNS, blockFromId("apple_sign"));
		assignTags(WALL_SIGNS, blockFromId("apple_wall_sign"));
		assignTags(CEILING_HANGING_SIGNS, blockFromId("apple_hanging_sign"));
		assignTags(WALL_HANGING_SIGNS, blockFromId("apple_wall_hanging_sign"));
		assignTags(WOODEN_STAIRS, blockFromId("apple_stairs"));
		assignTags(WOODEN_SLABS, blockFromId("apple_slab"));
		assignTags(WOODEN_BUTTONS, blockFromId("apple_button"));
		assignTags(WOODEN_PRESSURE_PLATES, blockFromId("apple_pressure_plate"));
		assignTags(WOODEN_FENCES,blockFromId("apple_fence"));
		assignTags(FENCE_GATES, blockFromId("apple_fence_gate"));
		assignTags(WOODEN_DOORS, blockFromId("apple_door"));
		assignTags(WOODEN_TRAPDOORS, blockFromId("apple_trapdoor"));

	}
}
