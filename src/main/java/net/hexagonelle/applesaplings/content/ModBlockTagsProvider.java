package net.hexagonelle.applesaplings.content;

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

import static net.hexagonelle.applesaplings.content.ModBlockTags.ORANGE_LOGS;
import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.BLOCK_MAP;
import static net.hexagonelle.applesaplings.content.ModBlockTags.APPLE_LOGS;
import static net.minecraft.tags.BlockTags.*;

public class ModBlockTagsProvider extends BlockTagsProvider {

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {

		assignWoodSetTags("apple",APPLE_LOGS);
		assignWoodSetTags("orange",ORANGE_LOGS);
	}

	public ModBlockTagsProvider(
		PackOutput output,
		CompletableFuture<HolderLookup.Provider> lookupProvider,
		@Nullable ExistingFileHelper existingFileHelper
	){
		super(output, lookupProvider, AppleSaplings.MODID, existingFileHelper);
	}

	@SafeVarargs
	private void assignTags(TagKey<Block> tagKey, DeferredBlock<Block>... blockRegistryObjects){
		Stream.of(blockRegistryObjects).forEach(block -> this.tag(tagKey).add(block.get()));
	}

	private static DeferredBlock<Block> getDeferredBlock(String blockId){
		return BLOCK_MAP.get(blockId);
	}

	public void assignWoodSetTags(
		String woodTypeId,
		TagKey<Block> logBlockTags
	){

		DeferredBlock<Block> saplingBlock = getDeferredBlock(woodTypeId + "_sapling");
		DeferredBlock<Block> leavesBlock = getDeferredBlock(woodTypeId + "_leaves");
		DeferredBlock<Block> floweringLeavesBlock = getDeferredBlock("flowering_" + woodTypeId + "_leaves");
		DeferredBlock<Block> logBlock = getDeferredBlock(woodTypeId + "_log");
		DeferredBlock<Block> woodBlock = getDeferredBlock(woodTypeId + "_wood");
		DeferredBlock<Block> strippedLogBlock = getDeferredBlock("stripped_" + woodTypeId + "_log");
		DeferredBlock<Block> strippedWoodBlock = getDeferredBlock("stripped_" + woodTypeId + "_wood");
		DeferredBlock<Block> planksBlock = getDeferredBlock(woodTypeId + "_planks");
		DeferredBlock<Block> signBlock = getDeferredBlock(woodTypeId + "_sign");
		DeferredBlock<Block> wallSignBlock = getDeferredBlock(woodTypeId + "_wall_sign");
		DeferredBlock<Block> hangingSignBlock = getDeferredBlock(woodTypeId + "_hanging_sign");
		DeferredBlock<Block> wallHangingSignBlock = getDeferredBlock(woodTypeId + "_wall_hanging_sign");
		DeferredBlock<Block> stairsBlock = getDeferredBlock(woodTypeId + "_stairs");
		DeferredBlock<Block> slabBlock = getDeferredBlock(woodTypeId + "_slab");
		DeferredBlock<Block> buttonBlock = getDeferredBlock(woodTypeId + "_button");
		DeferredBlock<Block> pressurePlateBlock = getDeferredBlock(woodTypeId + "_pressure_plate");
		DeferredBlock<Block> fenceBlock = getDeferredBlock(woodTypeId + "_fence");
		DeferredBlock<Block> fenceGateBlock = getDeferredBlock(woodTypeId + "_fence_gate");
		DeferredBlock<Block> doorBlock = getDeferredBlock(woodTypeId + "_door");
		DeferredBlock<Block> trapDoorBlock = getDeferredBlock(woodTypeId + "_trapdoor");

		assignTags(LEAVES,
			floweringLeavesBlock,
			leavesBlock
		);
		assignTags(LOGS,
			logBlock,
			woodBlock,
			strippedLogBlock,
			strippedWoodBlock
		);
		assignTags(ModBlockTags.STRIPPED_LOGS,
			strippedLogBlock,
			strippedWoodBlock
		);
		assignTags(LOGS_THAT_BURN,
			logBlock,
			woodBlock,
			strippedLogBlock,
			strippedWoodBlock
		);
		assignTags(logBlockTags,
			logBlock,
			woodBlock,
			strippedLogBlock,
			strippedWoodBlock
		);
		assignTags(PLANKS, planksBlock);
		assignTags(SIGNS,
			signBlock,
			wallSignBlock
		);
		assignTags(ALL_HANGING_SIGNS,
			hangingSignBlock,
			wallHangingSignBlock
		);

		assignTags(SAPLINGS,saplingBlock);
		assignTags(STANDING_SIGNS, signBlock);
		assignTags(WALL_SIGNS, wallSignBlock);
		assignTags(CEILING_HANGING_SIGNS, hangingSignBlock);
		assignTags(WALL_HANGING_SIGNS, wallHangingSignBlock);
		assignTags(WOODEN_STAIRS, stairsBlock);
		assignTags(WOODEN_SLABS, slabBlock);
		assignTags(WOODEN_BUTTONS, buttonBlock);
		assignTags(WOODEN_PRESSURE_PLATES, pressurePlateBlock);
		assignTags(WOODEN_FENCES, fenceBlock);
		assignTags(FENCE_GATES, fenceGateBlock);
		assignTags(WOODEN_DOORS, doorBlock);
		assignTags(WOODEN_TRAPDOORS, trapDoorBlock);

	}

}
