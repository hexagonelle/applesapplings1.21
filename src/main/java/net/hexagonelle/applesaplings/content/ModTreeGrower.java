package net.hexagonelle.applesaplings.content;

import net.hexagonelle.applesaplings.content.registers.tree.TreeGrowerRegistry;
import net.minecraft.world.level.block.grower.TreeGrower;

public class ModTreeGrower{
	public static final TreeGrower APPLE;
	static{
		APPLE = TreeGrowerRegistry.registerTreeGrower("apple", ModTreeFeatures.APPLE);
	}
}
