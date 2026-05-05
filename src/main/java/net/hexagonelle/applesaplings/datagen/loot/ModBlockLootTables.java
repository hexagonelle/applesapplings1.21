package net.hexagonelle.applesaplings.datagen.loot;

import net.hexagonelle.applesaplings.modclasses.blocks.FloweringLeavesBlock;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.BLOCK_MAP;
import static net.hexagonelle.applesaplings.content.registers.ItemRegistry.ITEM_MAP;
import static net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition.hasBlockStateProperties;


public class ModBlockLootTables extends BlockLootSubProvider {
	public ModBlockLootTables(
		HolderLookup.Provider registries
	){
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(),registries);
	}

	protected LootTable.Builder createFloweringLeavesDrops(
			Block leavesBlock,
			Block saplingBlock,
			Item fruitItem,
			float... chances) {

		// convert fruitItem to LootTableItem
		LootPoolSingletonContainer.Builder<?> fruitLoot = LootItem.lootTableItem(fruitItem);
		// convert chances to NumberProvider
		NumberProvider chancesProvider = ConstantValue.exactly(1.0F);
		// create condition that player cannot have shears or silk touch
		LootItemCondition.Builder NO_SHEARS_OR_SILK_TOUCH = (HAS_SHEARS.or(hasSilkTouch())).invert();
		// define fully grown state
		StatePropertiesPredicate.Builder fullyGrownState =
				StatePropertiesPredicate.Builder.properties()
						.hasProperty(FloweringLeavesBlock.AGE, FloweringLeavesBlock.MAX_AGE);
		// convert fullyGrownState to a condition
		LootItemBlockStatePropertyCondition.Builder fullyGrown =
				hasBlockStateProperties(leavesBlock).setProperties(fullyGrownState);
		// define lootPool that will drop fruit when leaves are fully grown
		LootPool.Builder lootPool =
				LootPool.lootPool()
						.setRolls(chancesProvider)
						.when(NO_SHEARS_OR_SILK_TOUCH)
						.add(this.applyExplosionCondition(leavesBlock, fruitLoot).when(fullyGrown));

		return this.createLeavesDrops(leavesBlock, saplingBlock, chances).withPool(lootPool);
	}

	protected LootTable.Builder FloweringLeavesLootFactory(
			Block block,
			DeferredBlock<Block> sapling,
			Item fruit
	){
        return createFloweringLeavesDrops(block, sapling.get(), fruit, NORMAL_LEAVES_SAPLING_CHANCES);
	}

	@Override
	protected void generate() {
		this.dropSelf(BLOCK_MAP.get("apple_sapling").get());
		this.add(
			BLOCK_MAP.get("apple_leaves").get(),
			block -> createLeavesDrops(
				block,
				BLOCK_MAP.get("apple_sapling").get(),
				NORMAL_LEAVES_SAPLING_CHANCES
			)
		);
		this.add(
			BLOCK_MAP.get("flowering_apple_leaves").get(),
			block -> FloweringLeavesLootFactory(
				block,
				BLOCK_MAP.get("apple_sapling"),
				Items.APPLE
			)
		);
		this.dropSelf(BLOCK_MAP.get("apple_log").get());
		this.dropSelf(BLOCK_MAP.get("apple_wood").get());
		this.dropSelf(BLOCK_MAP.get("stripped_apple_log").get());
		this.dropSelf(BLOCK_MAP.get("stripped_apple_wood").get());
		this.dropSelf(BLOCK_MAP.get("apple_planks").get());
		this.dropSelf(BLOCK_MAP.get("apple_sign").get());
		this.dropSelf(BLOCK_MAP.get("apple_hanging_sign").get());
		this.dropSelf(BLOCK_MAP.get("apple_stairs").get());
		this.dropSelf(BLOCK_MAP.get("apple_slab").get());
		this.dropSelf(BLOCK_MAP.get("apple_button").get());
		this.dropSelf(BLOCK_MAP.get("apple_pressure_plate").get());
		this.dropSelf(BLOCK_MAP.get("apple_fence").get());
		this.dropSelf(BLOCK_MAP.get("apple_fence_gate").get());
		this.dropSelf(BLOCK_MAP.get("apple_door").get());
		this.dropSelf(BLOCK_MAP.get("apple_trapdoor").get());
		this.add(
			BLOCK_MAP.get("apple_wall_sign").get(),
			block -> createSingleItemTable(ITEM_MAP.get("apple_sign").get())
		);
		this.add(
			BLOCK_MAP.get("apple_wall_hanging_sign").get(),
			block -> createSingleItemTable(ITEM_MAP.get("apple_hanging_sign").get())
		);
	}

	@Override
	protected @NotNull Iterable<Block> getKnownBlocks(){


		return BLOCK_MAP.values().stream().map(DeferredBlock<Block>::get)::iterator;
	}
}
