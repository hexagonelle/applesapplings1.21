package net.hexagonelle.applesaplings.modclasses.worldgen;

import com.mojang.serialization.MapCodec;
import net.hexagonelle.applesaplings.content.worldgen.tree.FloweringLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.neoforge.registries.DeferredHolder;

import static net.hexagonelle.applesaplings.content.registers.TreeDecoratorTypeRegistry.TREE_DECORATOR_TYPES;

public class ModTreeDecoratorType {
	public static final DeferredHolder<TreeDecoratorType<?>,TreeDecoratorType<FloweringLeavesDecorator>> FLOWERING_LEAVES;

	private static <P extends TreeDecorator> DeferredHolder<TreeDecoratorType<?>,TreeDecoratorType<P>> register(
		String name,
		MapCodec<P> codec
	) {
		return TREE_DECORATOR_TYPES.register(name, () -> new TreeDecoratorType<>(codec));
	}

	static {
		FLOWERING_LEAVES = register("flowering_leaves",FloweringLeavesDecorator.CODEC);
	}

}
