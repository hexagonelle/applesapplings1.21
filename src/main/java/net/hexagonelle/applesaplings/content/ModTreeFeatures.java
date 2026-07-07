package net.hexagonelle.applesaplings.content;

import com.mojang.datafixers.kinds.Const;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import static net.hexagonelle.applesaplings.content.registers.tree.TreeFeatureRegistry.*;

public class ModTreeFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE = createModKey("apple");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE = createModKey("orange");


	public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context) {
		createFloweringTree(
			context,"apple",
			() -> new StraightTrunkPlacer(3,2,1),
			() -> new BlobFoliagePlacer(ConstantInt.of(3),ConstantInt.of(0),3),
			APPLE
		);
		createFloweringTree(context,"orange",
			() -> new StraightTrunkPlacer(3,0,0),
			() -> new BushFoliagePlacer(
				ConstantInt.of(1), // radius at the max trunk height
				ConstantInt.of(1), // height above max trunk for top of leaves
				3                       // number of vertical layers of leaves
			),
			ORANGE
		);
	}

}