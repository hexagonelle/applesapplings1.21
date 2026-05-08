package net.hexagonelle.applesaplings.content;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.BLOCK_MAP;
import static net.hexagonelle.applesaplings.content.registers.ItemRegistry.ITEM_MAP;

public class ModRecipeProvider extends RecipeProvider {
	public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries){
		super(output,registries);
	}

	@Override
	protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
		woodSetRecipes(
			recipeOutput,
			"apple",
			Items.APPLE,
			Items.OAK_SAPLING,
			ModItemTags.APPLE_LOGS
		);
		woodSetRecipes(
			recipeOutput,
			"orange",
			Items.AXOLOTL_SPAWN_EGG,
			Items.ACACIA_SAPLING,
			ModItemTags.ORANGE_LOGS
		);
	}

	private static Block getModBlock(String blockId){
		return BLOCK_MAP.get(blockId).get();
	}

	protected static void saplingFromFruit(
		RecipeOutput recipeOutput,
		String woodTypeId,
		Item fruit,
		ItemLike baseSapling
	){
		ShapelessRecipeBuilder.shapeless(
				RecipeCategory.MISC,
				getModBlock(woodTypeId + "_sapling"),
				1
			).unlockedBy(getHasName(baseSapling),has(fruit))
			.requires(baseSapling).requires(fruit)
			.save(recipeOutput);
	}

	protected static void woodFromLogs(
		RecipeOutput recipeOutput,
		String woodTypeId
	){
		Block wood = getModBlock(woodTypeId + "_wood");
		Block log = getModBlock(woodTypeId + "_log");
		woodFromLogs(recipeOutput, wood, log);
	}

	protected static void planksFromLog(
		RecipeOutput recipeOutput,
		String woodTypeId,
		TagKey<Item> log
	){
		Block planks = getModBlock(woodTypeId + "_planks");
		planksFromLog(recipeOutput, planks, log,4);
	}

	protected static void stairsFromPlanks(
		RecipeOutput recipeOutput,
		String woodTypeId
	){
		Block stairs = getModBlock(woodTypeId + "_stairs");
		Block planks = getModBlock(woodTypeId + "_planks");
		Block log = getModBlock(woodTypeId + "_log");
		Ingredient material = Ingredient.of(planks);
		RecipeProvider.stairBuilder(stairs, material)
			.unlockedBy("has_log", has(log))
			.save(recipeOutput);
	}

	protected static void slabFromPlanks(
		RecipeOutput recipeOutput,
		String woodTypeId
	){
		Block slab = getModBlock(woodTypeId + "_slab");
		Block material = getModBlock(woodTypeId + "_planks");
		RecipeProvider.slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, slab, material);
	}

	protected static void fenceFromPlanks(
		RecipeOutput recipeOutput,
		String woodTypeId
	){
		Block fence = getModBlock(woodTypeId + "_fence");
		Block planks = getModBlock(woodTypeId + "_planks");
		Block log = getModBlock(woodTypeId + "_log");
		Ingredient material = Ingredient.of(planks);
		RecipeProvider.fenceBuilder(fence,material)
			.unlockedBy("has_log", has(log))
			.save(recipeOutput);
	}

	protected static void fenceGateFromPlanks(
		RecipeOutput recipeOutput,
		String woodTypeId
	){
		Block fenceGate = getModBlock(woodTypeId + "_fence_gate");
		Block planks = getModBlock(woodTypeId + "_planks");
		Block log = getModBlock(woodTypeId + "_log");
		Ingredient material = Ingredient.of(planks);
		RecipeProvider.fenceGateBuilder(fenceGate,material)
			.unlockedBy("has_log", has(log))
			.save(recipeOutput);
	}

	protected static void doorFromPlanks(
		RecipeOutput recipeOutput,
		String woodTypeId
	){
		Block door = getModBlock(woodTypeId + "_door");
		Block planks = getModBlock(woodTypeId + "_planks");
		Block log = getModBlock(woodTypeId + "_log");
		Ingredient material = Ingredient.of(planks);
		RecipeProvider.doorBuilder(door,material)
			.unlockedBy("has_log", has(log))
			.save(recipeOutput);
	}

	protected static void trapdoorfromPlanks(
		RecipeOutput recipeOutput,
		String woodTypeId
	){
		Block trapdoor = getModBlock(woodTypeId + "_trapdoor");
		Block planks = getModBlock(woodTypeId + "_planks");
		Block log = getModBlock(woodTypeId + "_log");
		Ingredient material = Ingredient.of(planks);
		RecipeProvider.trapdoorBuilder(trapdoor,material)
			.unlockedBy("has_log", has(log))
			.save(recipeOutput);
	}

	protected static void buttonFromPlanks(
		RecipeOutput recipeOutput,
		String woodTypeId
	){
		Block button = getModBlock(woodTypeId + "_button");
		Block planks = getModBlock(woodTypeId + "_planks");
		Block log = getModBlock(woodTypeId + "_log");
		Ingredient material = Ingredient.of(planks);
		RecipeProvider.buttonBuilder(button,material)
			.unlockedBy("has_log", has(log))
			.save(recipeOutput);
	}

	protected static void pressurePlateFromPlanks(
		RecipeOutput recipeOutput,
		String woodTypeId
	){
		Block pressurePlate = getModBlock(woodTypeId + "_pressure_plate");
		Block material = getModBlock(woodTypeId + "_planks");
		RecipeProvider.pressurePlate(recipeOutput,pressurePlate,material);
	}

	protected static void signFromPlanks(
		RecipeOutput recipeOutput,
		String woodTypeId
	){
		Item sign = ITEM_MAP.get(woodTypeId + "_sign").get();
		Block planks = getModBlock(woodTypeId + "_planks");
		Block log = getModBlock(woodTypeId + "_log");
		Ingredient material = Ingredient.of(planks);
		RecipeProvider.signBuilder(sign,material)
			.unlockedBy("has_log", has(log))
			.save(recipeOutput);
	}

	protected static void hangingSignFromPlanks(
		RecipeOutput recipeOutput,
		String woodTypeId
	){
		Item hangingSign = ITEM_MAP.get(woodTypeId + "_hanging_sign").get();
		Block material = getModBlock(woodTypeId + "_planks");
		RecipeProvider.hangingSign(recipeOutput,hangingSign,material);
	}

	protected static void boatFromPlanks(
		RecipeOutput recipeOutput,
		String woodTypeId
	){
		Item boat = ITEM_MAP.get(woodTypeId + "_boat").get();
		Block material = getModBlock(woodTypeId + "_planks");
		RecipeProvider.woodenBoat(recipeOutput,boat,material);
	}

	protected static void chestBoatFromPlanks(
		RecipeOutput recipeOutput,
		String woodTypeId
	){
		Item chestBoat = ITEM_MAP.get(woodTypeId + "_chest_boat").get();
		Item material = ITEM_MAP.get(woodTypeId + "_boat").get();
		RecipeProvider.chestBoat(recipeOutput,chestBoat,material);
	}

	protected static void woodSetRecipes(
		RecipeOutput recipeOutput,
		String woodTypeId,
		Item fruit,
		Item baseSapling,
		TagKey<Item> logsTag
	) {
		saplingFromFruit(recipeOutput, woodTypeId, fruit, baseSapling);
		woodFromLogs(recipeOutput, woodTypeId);
		woodFromLogs(recipeOutput, "stripped_" + woodTypeId);
		planksFromLog(recipeOutput, woodTypeId, logsTag);
		stairsFromPlanks(recipeOutput, woodTypeId);
		slabFromPlanks(recipeOutput, woodTypeId);
		fenceFromPlanks(recipeOutput, woodTypeId);
		fenceGateFromPlanks(recipeOutput, woodTypeId);
		doorFromPlanks(recipeOutput, woodTypeId);
		trapdoorfromPlanks(recipeOutput, woodTypeId);
		buttonFromPlanks(recipeOutput, woodTypeId);
		pressurePlateFromPlanks(recipeOutput, woodTypeId);
		signFromPlanks(recipeOutput, woodTypeId);
		hangingSignFromPlanks(recipeOutput, woodTypeId);
		boatFromPlanks(recipeOutput, woodTypeId);
		chestBoatFromPlanks(recipeOutput, woodTypeId);
	}
}
