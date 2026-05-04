package net.hexagonelle.applesaplings.content;

import net.hexagonelle.applesaplings.content.registers.CreativeTabRegistry;
import net.hexagonelle.applesaplings.content.suppliers.CreativeTabSuppliers;

public class ModCreativeTabs {

	public static void init() {

		CreativeTabRegistry.registerCreativeTab(
			"applesaplings_tab",
			"Apple Saplings",
			() -> CreativeTabSuppliers.createCreativeTab(
				"applesaplings_tab",
				"stripped_apple_log"
			)
		);

	}

}
