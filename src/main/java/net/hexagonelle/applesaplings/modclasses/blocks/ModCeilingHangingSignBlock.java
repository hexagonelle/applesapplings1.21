package net.hexagonelle.applesaplings.modclasses.blocks;

//import net.hexagonelle.applesaplings.blocks.blockentities.custom.ModHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;

public class ModCeilingHangingSignBlock extends CeilingHangingSignBlock {
	public ModCeilingHangingSignBlock(WoodType woodType, Properties properties) { super(woodType, properties); }

	@Override
	public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
		return new HangingSignBlockEntity(blockPos, blockState);
	}
}
