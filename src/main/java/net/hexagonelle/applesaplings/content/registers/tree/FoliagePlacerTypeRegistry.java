package net.hexagonelle.applesaplings.content.registers.tree;

import com.mojang.serialization.MapCodec;
import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.modclasses.worldgen.SphereFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Supplier;

//// CURRENTLY UNUSED ////


public class FoliagePlacerTypeRegistry<P extends FoliagePlacer> {
//	public static final FoliagePlacerTypeRegistry<SphereFoliagePlacer> SPHERE_FOLIAGE_PLACER;
	private final MapCodec<P> codec;

	public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
		DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, AppleSaplings.MODID);

	// Creates a hashmap so that we can refer to a RegistryObject by a string
	public static final HashMap<String, DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<?>>> FOLIAGEPLACERTYPE_MAP = new LinkedHashMap<>();

	// A method that will register the DeferredRegister<FoliagePlacerType<?>> to the mod event bus
	public static void register(IEventBus eventBus) {
		FOLIAGE_PLACERS.register(eventBus);
	}

	// METHODS FOR REGISTERING FOLIAGE PLACER TYPES //

	// A method that creates the corresponding BlockItem and registers both Block and BlockItem under the blockID.
	public static void registerFoliagePlacerType(
		String foliagePlacerTypeId,
		Supplier<FoliagePlacerType<?>> foliagePlacerTypeSupplier
	) {
		DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<?>> deferredFoliagePlacerType = FOLIAGE_PLACERS.register(foliagePlacerTypeId, foliagePlacerTypeSupplier);
		FOLIAGEPLACERTYPE_MAP.put(foliagePlacerTypeId, deferredFoliagePlacerType);
	}

	public FoliagePlacerTypeRegistry(MapCodec<P> codec) {
		this.codec = codec;
	}

	public MapCodec<P> codec() {
		return this.codec;
	}

//	static {
//		SPHERE_FOLIAGE_PLACER = registerFoliagePlacerType("sphere_foliage_placer",
//			(radius,offset) -> new SphereFoliagePlacer(radius,offset));
//	}
}
