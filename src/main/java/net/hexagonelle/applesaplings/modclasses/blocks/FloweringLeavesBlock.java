package net.hexagonelle.applesaplings.modclasses.blocks;

//import net.minecraftforge.common.ForgeHooks;


//public class FloweringLeavesBlock extends LeavesBlock {
//	public static final int MAX_AGE = 3;
//	public static Item FRUIT_ITEM = Items.APPLE;
//	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
//	private static final float growthSpeed = 3.0f;
//	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
//
//	public FloweringLeavesBlock(Properties properties) {
//		super(properties);
//		this.registerDefaultState(
//				this.stateDefinition.any()
//						.setValue(
//								DISTANCE, 1
//						).setValue(
//								PERSISTENT, false
//						).setValue(
//								AGE, 0
//						).setValue(
//								WATERLOGGED, false
//						)
//		);
//	}
//
//	// Returns the age of a given blockstate.
//	public int getAge(BlockState blockState) {
//		return blockState.getValue(this.getAgeProperty());
//	}
//
//	// Returns the total number of growth stages.
//	public int getMaxAge(){
//		return MAX_AGE;
//	}
//
//	// If the leaves are not fully grown, they can be grown further.
//	protected boolean growing(BlockState currentBlockState) {
//		return this.getAge(currentBlockState) < this.getMaxAge();
//	}
//
//	// Return whether this block needs random ticking.
//	public boolean isRandomlyTicking(@NotNull BlockState currentBlockState) {
//		return this.decaying(currentBlockState) || this.growing(currentBlockState);
//	}
//
//	@Override
//	public boolean isFlammable(
//		@NotNull BlockState blockstate,
//		@NotNull BlockGetter level,
//		@NotNull BlockPos blockPos,
//		@NotNull Direction direction){
//		return true;
//	}
//
//	@Override
//	public int getFlammability(
//		@NotNull BlockState blockstate,
//		@NotNull BlockGetter level,
//		@NotNull BlockPos blockPos,
//		@NotNull Direction direction){
//		return 60;
//	}
//
//	@Override
//	public int getFireSpreadSpeed(
//		@NotNull BlockState blockstate,
//		@NotNull BlockGetter level,
//		@NotNull BlockPos blockPos,
//		@NotNull Direction direction){
//		return 30;
//	}
//
//	public IntegerProperty getAgeProperty() {
//		return AGE;
//	}
//
//	// Returns a blockstate equal to
//	// what *this* blockstate *would* be,
//	// if the AGE property was set to the given age.
//	public BlockState getStateForAge(int givenAge) {
//		return this.defaultBlockState().setValue(AGE, givenAge);
//	}
//
//	@Override
//	//Performs a random tick on a block.
//	public void randomTick(
//			@NotNull BlockState currentBlockState,
//			@NotNull ServerLevel serverLevel,
//			@NotNull BlockPos blockPosition,
//			@NotNull RandomSource random
//	){
//		// If the leaves should be decaying, remove the block
//		// and drop the corresponding resources
//		if (this.decaying(currentBlockState)) {
//			dropResources(currentBlockState, serverLevel, blockPosition);
//			serverLevel.removeBlock(blockPosition, false);
//		// If the leaves can be grown further
//		} else if (this.growing(currentBlockState)) {
//			// and if the random tick allows it
//			if (
//					ForgeHooks.onCropsGrowPre(
//							serverLevel,
//							blockPosition,
//							currentBlockState,
//							random.nextInt((int)(25.0F / growthSpeed) + 1) == 0
//					)
//			){
//				// then increment the blockstate by 1
//				serverLevel.setBlock(
//					blockPosition,
//					this.getStateForAge(getAge(currentBlockState) + 1),
//						2
//				);
//				ForgeHooks.onCropsGrowPost(serverLevel, blockPosition, currentBlockState);
//			}
//		}
//	}
//
//
//	public @NotNull InteractionResult use(
//			@NotNull BlockState currentBlockState,
//			@NotNull Level serverLevel,
//			@NotNull BlockPos blockPos,
//			@NotNull Player player,
//			@NotNull InteractionHand handIn,
//			@NotNull BlockHitResult hit
//	) {
//		if (getAge(currentBlockState) == getMaxAge()) {
//			Direction hitDirection = hit.getDirection();
//			BlockPos spawnApplePos = blockPos.relative(hitDirection);
//			System.out.println("BlockPosition:"+blockPos.getX()+","+blockPos.getY()+","+blockPos.getZ());
//			System.out.println("HitDirection:"+hitDirection);
//			System.out.println("SpawnApplePosition:"+spawnApplePos.getX()+","+spawnApplePos.getY()+","+spawnApplePos.getZ());
//			ItemEntity droppedFruit =
//				new ItemEntity(
//					serverLevel,
//					spawnApplePos.getX() + 0.5,
//					spawnApplePos.getY() + 0.5,
//					spawnApplePos.getZ() + 0.5,
//					new ItemStack(FRUIT_ITEM, 1));
//			droppedFruit.spawnAtLocation(FRUIT_ITEM,0);
//			//ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.APPLE));
//			// then set blockstate back to 1
//			serverLevel.setBlock(
//				blockPos,
//					this.getStateForAge(0),
//					1
//			);
//			return InteractionResult.SUCCESS;
//		}
//		return super.use(currentBlockState, serverLevel, blockPos, player, handIn, hit);
//	}
//
//	@Override
//	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
//		builder.add(DISTANCE, PERSISTENT, WATERLOGGED, AGE);
//	}
//}
