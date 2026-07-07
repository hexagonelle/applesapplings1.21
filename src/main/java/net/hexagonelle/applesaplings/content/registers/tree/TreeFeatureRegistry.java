package net.hexagonelle.applesaplings.content.registers.tree;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.modclasses.worldgen.FloweringLeavesDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;

import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.BLOCK_MAP;

public class TreeFeatureRegistry {

	public static ResourceKey<ConfiguredFeature<?, ?>> createModKey(String name) {
		return ResourceKey.create(
			Registries.CONFIGURED_FEATURE,
			ResourceLocation.fromNamespaceAndPath(AppleSaplings.MODID, name)
		);
	}

	public static final HashMap<String, ResourceKey<ConfiguredFeature<?, ?>>> TREE_FEATURES_MAP =
		new LinkedHashMap<>();

	public static TreeConfiguration.TreeConfigurationBuilder createFloweringBlobTree(
		String woodTypeId,
		Supplier<TrunkPlacer> trunkFunction,
		Supplier<FoliagePlacer> foliageFunction
		){

		Block logBlock = BLOCK_MAP.get(woodTypeId + "_log").get();
		Block leavesBlock = BLOCK_MAP.get(woodTypeId + "_leaves").get();
//		IntProvider foliageRadius = ConstantInt.of(3);
//		IntProvider foliageOffset = ConstantInt.of(0);

		return new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(logBlock),
//			new StraightTrunkPlacer(5,2,1),
			trunkFunction.get(),
			BlockStateProvider.simple(leavesBlock),
//			new BlobFoliagePlacer(foliageRadius,foliageOffset,3),
			foliageFunction.get(),
			new TwoLayersFeatureSize(1,0,2)
		);

	}

	// creates a flowering leaves decorator
	public static List<TreeDecorator> createFloweringDecorator(
		Float spawnChance,
		int spawnAttempts,
		String woodTypeId
	){
		Block floweringBlock = BLOCK_MAP.get("flowering_" + woodTypeId + "_leaves").get();
		return List.of(
			new FloweringLeavesDecorator(
				spawnChance,
				spawnAttempts,
				BlockStateProvider.simple(floweringBlock))
		);
	}

	public static void createFloweringTree(
		BootstrapContext<ConfiguredFeature<?,?>> context,
		String woodTypeId,
		Supplier<TrunkPlacer> trunkFunction,
		Supplier<FoliagePlacer> foliageFunction,
		ResourceKey<ConfiguredFeature<?, ?>> treeFeature
	){

		List<TreeDecorator> floweringDecoratorList =
			createFloweringDecorator(0.1F, 3,woodTypeId);

		FeatureUtils.register(
			context, treeFeature, Feature.TREE,
			createFloweringBlobTree(
				woodTypeId,
				trunkFunction,
				foliageFunction
			)
				.decorators(floweringDecoratorList)
				.build()
		);

		TREE_FEATURES_MAP.put(woodTypeId, treeFeature);
	}

}
