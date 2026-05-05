package net.hexagonelle.applesaplings.modclasses.worldgen;

import com.mojang.serialization.MapCodec;
import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.content.worldgen.tree.FloweringLeavesDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class ModTreeDecoratorType<P extends TreeDecorator> extends TreeDecoratorType<P> {
	public static final DeferredHolder<TreeDecoratorType<?>,TreeDecoratorType<FloweringLeavesDecorator>> FLOWERING_LEAVES;
	private final MapCodec<P> codec;

	// Creates the register that holds the blocks
	public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES =
		DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, AppleSaplings.MODID);

	// A method that will register the DeferredRegister<Block> to the mod event bus
	public static void register(IEventBus eventBus) {
		TREE_DECORATOR_TYPES.register(eventBus);
	}

	public ModTreeDecoratorType(MapCodec<P> codec) {
		super(codec);
		this.codec = codec;
	}

	public @NotNull MapCodec<P> codec() {
		return this.codec;
	}

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
