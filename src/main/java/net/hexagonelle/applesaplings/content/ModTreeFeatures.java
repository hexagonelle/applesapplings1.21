package net.hexagonelle.applesaplings.content;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import static net.hexagonelle.applesaplings.content.registers.tree.TreeFeatureRegistry.*;

public class ModTreeFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE = createModKey("apple");


	public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context) {
		createFloweringTree(context,"apple", APPLE);
	}

}
