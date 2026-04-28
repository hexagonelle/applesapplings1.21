package net.hexagonelle.applesaplings.datagen.models;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.BlockStateMappings;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Map;

import static net.hexagonelle.applesaplings.blocks.BlockRegistry.BLOCK_MAP;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;
import static net.minecraft.resources.ResourceLocation.withDefaultNamespace;

public class ModBlockStateProvider extends BlockStateProvider {


	public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
		super(output, modid, exFileHelper);
	}

	public Block blockFromId(String blockId){
		return BLOCK_MAP.get(blockId).get();
	}

	public static String blockPathName(String blockId){return "block/" + blockId;}

	private ResourceLocation blockResource(String blockId) {
		return fromNamespaceAndPath(AppleSaplings.MODID, blockPathName(blockId));
	}

	public void blockItem(String blockId){
		Block block = BLOCK_MAP.get(blockId).get();
		simpleBlockItem(
			block,
			new ModelFile.UncheckedModelFile(blockResource(blockId)));
	}

	public void blockWithItem(String blockId){
		Block block = blockFromId(blockId);
		simpleBlockWithItem(block, cubeAll(block));
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

	@Override
	protected void registerStatesAndModels() {
		for (Map.Entry<String, BlockStateMappings.blockStateMethods> set :
			BlockStateMappings.blockBlockstateMap.entrySet()){
			String blockId = set.getKey();
			switch (set.getValue()) {
				case BLOCK_ITEM:
					blockItem(blockId);
					break;
				case SAPLING_BLOCK:
					saplingBlock(blockId);
					break;
				default:
					blockWithItem(blockId);
			}
		}
	}
}
