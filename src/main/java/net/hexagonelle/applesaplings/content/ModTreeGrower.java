package net.hexagonelle.applesaplings.content;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.antlr.v4.runtime.tree.Tree;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;

import static net.hexagonelle.applesaplings.content.ModTreeFeatures.TREE_FEATURES_MAP;

public class ModTreeGrower{
	public static final TreeGrower APPLE;

	// Creates a hashmap so that we can refer to a TreeGrower by a string
	public static final HashMap<String, TreeGrower> TREE_GROWER_MAP = new HashMap<>();

//	 adds a TreeGrower and placesit in the HashMap by name
	public static TreeGrower registerTreeGrower(
		String woodTypeId
	) {
		TreeGrower treeGrower = new TreeGrower(
			AppleSaplings.MODID + ":" + woodTypeId,
			0.1F,
			Optional.empty(),
			Optional.empty(),
			Optional.of(TREE_FEATURES_MAP.get(woodTypeId)),
			Optional.empty(),
			Optional.empty(),
			Optional.empty()
		);

		TREE_GROWER_MAP.put(woodTypeId,treeGrower);
		return treeGrower;
	}

	static{
		APPLE = new TreeGrower(
			AppleSaplings.MODID + ":apple",
			0.1F,
			Optional.empty(),
			Optional.empty(),
			Optional.of(ModTreeFeatures.APPLE),
			Optional.empty(),
			Optional.empty(),
			Optional.empty()
		);

//		APPLE = registerTreeGrower("apple");
	}
}
