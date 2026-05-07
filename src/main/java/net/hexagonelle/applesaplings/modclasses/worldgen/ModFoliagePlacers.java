package net.hexagonelle.applesaplings.modclasses.worldgen;

//// CURRENTLY UNUSED ////

//public class ModFoliagePlacers {
//
//	// HELPER METHODS //
//
//	// Create a Deferred Register to hold FoliagePlacerTypes which will all be registered under the "applesaplings" namespace
//	public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
//		DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, AppleSaplings.MODID);
//
//	// A method that will register the DeferredRegister<FoliagePlacerType<?>> to the mod event bus
//	public static void register(IEventBus eventBus) {
//		FOLIAGE_PLACERS.register(eventBus);
//	}
//
//	// METHODS FOR REGISTERING FOLIAGE PLACER TYPES //
//
//	// A method that creates the corresponding BlockItem and registers both Block and BlockItem under the blockID.
//	public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<AppleFoliagePlacer>> APPLE_FOLIAGE_PLACER =
//		FOLIAGE_PLACERS.register("apple_foliage_placer", () -> new FoliagePlacerType<>(AppleFoliagePlacer.CODEC));
//}
