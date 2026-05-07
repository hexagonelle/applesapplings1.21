package net.hexagonelle.applesaplings.modclasses.blocks;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.*;

@EventBusSubscriber
public class ModBlockEntities {

	@SubscribeEvent
	public static void modifyBlockEntities(BlockEntityTypeAddBlocksEvent event) {

		for (String blockId : MOD_SIGN_BLOCKS) {
			event.modify(
				BlockEntityType.SIGN,
				BLOCK_MAP.get(blockId).get()
			);
		}

		for (String blockId : MOD_HANGING_SIGN_BLOCKS) {
			event.modify(
				BlockEntityType.HANGING_SIGN,
				BLOCK_MAP.get(blockId).get()
			);
		}
	}
}
