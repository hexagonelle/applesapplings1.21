package net.hexagonelle.applesaplings.modclasses;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModWood extends RotatedPillarBlock {

	public ModWood(Properties properties) {
		super(properties);
	}

	private Boolean isStripped = false;
	private Block strippedVersion = null;

	public Block setIsStripped(Boolean bool){
		isStripped = bool;
		return this;
	}
	public Boolean getIsStripped(){
		return isStripped;
	}

	public Block setStrippedVersion(Block strippedBlock){
		if (!isStripped) {strippedVersion = strippedBlock;}
		return this;
	}

	@Override
	public boolean  isFlammable(
		@NotNull BlockState blockstate,
		@NotNull BlockGetter level,
		@NotNull BlockPos blockPos,
		@NotNull Direction direction){
		return true;
	}

	@Override
	public int getFlammability(
		@NotNull BlockState blockstate,
		@NotNull BlockGetter level,
		@NotNull BlockPos blockPos,
		@NotNull Direction direction){
		return 5;
	}

	@Override
	public int getFireSpreadSpeed(
		@NotNull BlockState blockstate,
		@NotNull BlockGetter level,
		@NotNull BlockPos blockPos,
		@NotNull Direction direction){
		return 5;
	}

//	@Override
//	public @Nullable BlockState getToolModifiedState(
//		BlockState state,
//		UseOnContext context,
//		ToolAction toolAction,
//		boolean simulate
//	) {
//		if(context.getItemInHand().getItem() instanceof AxeItem) {
//			if(state.is(this) && !isStripped) {
//				return strippedVersion.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
//			}
//		}
//
//		return super.getToolModifiedState(state, context, toolAction, simulate);
//	}

}
