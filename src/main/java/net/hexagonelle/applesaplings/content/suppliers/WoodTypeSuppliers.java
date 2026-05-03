package net.hexagonelle.applesaplings.content.suppliers;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class WoodTypeSuppliers {

	public static WoodType createWoodType(
		String woodTypeId
	){
		return new WoodType(AppleSaplings.MODID + ":" + woodTypeId, BlockSetType.OAK);
	}

}
