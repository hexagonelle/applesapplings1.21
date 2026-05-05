package net.hexagonelle.applesaplings.datagen.tags;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.hexagonelle.applesaplings.content.registers.ItemRegistry.ITEM_MAP;

public class ModItemTagsProvider extends ItemTagsProvider {
	public ModItemTagsProvider(
		PackOutput packOutput,
		CompletableFuture<HolderLookup.Provider> lookupProvider,
		CompletableFuture<TagsProvider.TagLookup<Block>> blockTags,
		@Nullable ExistingFileHelper existingFileHelper
	){
		super(packOutput, lookupProvider, blockTags, AppleSaplings.MODID, existingFileHelper);
	}

@Override
	protected void addTags(HolderLookup.@NotNull Provider provider){
		this.copy(BlockTags.SAPLINGS,ItemTags.SAPLINGS);
		this.copy(BlockTags.LEAVES,ItemTags.LEAVES);
		this.copy(BlockTags.LOGS,ItemTags.LOGS);
		this.copy(BlockTags.LOGS_THAT_BURN,ItemTags.LOGS_THAT_BURN);
		this.copy(ModBlockTags.STRIPPED_LOGS,ModItemTags.STRIPPED_LOGS);
		this.copy(ModBlockTags.APPLE_LOGS,ModItemTags.APPLE_LOGS);
		this.copy(BlockTags.PLANKS,ItemTags.PLANKS);
		this.tag(ItemTags.SIGNS).add(ITEM_MAP.get("apple_sign").get());
		this.tag(ItemTags.HANGING_SIGNS).add(ITEM_MAP.get("apple_hanging_sign").get());
		this.copy(BlockTags.WOODEN_STAIRS,ItemTags.WOODEN_STAIRS);
		this.copy(BlockTags.WOODEN_FENCES,ItemTags.WOODEN_FENCES);
		this.copy(BlockTags.FENCE_GATES,ItemTags.FENCE_GATES);
	}

}
