package net.hexagonelle.applesaplings.modclasses.worldgen;

import net.hexagonelle.applesaplings.AppleSaplings;
import net.hexagonelle.applesaplings.content.ModTreeFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER =
			new RegistrySetBuilder()
				.add(Registries.CONFIGURED_FEATURE, ModTreeFeatures::bootstrap);

	public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, BUILDER, Set.of(AppleSaplings.MODID));
	}
}
