package net.hexagonelle.applesaplings.content.registers;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TreeDecoratorTypeRegistry {

	// Create a Deferred Register to hold TreeDecoratorType under the "applesaplings" namespace
	public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES =
		DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, AppleSaplings.MODID);

	// A method that will register the DeferredRegister<TreeDecoratorType<?>> to the mod event bus
	public static void register(IEventBus eventBus){
		TREE_DECORATOR_TYPES.register(eventBus);
	}

}
