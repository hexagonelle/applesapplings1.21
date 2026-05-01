package net.hexagonelle.applesaplings.worldgen.tree.decorators;

import com.mojang.serialization.MapCodec;
import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

//public class ModTreeDecorators {
//
//	// Create a Deferred Register to hold TreeDecoratorType under the "applesaplings" namespace
//	public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES =
//		DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, AppleSaplings.MODID);
//
//	// A method that will register the DeferredRegister<TreeDecoratorType<?>> to the mod event bus
//	public static void register(IEventBus eventBus){
//		TREE_DECORATOR_TYPES.register(eventBus);
//	}
//
//	// A method that creates a TreeDecoratorType and registers it under TREE_DECORATOR_TYPES with the given ID
//	private static <T extends TreeDecoratorType<?>> DeferredHolder<TreeDecoratorType<?>,T> registerDecoratorType(
//		String key,
//		Supplier<T> treeDecoratorType
//	){
//		return TREE_DECORATOR_TYPES.register(key,treeDecoratorType);
//	}
//
//	private static TreeDecoratorType<FloweringLeavesDecorator> createFloweringLeavesDecorator(
//		MapCodec<FloweringLeavesDecorator> codec
//	){
//		return new TreeDecoratorType<>(codec);
//	}
//
//	public static final DeferredHolder<TreeDecoratorType<?>,TreeDecoratorType<FloweringLeavesDecorator>> FLOWERING_LEAVES
//		= registerDecoratorType(
//			"flowering_leaves",
//			() -> createFloweringLeavesDecorator(FloweringLeavesDecorator.CODEC)
//		);
//
//}
