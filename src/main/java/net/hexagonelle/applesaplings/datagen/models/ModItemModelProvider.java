package net.hexagonelle.applesaplings.datagen.models;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static net.minecraft.resources.ResourceLocation.withDefaultNamespace;

public class ModItemModelProvider extends ItemModelProvider {
	public ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
		super(output, modid, existingFileHelper);
	}

	private ResourceLocation blockResource(String blockId){
		return modLoc("block/" + blockId);
	}

	public void flatItemModel(
		String name,
		ResourceLocation texture
	){
		singleTexture(name, withDefaultNamespace("item/generated"), "layer0", texture);
	}

	// generates a flat item form of a block with the texture taken from the block texture
	public void blockItemWithFlatBlockTexture(String blockId) {
		flatItemModel(blockId, blockResource(blockId));
	}

	@Override
	protected void registerModels() {

	}
}
