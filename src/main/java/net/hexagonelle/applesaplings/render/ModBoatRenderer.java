package net.hexagonelle.applesaplings.render;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.modclasses.entities.ModBoat;
import net.hexagonelle.applesaplings.modclasses.entities.ModChestBoat;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.stream.Stream;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

public class ModBoatRenderer extends BoatRenderer {

	private final Map<ModBoat.Type, Pair<ResourceLocation,ListModel<Boat>>> boatResources;

	public ModBoatRenderer(EntityRendererProvider.Context context, boolean isChestBoat) {
		super(context, isChestBoat);

		this.boatResources =
			Stream
				.of(ModBoat.Type.values())
				.collect(
					ImmutableMap.toImmutableMap(
						type -> type,
						type -> Pair.of(
							fromNamespaceAndPath(
								AppleSaplings.MODID,
								getTextureLocation(type, isChestBoat)
							),this.createBoatModel(context, type, isChestBoat)
						)
					)
				);
	}


	private static String getTextureLocation(ModBoat.Type boatType, boolean isChestBoat) {
		return isChestBoat ?
			"textures/entity/chest_boat/" + boatType.getName() + ".png" :
			"textures/entity/boat/" + boatType.getName() + ".png";
	}

	private ListModel<Boat> createBoatModel(
		EntityRendererProvider.Context context,
		ModBoat.Type boatType,
		boolean isChestBoat
	){
		ModelLayerLocation modelLayerLocation =
			isChestBoat ?
				ModBoatRenderer.createChestBoatModelName(boatType) :
				ModBoatRenderer.createBoatModelName(boatType);

		ModelPart modelpart = context.bakeLayer(modelLayerLocation);
		return isChestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
	}

	public static ModelLayerLocation createBoatModelName(ModBoat.Type boatType) {
		return createLocation("boat/" + boatType.getName(), "main");
	}

	public static ModelLayerLocation createChestBoatModelName(ModBoat.Type boatType) {
		return createLocation("chest_boat/" + boatType.getName(), "main");
	}

	private static ModelLayerLocation createLocation(String path, String model) {
		return new ModelLayerLocation(fromNamespaceAndPath(AppleSaplings.MODID, path), model);
	}

	@Override
	public @NotNull Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(@NotNull Boat boat) {
		Pair<ResourceLocation,ListModel<Boat>> model = null;
		if(boat instanceof ModBoat modBoat){
			model = boatResources.get(modBoat.getModVariant());
		}
		if(boat instanceof ModChestBoat modChestBoat){
			model = boatResources.get(modChestBoat.getModVariant());
		}
			return model == null ? boatResources.get(ModBoat.Type.byName("applewood")) : model;
	}

}
