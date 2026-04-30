package net.hexagonelle.applesaplings.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.hexagonelle.applesaplings.blocks.BlockRegistry.BLOCK_MAP;

public class ModBlockTagsProvider extends BlockTagsProvider {
	public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, modId, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.@NotNull Provider provider) {
		tag(BlockTags.FENCES).add(BLOCK_MAP.get("applewood_fence").get());
		tag(BlockTags.FENCE_GATES).add(BLOCK_MAP.get("applewood_fence_gate").get());
	}
}
