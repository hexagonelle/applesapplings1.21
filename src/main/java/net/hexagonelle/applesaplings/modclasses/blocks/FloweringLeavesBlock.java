package net.hexagonelle.applesaplings.modclasses.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.CommonHooks;
import org.jetbrains.annotations.NotNull;

import static net.neoforged.neoforge.common.CommonHooks.onRightClickBlock;


public class FloweringLeavesBlock extends LeavesBlock {
	public static final int MAX_AGE = 3;
	private final Item FRUIT_ITEM;
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	private static final float growthSpeed = 3.0f;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public FloweringLeavesBlock(Item fruit, Properties properties) {
		super(properties);
		this.FRUIT_ITEM = fruit;
		this.registerDefaultState(
				this.stateDefinition.any()
						.setValue(
								DISTANCE, 1
						).setValue(
								PERSISTENT, false
						).setValue(
								AGE, 0
						).setValue(
								WATERLOGGED, false
						)
		);
	}

	// Returns the age of a given blockstate.
	public int getAge(BlockState blockState) {
		return blockState.getValue(this.getAgeProperty());
	}

	// Returns the total number of growth stages.
	public int getMaxAge(){
		return MAX_AGE;
	}

	// If the leaves are not fully grown, they can be grown further.
	protected boolean growing(BlockState currentBlockState) {
		return this.getAge(currentBlockState) < this.getMaxAge();
	}

	// Return whether this block needs random ticking.
	public boolean isRandomlyTicking(@NotNull BlockState currentBlockState) {
		return this.decaying(currentBlockState) || this.growing(currentBlockState);
	}

	@Override
	public boolean isFlammable(
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
		return 60;
	}

	@Override
	public int getFireSpreadSpeed(
		@NotNull BlockState blockstate,
		@NotNull BlockGetter level,
		@NotNull BlockPos blockPos,
		@NotNull Direction direction){
		return 30;
	}

	public IntegerProperty getAgeProperty() {
		return AGE;
	}

	// Returns a blockstate equal to
	// what *this* blockstate *would* be,
	// if the AGE property was set to the given age.
	public BlockState getStateForAge(int givenAge) {
		return this.defaultBlockState().setValue(AGE, givenAge);
	}

	@Override
	//Performs a random tick on a block.
	public void randomTick(
			@NotNull BlockState currentBlockState,
			@NotNull ServerLevel serverLevel,
			@NotNull BlockPos blockPosition,
			@NotNull RandomSource random
	){
		// If the leaves should be decaying, remove the block
		// and drop the corresponding resources
		if (this.decaying(currentBlockState)) {
			dropResources(currentBlockState, serverLevel, blockPosition);
			serverLevel.removeBlock(blockPosition, false);
		// If the leaves can be grown further
		} else if (this.growing(currentBlockState)) {
			// and if the random tick allows it
			if (
					CommonHooks.canCropGrow(
						serverLevel,
						blockPosition,
						currentBlockState,
						random.nextInt((int)(25.0F / growthSpeed) + 1) == 0
						)
			){
				// then increment the blockstate by 1
				serverLevel.setBlock(
					blockPosition,
					this.getStateForAge(getAge(currentBlockState) + 1),
						2
				);
				CommonHooks.fireCropGrowPost(serverLevel, blockPosition, currentBlockState);
			}
		}
	}

	protected @NotNull InteractionResult useWithoutItem(
		@NotNull BlockState currentBlockState,
		@NotNull Level serverLevel,
		@NotNull BlockPos blockPos,
		@NotNull Player player,
		@NotNull BlockHitResult hit
	) {
		if (getAge(currentBlockState) == getMaxAge()) {
			Direction hitDirection = hit.getDirection();
			BlockPos spawnApplePos = blockPos.relative(hitDirection);
			System.out.println("BlockPosition:"+blockPos.getX()+","+blockPos.getY()+","+blockPos.getZ());
			System.out.println("HitDirection:"+hitDirection);
			System.out.println("SpawnApplePosition:"+spawnApplePos.getX()+","+spawnApplePos.getY()+","+spawnApplePos.getZ());
			ItemEntity droppedFruit =
				new ItemEntity(
					serverLevel,
					spawnApplePos.getX() + 0.5,
					spawnApplePos.getY() + 0.5,
					spawnApplePos.getZ() + 0.5,
					new ItemStack(FRUIT_ITEM, 1));
			droppedFruit.spawnAtLocation(FRUIT_ITEM,0);
			//ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.APPLE));
			// then set blockstate back to 1
			serverLevel.setBlock(
				blockPos,
					this.getStateForAge(0),
					1
			);
			return InteractionResult.SUCCESS;
		}
		return super.useWithoutItem(currentBlockState,serverLevel,blockPos,player,hit);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
		builder.add(DISTANCE, PERSISTENT, WATERLOGGED, AGE);
	}
}
