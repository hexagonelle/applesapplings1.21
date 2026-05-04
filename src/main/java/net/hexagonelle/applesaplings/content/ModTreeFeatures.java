package net.hexagonelle.applesaplings.content;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.content.worldgen.tree.FloweringLeavesDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.BLOCK_MAP;
import static net.minecraft.data.worldgen.features.FeatureUtils.createKey;

public class ModTreeFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE = createModKey("apple");

	public static ResourceKey<ConfiguredFeature<?, ?>> createModKey(String name) {
		return ResourceKey.create(
			Registries.CONFIGURED_FEATURE,
			ResourceLocation.fromNamespaceAndPath(AppleSaplings.MODID, name)
		);
	}

	public static final HashMap<String, ResourceKey<ConfiguredFeature<?, ?>>> TREE_FEATURES_MAP =
		new LinkedHashMap<>();


	private static TreeConfiguration.TreeConfigurationBuilder createFloweringBlobTree(String woodTypeId){

		Block logBlock = BLOCK_MAP.get(woodTypeId + "_log").get();
		Block leavesBlock = BLOCK_MAP.get(woodTypeId + "_leaves").get();
		IntProvider foliageRadius = ConstantInt.of(3);
		IntProvider foliageOffset = ConstantInt.of(0);

		return new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(logBlock),
			new StraightTrunkPlacer(5,2,1),
			BlockStateProvider.simple(leavesBlock),
			new BlobFoliagePlacer(foliageRadius,foliageOffset,3),
			new TwoLayersFeatureSize(1,0,2)
		);

	}

	// creates a flowering leaves decorator
	private static List<TreeDecorator> createFloweringDecorator(
		Float spawnChance,
		int spawnAttempts,
		String woodTypeId
	){
		Block floweringBlock = BLOCK_MAP.get("flowering_"+woodTypeId + "_leaves").get();
		return List.of(
			new FloweringLeavesDecorator(
				spawnChance,
				spawnAttempts,
				BlockStateProvider.simple(floweringBlock))
		);
	}

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context) {
		List<TreeDecorator> floweringAppleDecoratorList =
			createFloweringDecorator(0.1F, 3,"apple");

		FeatureUtils.register(
			context, APPLE, Feature.TREE,
			createFloweringBlobTree("apple")
				.decorators(floweringAppleDecoratorList)
				.build()
		);

		TREE_FEATURES_MAP.put("apple", APPLE);

	}

}
