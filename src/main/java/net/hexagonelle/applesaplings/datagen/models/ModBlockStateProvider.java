package net.hexagonelle.applesaplings.datagen.models;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.BlockRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.hexagonelle.applesaplings.blocks.BlockRegistry.BLOCK_MAP;
import static net.minecraft.resources.ResourceLocation.*;

public class ModBlockStateProvider extends BlockStateProvider {


	public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
		super(output, modid, exFileHelper);
	}



	public static final Map<BlockStateMethodArgPair.blockStateMethods, List<String>> methodArgsMap = new HashMap<>();

	public static final Map<String, BlockStateMethodArgPair> blockBlockstateMap = new HashMap<>();

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
		String parent,
		ResourceLocation texture,
		String renderAs
	){
		return models().singleTexture(
			blockId, mcLoc(parent),
			"all", texture
		).renderType(renderAs);
	}

	public void blockItem(String blockId){
		Block block = BLOCK_MAP.get(blockId).get();
		simpleBlockItem(
			block,
			new ModelFile.UncheckedModelFile(blockResource(blockId)));
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
				blockId, parent,
				blockTexture(block), renderAs
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

	public void saplingBlock(String blockId){
		Block block = blockFromId(blockId);
		simpleBlock(
			block,
			models()
				.cross(blockId, blockTexture(block))
				.renderType("cutout")
		);
	}

	// creates a model json and texture json for the given LeavesBlock
	private void customLeaves(String leavesIdPrefix){
		blockWithItem(leavesIdPrefix + "_leaves","block/leaves","cutout");
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
		blockWithItem(woodType + "_planks","block/oak_planks","solid");
	}

	public void customWoodStairs(String woodType){
		stairsBlock(
			(StairBlock) BlockRegistry.BLOCK_MAP.get(woodType + "_stairs").get(),
			blockResource(woodType + "_planks")
		);
	}

	public void customWoodSlab(String woodType){
		Block baseBlock = BlockRegistry.BLOCK_MAP.get(woodType + "_planks").get();
		slabBlock(
			(SlabBlock) BlockRegistry.BLOCK_MAP.get(woodType + "_slab").get(),
			blockTexture(baseBlock), blockTexture(baseBlock)
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
				default:
					cubeBlockWithItem(argsList.getFirst());
			}
		}
	}
}
