package net.hexagonelle.applesaplings.content;

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



	@Override
	protected void generate() {
		generateWoodSetLoot("apple",Items.APPLE);
	}


	private static Block getModBlock(String blockId){
		return BLOCK_MAP.get(blockId).get();
	}

	private static Item getModItem(String blockId){
		return ITEM_MAP.get(blockId).get();
	}

	protected LootTable.Builder createFloweringLeavesDrops(
			Block leavesBlock,
			Block saplingBlock,
			Item fruitItem
		){

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

		return this.createLeavesDrops(leavesBlock, saplingBlock, NORMAL_LEAVES_SAPLING_CHANCES).withPool(lootPool);
	}

	protected LootTable.Builder FloweringLeavesLootFactory(
		Block floweringLeavesBlock,
		String woodTypeId,
		Item fruit
	){
		Block sapling = getModBlock(woodTypeId + "_sapling");
		return createFloweringLeavesDrops(floweringLeavesBlock, sapling, fruit);
	}

	protected void generateWoodSetLoot(String woodTypeId, Item fruit){

		Block saplingBlock = getModBlock(woodTypeId + "_sapling");
		Block leavesBlock = getModBlock(woodTypeId + "_leaves");
		Block floweringLeavesBlock = getModBlock("flowering_" + woodTypeId + "_leaves");
		Block logBlock = getModBlock(woodTypeId + "_log");
		Block woodBlock = getModBlock(woodTypeId + "_wood");
		Block strippedLogBlock = getModBlock("stripped_" + woodTypeId + "_log");
		Block strippedWoodBlock = getModBlock("stripped_" + woodTypeId + "_wood");
		Block planksBlock = getModBlock(woodTypeId + "_planks");
		Block signBlock = getModBlock(woodTypeId + "_sign");
		Item signItem = getModItem(woodTypeId + "_sign");
		Block wallSignBlock = getModBlock(woodTypeId + "_wall_sign");
		Block hangingSignBlock = getModBlock(woodTypeId + "_hanging_sign");
		Item hangingSignItem = getModItem(woodTypeId + "_hanging_sign");
		Block wallHangingSignBlock = getModBlock(woodTypeId + "_wall_hanging_sign");
		Block stairsBlock = getModBlock(woodTypeId + "_stairs");
		Block slabBlock = getModBlock(woodTypeId + "_slab");
		Block buttonBlock = getModBlock(woodTypeId + "_button");
		Block pressurePlateBlock = getModBlock(woodTypeId + "_pressure_plate");
		Block fenceBlock = getModBlock(woodTypeId + "_fence");
		Block fenceGateBlock = getModBlock(woodTypeId + "_fence_gate");
		Block doorBlock = getModBlock(woodTypeId + "_door");
		Block trapDoorBlock = getModBlock(woodTypeId + "_trapdoor");

		this.dropSelf(saplingBlock);
		this.add(leavesBlock, block -> createLeavesDrops(block, saplingBlock, NORMAL_LEAVES_SAPLING_CHANCES));
		this.add(floweringLeavesBlock, block -> FloweringLeavesLootFactory(block, woodTypeId, fruit));
		this.dropSelf(logBlock);
		this.dropSelf(woodBlock);
		this.dropSelf(strippedLogBlock);
		this.dropSelf(strippedWoodBlock);
		this.dropSelf(planksBlock);
		this.dropSelf(signBlock);
		this.dropSelf(hangingSignBlock);
		this.dropSelf(stairsBlock);
		this.dropSelf(slabBlock);
		this.dropSelf(buttonBlock);
		this.dropSelf(pressurePlateBlock);
		this.dropSelf(fenceBlock);
		this.dropSelf(fenceGateBlock);
		this.dropSelf(doorBlock);
		this.dropSelf(trapDoorBlock);
		this.add(wallSignBlock, block -> createSingleItemTable(signItem));
		this.add(wallHangingSignBlock, block -> createSingleItemTable(hangingSignItem));
	}

	@Override
	protected @NotNull Iterable<Block> getKnownBlocks(){
		return BLOCK_MAP.values().stream().map(DeferredBlock<Block>::get)::iterator;
	}
}
