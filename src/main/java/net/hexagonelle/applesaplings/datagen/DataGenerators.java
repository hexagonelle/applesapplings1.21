package net.hexagonelle.applesaplings.datagen;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.datagen.lang.ModLanguageProvider;
import net.hexagonelle.applesaplings.datagen.blockmodels.ModBlockStateProvider;
import net.hexagonelle.applesaplings.datagen.itemmodels.ModItemModelProvider;
import net.hexagonelle.applesaplings.datagen.tags.ModBlockTagsProvider;
import net.hexagonelle.applesaplings.datagen.tags.ModItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = AppleSaplings.MODID)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event){
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(
			event.includeClient(),
			new ModBlockStateProvider(packOutput, existingFileHelper)
		);
		generator.addProvider(
			event.includeClient(),
			new ModItemModelProvider(packOutput, existingFileHelper)
		);
		generator.addProvider(
			event.includeClient(),
			new ModLanguageProvider(packOutput, "en_us")
		);
		TagsProvider<Block> blockTags = generator.addProvider(
			event.includeClient(),
			new ModBlockTagsProvider(packOutput, lookupProvider, existingFileHelper)
		);
		generator.addProvider(
			event.includeClient(),
			new ModItemTagsProvider(packOutput, lookupProvider, blockTags.contentsGetter(), existingFileHelper)
		);
		generator.addProvider(
			event.includeServer(),
			new ModDatapackProvider(packOutput,lookupProvider)
		);

//		generator.addProvider(
//			event.includeServer(),
//			new LootTableProvider(
//				packOutput,
//				Collections.emptySet(),
//				List.of(
//					new LootTableProvider.SubProviderEntry(
//						ModBlockLootTableProvider::new,
//						LootContextParamSets.BLOCK
//					)
//				),
//				lookupProvider
//			)
//		);

	}

}
