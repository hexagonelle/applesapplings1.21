package net.hexagonelle.applesaplings.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockSuppliers {

	// Create a planks block with the properties of the vanilla OAK_PLANKS
	public static Block createPlanks() {
		return new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
	}

}
