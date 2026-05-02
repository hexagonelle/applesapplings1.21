package net.hexagonelle.applesaplings.entities;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.entities.custom.ModBoat;
import net.hexagonelle.applesaplings.entities.custom.ModChestBoat;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;



public class EntityTypeRegistry {

	// Create a Deferred Register to hold EntityType under the "applesaplings" namespace
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
		DeferredRegister.create(Registries.ENTITY_TYPE, AppleSaplings.MODID);

	// A method that will register the DeferredRegister<Block> to the mod event bus
	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}

	// For creating boats:
	public static final Supplier<EntityType<ModBoat>> MOD_BOAT =
		ENTITY_TYPES.register(
			"mod_boat",
			() -> EntityType.Builder.<ModBoat>of(
					ModBoat::new,
					MobCategory.MISC
				).sized(1.375f, 0.5625f)
				.build("mod_boat")
		);


	public static final Supplier<EntityType<ModChestBoat>> MOD_CHEST_BOAT =
		ENTITY_TYPES.register(
			"mod_chest_boat",
			() -> EntityType.Builder.<ModChestBoat>of(
					ModChestBoat::new,
					MobCategory.MISC
				).sized(1.375f, 0.5625f)
				.build("mod_chest_boat")
		);

}
