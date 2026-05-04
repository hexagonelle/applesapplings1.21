package net.hexagonelle.applesaplings.content.worldgen.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hexagonelle.applesaplings.modclasses.worldgen.ModTreeDecoratorType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FloweringLeavesDecorator extends TreeDecorator {

	public static final MapCodec<FloweringLeavesDecorator> CODEC =
		RecordCodecBuilder.mapCodec(builder -> builder.group(
			Codec.floatRange(0.0F, 1.0F).fieldOf("probability")
				.forGetter((instance) -> instance.probability),
			Codec.intRange(1, 16).fieldOf("rolls")
				.forGetter((instance) -> instance.rolls),
			BlockStateProvider.CODEC.fieldOf("floweringLeaves")
				.forGetter((instance) -> instance.floweringLeavesProvider)
		).apply(builder, FloweringLeavesDecorator::new));

	/** Probability to generate flowering leaves on each roll */
	private final float probability;
	/** Number of attempts to generate flowering leaves per valid position */
	private final int rolls;
	/** FloweringLeavesBlock to spawn as decorator */
	private final BlockStateProvider floweringLeavesProvider;

	public FloweringLeavesDecorator(float spawnChance, int spawnAttempts, BlockStateProvider blockProvider) {
		this.probability = spawnChance;
		this.rolls = spawnAttempts;
		this.floweringLeavesProvider = blockProvider;
	}

	@Override
	protected @NotNull TreeDecoratorType<?> type() {
		return ModTreeDecoratorType.FLOWERING_LEAVES.get();
	}

	public void place(@NotNull Context context) {
		RandomSource randomSource = context.random();
		List<BlockPos> leavesList = context.leaves();

		List<BlockPos> spawnOptions =
			leavesList.stream().filter(
				positionOption -> isAirAdjacent(context,positionOption)
			).toList();

		if (!spawnOptions.isEmpty()) {
			spawnOptions.forEach((option) -> {
				for (int k = 0; k < rolls; ++k) {
					if (randomSource.nextFloat() < this.probability) {
						BlockPos blockPos = new BlockPos(option.getX(), option.getY(), option.getZ());
						context.setBlock(
							blockPos,
							this.floweringLeavesProvider.getState(randomSource,blockPos));
					}
				}
			});
		}
	}

	static {
//		CODEC =
//			BuiltInRegistries
//				.TREE_DECORATOR_TYPE
//				.byNameCodec()
//				.dispatch(FloweringLeavesDecorator::type, TreeDecoratorTypeRegistry::codec);
	}

	// check if the block in a given direction is
	private boolean isAirInDirection(
		@NotNull Context context,
		BlockPos blockPos,
		Direction direction
	){
		return context.isAir(blockPos.relative(direction));
	}

	private boolean isAirAdjacent(
		@NotNull Context context,
		BlockPos blockPos
	){
		return
			isAirInDirection(context, blockPos, Direction.NORTH) ||
			isAirInDirection(context, blockPos, Direction.EAST) ||
			isAirInDirection(context, blockPos, Direction.SOUTH) ||
			isAirInDirection(context, blockPos, Direction.WEST) ||
			isAirInDirection(context, blockPos, Direction.UP) ||
			isAirInDirection(context, blockPos, Direction.DOWN);
	}
}
