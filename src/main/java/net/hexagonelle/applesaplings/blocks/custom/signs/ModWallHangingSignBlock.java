package net.hexagonelle.applesaplings.blocks.custom.signs;

import net.hexagonelle.applesaplings.blocks.blockentities.custom.ModHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;

public class ModWallHangingSignBlock extends WallHangingSignBlock {
	public ModWallHangingSignBlock(WoodType woodType, Properties properties) { super(woodType,properties);	}

	@Override
	public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
		return new ModHangingSignBlockEntity(blockPos, blockState);
	}
}
