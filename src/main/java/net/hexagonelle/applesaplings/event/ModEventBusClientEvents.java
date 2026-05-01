package net.hexagonelle.applesaplings.event;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.blocks.blockentities.BlockEntityTypeRegistry;
import net.hexagonelle.applesaplings.entities.client.ModModelLayers;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = AppleSaplings.MODID, value = Dist.CLIENT)
public class ModEventBusClientEvents {
	@SubscribeEvent
	public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModModelLayers.APPLEWOOD_BOAT_LAYER, BoatModel::createBodyModel);
		event.registerLayerDefinition(ModModelLayers.APPLEWOOD_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
	}
	@SubscribeEvent
	public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(BlockEntityTypeRegistry.MOD_SIGN.get(), SignRenderer::new);
//		event.registerBlockEntityRenderer(BlockEntityTypeRegistry.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
	}
}
