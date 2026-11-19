package net.hexagonelle.applesaplings.datagen.providers;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.modclasses.loot.AddItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
	public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, AppleSaplings.MODID);
	}

		@Override
		protected void start() {

			this.add("orange_sapling_from_village_chest",
				new AddItemModifier(
					new LootItemCondition[] {
						new LootTableIdCondition.Builder(
							ResourceLocation.withDefaultNamespace("chests/village/village_fisher")
						).build()
					},
					"orange_sapling")
			);


		}


	}
