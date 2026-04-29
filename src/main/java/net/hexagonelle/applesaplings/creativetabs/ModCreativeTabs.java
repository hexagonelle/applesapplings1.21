package net.hexagonelle.applesaplings.creativetabs;

public class ModCreativeTabs {

	public static void init() {

		CreativeTabRegistry.registerCreativeTab(
			"applesaplings_tab",
			"Apple Saplings",
			() -> CreativeTabSuppliers.createCreativeTab(
				"applesaplings_tab",
				"stripped_applewood_log"
			)
		);

	}

}
