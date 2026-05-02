package net.hexagonelle.applesaplings.items;

import net.hexagonelle.applesaplings.entities.custom.ModBoat;
import net.hexagonelle.applesaplings.items.custom.ModBoatItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;

import static net.hexagonelle.applesaplings.blocks.BlockRegistry.BLOCK_MAP;

public class ItemSuppliers {

	// A method that creates a new BlockItem, given some DeferredBlock<Block>
	public static Item createBlockItem(String blockId){
		return new BlockItem(BLOCK_MAP.get(blockId).get(), new Item.Properties());
	}

	public static Item createSignItem(String woodType){
		Block standingSign = BLOCK_MAP.get(woodType + "_sign").get();
		Block wallSign = BLOCK_MAP.get(woodType + "_wall_sign").get();
		return new SignItem(new Item.Properties().stacksTo(16),standingSign,wallSign);
	}

	public static Item createBoatItem(String woodType){
		return new ModBoatItem(false, ModBoat.Type.APPLEWOOD, new Item.Properties());
	}

	public static Item createChestBoatItem(String woodType){
		return new ModBoatItem(true, ModBoat.Type.APPLEWOOD, new Item.Properties());
	}

}
