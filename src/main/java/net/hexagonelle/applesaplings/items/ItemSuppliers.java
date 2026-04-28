package net.hexagonelle.applesaplings.items;

import net.hexagonelle.applesaplings.blocks.BlockRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class ItemSuppliers {

	// A method that creates a new BlockItem, given some RegistryObject<Block>
	public static Item createBlockItem(String blockId){
		return new BlockItem(BlockRegistry.BLOCK_MAP.get(blockId).get(), new Item.Properties());
	}

}
