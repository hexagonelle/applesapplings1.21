package net.hexagonelle.applesaplings.datagen.providers;

import net.hexagonelle.applesaplings.modclasses.data.StrippableData;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.BLOCK_MAP;
import static net.hexagonelle.applesaplings.content.registers.WoodTypeRegistry.WOODTYPE_MAP;
import static net.hexagonelle.applesaplings.modclasses.data.StrippableData.STRIPPABLE_DATA;

public class ModDataMapProvider extends DataMapProvider {
	public ModDataMapProvider(
		PackOutput packOutput,
		CompletableFuture<HolderLookup.Provider> lookupProvider
	){
		super(packOutput, lookupProvider);
	}

	public static DeferredBlock<Block> getModBlock(String blockId){
		return BLOCK_MAP.get(blockId);
	}

	protected void addStrippable(String woodTypeId){
		builder(STRIPPABLE_DATA)
			.add(
				getModBlock(woodTypeId + "_wood"),
				new StrippableData("stripped_" + woodTypeId + "_wood"),false);
	}

	@Override
	protected void gather(HolderLookup.@NotNull Provider lookupProvider){
		for (Map.Entry<String, WoodType> set : WOODTYPE_MAP.entrySet()){
			String woodTypeId = set.getKey();
			addStrippable(woodTypeId);

		}
	}

}
