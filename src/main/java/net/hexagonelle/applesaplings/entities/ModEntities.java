package net.hexagonelle.applesaplings.entities;

import net.hexagonelle.applesaplings.AppleSaplings;
//import net.hexagonelle.applesaplings.entities.custom.ModBoat;
//import net.hexagonelle.applesaplings.entities.custom.ModChestBoat;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

//public class ModEntities {
//	// Create a Deferred Register to hold EntityType which will all be registered under the "applesaplings" namespace
//	public static final net.neoforged.neoforge.registries.DeferredRegister<EntityType<?>> ENTITY_TYPES =
//		DeferredRegister.create(Registries.ENTITY_TYPE, AppleSaplings.MODID);
//
//	// A method that will register the DeferredRegister<BlockEntityType<?>> to the mod event bus
//	public static void register(IEventBus eventBus){
//		ENTITY_TYPES.register(eventBus);
//	}
//
//	// A method that registers an EntityType under the entityID
//	private static <T extends EntityType<?>> DeferredHolder<EntityType<?>,T> registerEntity(
//		String entityTypeID,
//		Supplier<T> entity
//	) {
//		return ENTITY_TYPES.register(entityTypeID, entity);
//	}
//
//	public static final DeferredRegister<EntityType<ModBoat>> MOD_BOAT =
//		registerEntity("mod_boat", EntityCreator::createBoat);
//
//	public static final DeferredRegister<EntityType<ModChestBoat>> MOD_CHEST_BOAT =
//		registerEntity("mod_chest_boat", EntityCreator::createChestBoat);
//
//}
