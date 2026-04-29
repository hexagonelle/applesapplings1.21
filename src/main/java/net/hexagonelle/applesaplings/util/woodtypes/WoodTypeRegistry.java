package net.hexagonelle.applesaplings.util.woodtypes;

import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.HashMap;

public class WoodTypeRegistry {

	// Creates a hashmap so that we can refer to a RegistryObject by a string
	public static final HashMap<String, WoodType> WOODTYPE_MAP = new HashMap<>();

	static{
		ModWoodTypes.init();
	}

	// METHODS TO REGISTER WOODTYPES //

	public static void registerWooodType(
		String woodTypeId
	){
		WOODTYPE_MAP.put(woodTypeId, WoodType.register(WoodTypeSuppliers.createWoodType(woodTypeId)));
	}

}
