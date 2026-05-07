package net.hexagonelle.applesaplings.modclasses;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.Map;

import static net.hexagonelle.applesaplings.content.ModModelLayers.BOAT_LAYERS;
import static net.hexagonelle.applesaplings.content.ModModelLayers.CHEST_BOAT_LAYERS;

@EventBusSubscriber(modid = AppleSaplings.MODID, value = Dist.CLIENT)
public class ModEventBusClientEvents {
	@SubscribeEvent
	public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {

		for (Map.Entry<String, ModelLayerLocation> set : BOAT_LAYERS.entrySet()){
			ModelLayerLocation modelLayer = set.getValue();
			event.registerLayerDefinition(modelLayer, BoatModel::createBodyModel);
		}

		for (Map.Entry<String, ModelLayerLocation> set : CHEST_BOAT_LAYERS.entrySet()){
			ModelLayerLocation modelLayer = set.getValue();
			event.registerLayerDefinition(modelLayer, ChestBoatModel::createBodyModel);
		}

	}
}
