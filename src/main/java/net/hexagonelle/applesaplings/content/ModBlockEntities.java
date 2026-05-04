package net.hexagonelle.applesaplings.content;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.BLOCK_MAP;

@EventBusSubscriber
public class ModBlockEntities {

	@SubscribeEvent
	public static void modifyBlockEntities(BlockEntityTypeAddBlocksEvent event) {
		event.modify(
			BlockEntityType.SIGN,
			BLOCK_MAP.get("apple_sign").get(),
			BLOCK_MAP.get("apple_wall_sign").get()
		);

		event.modify(
			BlockEntityType.HANGING_SIGN,
			BLOCK_MAP.get("apple_hanging_sign").get(),
			BLOCK_MAP.get("apple_wall_hanging_sign").get()
		);

	}



}
