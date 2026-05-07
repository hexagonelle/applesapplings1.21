package net.hexagonelle.applesaplings.modclasses.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

public record StrippableData(String strippedBlockId) {
	public static final Codec<StrippableData> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			Codec.STRING.fieldOf("stripped_block").forGetter(StrippableData::strippedBlockId)
		).apply(instance, StrippableData::new)
	);

	public static final DataMapType<Block,StrippableData> STRIPPABLE_DATA = DataMapType.builder(
		ResourceLocation.fromNamespaceAndPath("neoforge","strippables"),
		Registries.BLOCK,
		StrippableData.CODEC
	).build();
}
