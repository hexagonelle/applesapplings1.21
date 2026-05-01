package net.hexagonelle.applesaplings.blocks.blockentities;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.blockentities.custom.ModSignBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.hexagonelle.applesaplings.blocks.BlockRegistry.BLOCK_MAP;
import static net.hexagonelle.applesaplings.blocks.BlockRegistry.MOD_SIGN_BLOCKS;

public class BlockEntityTypeRegistry {

	// Create a Deferred Register to hold BlockEntityType under the "applesaplings" namespace
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
		DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, AppleSaplings.MODID);

	// A method that will register the DeferredRegister<Block> to the mod event bus
	public static void register(IEventBus eventBus) {
		BLOCK_ENTITY_TYPES.register(eventBus);
	}

	// For creating signs:
	public static final Supplier<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
		BLOCK_ENTITY_TYPES.register(
			"mod_sign",
			() -> BlockEntityType.Builder.of(
			ModSignBlockEntity::new,
				MOD_SIGN_BLOCKS.stream().map(
					blockId -> BLOCK_MAP.get(blockId).get()
				).toList().toArray(new Block[0])
		).build(null));
}
