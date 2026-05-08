package net.hexagonelle.applesaplings.content;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class ModModelLayers {

	public static final HashMap<String,ModelLayerLocation> BOAT_LAYERS = new LinkedHashMap<>();
	public static final HashMap<String,ModelLayerLocation> CHEST_BOAT_LAYERS = new LinkedHashMap<>();

	static{
		boatModelLayer("apple");
		chestBoatModelLayer("apple");

		boatModelLayer("orange");
		chestBoatModelLayer("orange");
	}

	private static ResourceLocation modLoc(String path){
		return fromNamespaceAndPath(AppleSaplings.MODID,path);
	}

	private static void boatModelLayer(String woodTypeId){
		ModelLayerLocation modelLayer = new ModelLayerLocation(modLoc("boat/" + woodTypeId), "main");
		BOAT_LAYERS.put(woodTypeId, modelLayer);
	}

	private static void chestBoatModelLayer(String woodTypeId){
		ModelLayerLocation modelLayer = new ModelLayerLocation(modLoc("chest_boat/" + woodTypeId), "main");
		CHEST_BOAT_LAYERS.put(woodTypeId, modelLayer);
	}

}