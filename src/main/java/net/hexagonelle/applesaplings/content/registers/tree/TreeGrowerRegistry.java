package net.hexagonelle.applesaplings.content.registers.tree;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.HashMap;
import java.util.Optional;

public class TreeGrowerRegistry {

	// Creates a hashmap so that we can refer to a TreeGrower by a string
	public static final HashMap<String, TreeGrower> TREE_GROWER_MAP = new HashMap<>();

	//	 adds a TreeGrower and placesit in the HashMap by name
	public static TreeGrower registerTreeGrower(
		String woodTypeId,
		ResourceKey<ConfiguredFeature<?,?>> resourceKey
	) {
		TreeGrower treeGrower = new TreeGrower(
			AppleSaplings.MODID + ":" + woodTypeId,
			0.1F,
			Optional.empty(),
			Optional.empty(),
			Optional.of(resourceKey),
			Optional.empty(),
			Optional.empty(),
			Optional.empty()
		);

		TREE_GROWER_MAP.put(woodTypeId,treeGrower);
		return treeGrower;
	}

}
