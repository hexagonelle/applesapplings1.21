package net.hexagonelle.applesaplings.blocks.blockentities.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static net.hexagonelle.applesaplings.blocks.blockentities.BlockEntityTypeRegistry.MOD_SIGN;

public class ModSignBlockEntity extends SignBlockEntity {

	public ModSignBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(MOD_SIGN.get(), blockPos, blockState);
	}
}
