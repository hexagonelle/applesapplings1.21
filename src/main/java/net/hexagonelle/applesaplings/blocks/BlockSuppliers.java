package net.hexagonelle.applesaplings.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.jetbrains.annotations.NotNull;

public class BlockSuppliers {

	// Create a planks block with the properties of the vanilla OAK_PLANKS

	// Create a sapling with the given AbstractTreeGrower and the properties of the vanilla OAK_SAPLING
//	public static SaplingBlock createSapling(AbstractTreeGrower treeGrower){
//		return new SaplingBlock(treeGrower, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING));
//	}

	// Create a Flowering leaves block with the properties of the vanilla OAK_LEAVES
//	public static FloweringLeavesBlock createFloweringLeaves() {
//		return new FloweringLeavesBlock(
//			BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES));
//	}

	// Create a log or wood block with the properties of the given block
	public static Block createStrippedLogBlock() {
		return new CustomWood(BlockBehaviour.Properties
			.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
			.strength(2.0F)
//					.mapColor(CustomWood.woodMapColor(topMapColor,sideMapColor))
		).setIsStripped(true);
	}

	// Create a log or wood block with the properties of the given block
	public static Block createStrippedWoodBlock() {
		return new CustomWood(BlockBehaviour.Properties
			.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
			.strength(2.0F)
//					.mapColor(CustomWood.woodMapColor(topMapColor,sideMapColor))
		).setIsStripped(true);
	}

	// Create a stripped log or wood block with the properties of the given block
	public static Block createLogBlock(String strippedBlockKey) {
		return new CustomWood(BlockBehaviour.Properties
			.ofFullCopy(Blocks.OAK_LOG)
			.strength(2.0F)
//					.mapColor(CustomWood.woodMapColor(topMapColor,sideMapColor))
		).setStrippedVersion(BlockRegistry.BLOCK_MAP.get(strippedBlockKey).get());
	}

	// Create a stripped log or wood block with the properties of the given block
	public static Block createWoodBlock(String strippedBlockKey) {
		return new CustomWood(BlockBehaviour.Properties
			.ofFullCopy(Blocks.OAK_WOOD)
			.strength(2.0F)
//					.mapColor(CustomWood.woodMapColor(topMapColor,sideMapColor))
		).setStrippedVersion(BlockRegistry.BLOCK_MAP.get(strippedBlockKey).get());
	}

	// Create a planks block with the properties of the vanilla OAK_PLANKS
	public static Block createPlanks() {
		return new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
	}
	// Create a planks block with the properties of the vanilla OAK_PLANKS
	public static Block createLeaves() {
		return new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES))
		{
			@Override
			public boolean isFlammable(
				@NotNull BlockState state,
				@NotNull BlockGetter level,
				@NotNull BlockPos pos,
				@NotNull Direction direction) {
				return true;
			}
			@Override
			public int getFlammability(
				@NotNull BlockState state,
				@NotNull BlockGetter level,
				@NotNull BlockPos pos,
				@NotNull Direction direction) {
				return 60;
			}
			@Override
			public int getFireSpreadSpeed(
				@NotNull BlockState state,
				@NotNull BlockGetter level,
				@NotNull BlockPos pos,
				@NotNull Direction direction) {
				return 30;
			}
		};
	}public static Block createWoodStairBlock(String baseBlockString){
		return new StairBlock(BlockRegistry.BLOCK_MAP.get(baseBlockString).get().defaultBlockState(),
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
	}

	public static Block createWoodSlabBlock(){
		return new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB));
	}

	public static Block createWoodFenceBlock(){
		return new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE));
	}

	public static Block createWoodFenceGateBlock(){
		return new FenceGateBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE),
			SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
		);
	}

	public static Block createWoodDoorBlock(){
		return new DoorBlock(
			BlockSetType.OAK,
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).noOcclusion()
		);
	}

	public static Block createWoodTrapDoorBlock() {
		return new TrapDoorBlock(
			BlockSetType.OAK,
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR).noOcclusion()
		);
	}

	public static Block createWoodButton(){
		return new ButtonBlock(
			BlockSetType.OAK,
			10,
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
		);
	}

	public static Block createWoodPressurePlate(){
		return new PressurePlateBlock(
			BlockSetType.OAK,
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
		);
	}

}
