package net.hexagonelle.applesaplings.blocks.custom.signs;

import net.hexagonelle.applesaplings.blocks.blockentities.custom.ModSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;

public class ModStandingSignBlock extends StandingSignBlock implements EntityBlock {

	public ModStandingSignBlock(WoodType woodType, BlockBehaviour.Properties properties){
		super(woodType, properties);
	}

	@Override
	public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state){
		return new ModSignBlockEntity(pos,state);
	}


}
