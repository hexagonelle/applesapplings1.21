package net.hexagonelle.applesaplings.content;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;

public class ModTreeGrower{
	public static final TreeGrower APPLE;

	// Creates a hashmap so that we can refer to a TreeGrower by a string
	public static final HashMap<String, TreeGrower> TREE_GROWER_MAP = new HashMap<>();

	// adds a TreeGrower and placesit in the HashMap by name
//	public static void registerTreeGrower(
//		String woodTypeId,
//		Supplier<Block> blockSupplier
//	) {
//		TreeGrower treeGrower = new TreeGrower(
//			woodTypeId,
//			0.1F,
//			Optional.empty(),
//			Optional.empty(),
//			Optional.of(),
//			Optional.empty(),
//			Optional.empty(),
//			Optional.empty()
//		);
//	}

	static{
		APPLE = new TreeGrower(
			"apple",
			0.1F,
			Optional.empty(),
			Optional.empty(),
			Optional.of(ModTreeFeatures.APPLE),
			Optional.empty(),
			Optional.empty(),
			Optional.empty()
		);
	}
}
