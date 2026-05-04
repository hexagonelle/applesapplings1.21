package net.hexagonelle.applesaplings.modclasses.render;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class ModModelLayers {

	private static ResourceLocation modLoc(String path){
		return fromNamespaceAndPath(AppleSaplings.MODID,path);
	}
	private static ResourceLocation mcLoc(String path){
		return fromNamespaceAndPath("minecraft",path);
	}

	private static ModelLayerLocation boatModelLayer(String woodType){
		return new ModelLayerLocation(modLoc("boat/" + woodType), "main");
	}

	private static ModelLayerLocation chestBoatModelLayer(String woodType){
		return new ModelLayerLocation(modLoc("chest_boat/" + woodType), "main");
	}

	public static final ModelLayerLocation APPLE_BOAT_LAYER =
		boatModelLayer("apple");
	public static final ModelLayerLocation APPLE_CHEST_BOAT_LAYER =
		chestBoatModelLayer("apple");

}