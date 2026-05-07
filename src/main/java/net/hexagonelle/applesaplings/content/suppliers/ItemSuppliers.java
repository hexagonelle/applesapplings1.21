package net.hexagonelle.applesaplings.content.suppliers;

import net.hexagonelle.applesaplings.content.ModBoat;
import net.hexagonelle.applesaplings.modclasses.entities.ModBoatItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;

import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.BLOCK_MAP;

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

	public static Item createHangingSignItem(String woodType){
		Block ceilingHangingSign = BLOCK_MAP.get(woodType + "_hanging_sign").get();
		Block wallHangingSign = BLOCK_MAP.get(woodType + "_wall_hanging_sign").get();
		return new HangingSignItem(
			ceilingHangingSign,
			wallHangingSign,
			new Item.Properties().stacksTo(16)
		);
	}

	public static Item createBoatItem(String woodType){
		return new ModBoatItem(false, ModBoat.Type.APPLE, new Item.Properties());
	}

	public static Item createChestBoatItem(String woodType){
		return new ModBoatItem(true, ModBoat.Type.APPLE, new Item.Properties());
	}

}
