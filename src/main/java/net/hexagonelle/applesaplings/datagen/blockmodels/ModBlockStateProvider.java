package net.hexagonelle.applesaplings.datagen.blockmodels;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.content.registers.BlockRegistry;
import net.hexagonelle.applesaplings.modclasses.blocks.FloweringLeavesBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.BLOCK_MAP;
import static net.minecraft.resources.ResourceLocation.*;

public class ModBlockStateProvider extends BlockStateProvider {


	public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, AppleSaplings.MODID, exFileHelper);
	}

	public static final Map<String, BlockStateMethodArgPair> blockBlockstateMap = new LinkedHashMap<>();

	// registers a block and places the corresponding DeferredBlock in the HashMap by blockId
	public static void assignBlockstate(
		String blockId,
		BlockStateMethodArgPair methodArgPair
	) {
		blockBlockstateMap.put(blockId, methodArgPair);
	}

	public static Block blockFromId(String blockId){
		return BLOCK_MAP.get(blockId).get();
	}

	public static String blockPathName(String blockId){return "block/" + blockId;}
	public static String blockPathName(String namespace, String blockId){
		return namespace + ":block/" + blockId;
	}

	public static String blockPathNameWithState(String blockId, String state){
		return blockPathName(AppleSaplings.MODID, blockId) + state;
	}

	private ResourceLocation blockResource(String blockId) {
		return fromNamespaceAndPath(AppleSaplings.MODID, blockPathName(blockId));
	}

	private ResourceLocation blockResourceWithState(String blockId, String state) {
		return parse(blockPathNameWithState(blockId,state));
	}

	private ModelFile singleTextureModel(
		String blockId,
//		String parent,
		ResourceLocation texture
//		, String renderAs
	){
//		return models().singleTexture(
//			blockId, mcLoc(parent),
//			"all", texture
//		);
		return models().cubeAll(blockId,texture);
	}

	public void blockItem(String blockId){
		Block block = BLOCK_MAP.get(blockId).get();
		simpleBlockItem(
			block,
			new ModelFile.UncheckedModelFile(blockResource(blockId)));
	}

	// generates data for the item version of a block based on a specific blockstate
	private void blockItem(String blockId, String blockState){

		simpleBlockItem(
			BLOCK_MAP.get(blockId).get(),
			new ModelFile.UncheckedModelFile(blockResourceWithState(
				blockId,
				blockState
			))
		);
	}

	public void cubeBlock(String blockId){
		Block block = blockFromId(blockId);
		simpleBlock(block);
	}

	public void cubeBlockWithItem(String blockId){
		Block block = blockFromId(blockId);
		simpleBlockWithItem(block, cubeAll(block));
	}

	public void blockWithItem(String blockId,String parent,String renderAs){
		Block block = blockFromId(blockId);
		simpleBlockWithItem(
			block,
			singleTextureModel(
				blockId,
//				parent,
				blockTexture(block)
//				, renderAs
			)
		);
	}

	// generates data for the item version of a block based on a specific blockstate
	private void blockstateItem(String blockId, String blockState){
		simpleBlockItem(
			BlockRegistry.BLOCK_MAP.get(blockId).get(),
			new ModelFile.UncheckedModelFile(blockResourceWithState(blockId, blockState))
		);
	}

	public void saplingBlock(String woodTypeId){
		String blockId = woodTypeId + "_sapling";
		Block block = blockFromId(blockId);
		simpleBlock(
			block,
			models()
				.cross(blockId, blockTexture(block))
				.renderType("cutout")
		);
	}

	// creates a model json and texture json for the given LeavesBlock
	private void customLeaves(String woodTypeId){
//		blockWithItem(woodTypeId + "_leaves","block/leaves","cutout");
		cubeBlockWithItem(woodTypeId + "_leaves");
	}


	// creates a model json and texture json for the given blockstate of a FruitLeavesBlock
	private ConfiguredModel[] floweringLeavesStates(
		BlockState blockState,
		String woodTypeId
	){

		String blockId = "flowering_" + woodTypeId + "_leaves";
		DeferredBlock<Block> floweringLeavesDeferred = BLOCK_MAP.get(blockId);
		FloweringLeavesBlock floweringLeavesBlock = (FloweringLeavesBlock) floweringLeavesDeferred.get();
		String blockstateValue = String.valueOf(floweringLeavesBlock.getAge(blockState));

		ConfiguredModel[] models = new ConfiguredModel[1];

		models[0] = new ConfiguredModel(
			singleTextureModel(
//			blockPathNameWithState(blockId,blockstateValue), "block/leaves",
//			blockResourceWithState(blockId,blockstateValue),"cutout_mipped"
				blockPathNameWithState(blockId,blockstateValue),blockResourceWithState(blockId,blockstateValue)

			)
		);

		return models;
	}

	private void customFloweringLeaves(String woodTypeId){

		String blockId = "flowering_" + woodTypeId + "_leaves";
		FloweringLeavesBlock floweringLeavesBlock = (FloweringLeavesBlock) BLOCK_MAP.get(blockId).get();

		Function<BlockState,ConfiguredModel[]> function =
			state -> floweringLeavesStates(state,woodTypeId);

		getVariantBuilder(floweringLeavesBlock).forAllStates(function);
		blockItem(blockId,"0");

	}

	// generates model (and texture?) json for a stripped log block and its item
	private void customStrippedLog(String woodType){
		String logId = "stripped_" + woodType + "_log";
		logBlock((RotatedPillarBlock) BlockRegistry.BLOCK_MAP.get(logId).get());
		blockItem(logId);
	}

	// generates model (and texture?) json for a stripped wood block and its item
	private void customStrippedWood(String woodType){
		String logId = "stripped_" + woodType + "_log";
		String woodId = "stripped_" + woodType + "_wood";
		Block logBlock = BlockRegistry.BLOCK_MAP.get(logId).get();

		axisBlock(
			(RotatedPillarBlock) BlockRegistry.BLOCK_MAP.get(woodId).get(),
			blockTexture(logBlock), blockTexture(logBlock)
		);
		blockItem(woodId);
	}

	// generates model (and texture?) json for a log block and its item
	private void customLog(String woodType){
		String logId = woodType + "_log";
		logBlock((RotatedPillarBlock) BlockRegistry.BLOCK_MAP.get(logId).get());
		blockItem(logId);
	}

	// generates model (and texture?) json for a wood block and its item
	private void customWood(String woodType){
		String logId = woodType + "_log";
		String woodId = woodType + "_wood";
		Block logBlock = BlockRegistry.BLOCK_MAP.get(logId).get();

		axisBlock(
			(RotatedPillarBlock) BlockRegistry.BLOCK_MAP.get(woodId).get(),
			blockTexture(logBlock), blockTexture(logBlock)
		);
		blockItem(woodId);
	}

	private void customPlanks(String woodType){
		cubeBlockWithItem(woodType + "_planks");
	}

	public void customWoodStairs(String woodType){
		stairsBlock(
			(StairBlock) BlockRegistry.BLOCK_MAP.get(woodType + "_stairs").get(),
			blockResource(woodType + "_planks")
		);
	}

	public void customWoodSlab(String woodType){
		String baseBlockId = woodType + "_planks";
		slabBlock(
			(SlabBlock) BlockRegistry.BLOCK_MAP.get(woodType + "_slab").get(),
			blockResource(baseBlockId), blockResource(baseBlockId)
		);
	}

	public void customFence(String woodType){
		fenceBlock(
			(FenceBlock) BlockRegistry.BLOCK_MAP.get(woodType + "_fence").get(),
			blockTexture(BlockRegistry.BLOCK_MAP.get(woodType + "_planks").get())
		);
	}

	public void customFenceGate(String woodType){
		fenceGateBlock(
			(FenceGateBlock) BlockRegistry.BLOCK_MAP.get(woodType + "_fence_gate").get(),
			blockTexture(BlockRegistry.BLOCK_MAP.get(woodType + "_planks").get())
		);

	}public void customWoodDoor(String woodType){
		String doorId = woodType + "_door";
		doorBlockWithRenderType(
			(DoorBlock) BlockRegistry.BLOCK_MAP.get(doorId).get(),
			blockResourceWithState(doorId, "_bottom"),
			blockResourceWithState(doorId, "_top"),
			"cutout"
		);
	}

	public void customWoodTrapdoor(String woodType){
		String trapdoorId = woodType + "_trapdoor";
		trapdoorBlockWithRenderType(
			(TrapDoorBlock) BlockRegistry.BLOCK_MAP.get(trapdoorId).get(),
			blockResource(trapdoorId),
			true, "cutout"
		);
	}

	public void customButton(String woodType){
		buttonBlock(
			(ButtonBlock) BlockRegistry.BLOCK_MAP.get(woodType + "_button").get(),
			blockTexture(BlockRegistry.BLOCK_MAP.get(woodType + "_planks").get())
		);
	}

	public void customPressurePlate(String woodType){
		pressurePlateBlock(
			(PressurePlateBlock) BlockRegistry.BLOCK_MAP.get(woodType + "_pressure_plate").get(),
			blockTexture(BlockRegistry.BLOCK_MAP.get(woodType + "_planks").get())
		);
	}

	public void customSign(String woodType){
		Block standingSign = BLOCK_MAP.get(woodType + "_sign").get();
		Block wallSign = BLOCK_MAP.get(woodType + "_wall_sign").get();
		ResourceLocation texture = blockTexture(BlockRegistry.BLOCK_MAP.get(woodType + "_planks").get());
		signBlock((StandingSignBlock) standingSign, (WallSignBlock) wallSign,texture);
	}

	public void customHangingSign(String woodType){
		Block ceilingHangingSign = BLOCK_MAP.get(woodType + "_hanging_sign").get();
		Block wallHangingSign = BLOCK_MAP.get(woodType + "_wall_hanging_sign").get();
		ResourceLocation texture = blockTexture(BlockRegistry.BLOCK_MAP.get(woodType + "_planks").get());
		hangingSignBlock((CeilingHangingSignBlock) ceilingHangingSign, (WallHangingSignBlock) wallHangingSign,texture);
	}

	@Override
	protected void registerStatesAndModels() {
		for (Map.Entry<String, BlockStateMethodArgPair> set :
			blockBlockstateMap.entrySet()){
			BlockStateMethodArgPair methodArgPair = set.getValue();
			BlockStateMethodArgPair.blockStateMethods method = methodArgPair.getMethod();
			List<String> argsList = methodArgPair.getArguments();

			switch (method) {
				case BLOCK_ITEM:
					blockItem(argsList.getFirst());
					break;
				case SAPLING_BLOCK:
					saplingBlock(argsList.getFirst());
					break;
				case LEAVES:
					customLeaves(argsList.getFirst());
					break;
				case FLOWERING_LEAVES:
					customFloweringLeaves(argsList.getFirst());
					break;
				case STRIPPED_LOG:
					customStrippedLog(argsList.getFirst());
					break;
				case STRIPPED_WOOD:
					customStrippedWood(argsList.getFirst());
					break;
				case LOG:
					customLog(argsList.getFirst());
					break;
				case WOOD:
					customWood(argsList.getFirst());
					break;
				case PLANKS:
					customPlanks(argsList.getFirst());
					break;
				case WOOD_STAIRS:
					customWoodStairs(argsList.getFirst());
					break;
				case WOOD_SLAB:
					customWoodSlab(argsList.getFirst());
					break;
				case WOOD_FENCE:
					customFence(argsList.getFirst());
					break;
				case WOOD_FENCE_GATE:
					customFenceGate(argsList.getFirst());
					break;
				case WOOD_DOOR:
					customWoodDoor(argsList.getFirst());
					break;
				case WOOD_TRAPDOOR:
					customWoodTrapdoor(argsList.getFirst());
					break;
				case WOOD_BUTTON:
					customButton(argsList.getFirst());
					break;
				case WOOD_PRESSURE_PLATE:
					customPressurePlate(argsList.getFirst());
					break;
				case SIGN:
					customSign(argsList.getFirst());
					break;
				case HANGING_SIGN:
					customHangingSign(argsList.getFirst());
					break;
				default:
					cubeBlockWithItem(argsList.getFirst());
			}
		}
	}
}
