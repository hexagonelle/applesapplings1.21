package net.hexagonelle.applesaplings.modclasses.entities;

import net.hexagonelle.applesaplings.content.ModBoat;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public class ModBoatItem extends Item {
	private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
	private final ModBoat.Type type;
	private final boolean hasChest;

	public ModBoatItem(boolean hasChest, ModBoat.Type boatType, Properties properties) {
		super(properties);
		this.hasChest = hasChest;
		this.type = boatType;

	}

	@Override
	public @NotNull InteractionResultHolder<ItemStack> use(
		@NotNull Level level,
		Player player,
		@NotNull InteractionHand hand
	){
		ItemStack itemstack = player.getItemInHand(hand);
		HitResult hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
		if (hitresult.getType() == HitResult.Type.MISS) {
			return InteractionResultHolder.pass(itemstack);
		} else {
			Vec3 vec3 = player.getViewVector(1.0F);
			double d0 = 5.0F;
			List<Entity> list = level.getEntities(
				player,
				player.getBoundingBox().expandTowards(vec3.scale(d0)).inflate(1.0F),
				ENTITY_PREDICATE
			);
			if (!list.isEmpty()) {
				Vec3 vec31 = player.getEyePosition();

				for(Entity entity : list) {
					AABB aabb = entity.getBoundingBox().inflate(entity.getPickRadius());
					if (aabb.contains(vec31)) {
						return InteractionResultHolder.pass(itemstack);
					}
				}
			}

			if (hitresult.getType() == HitResult.Type.BLOCK) {
				Boat boat = this.getBoat(level, hitresult, itemstack, player);

				if (boat instanceof ModChestBoat chestBoat){
					chestBoat.setVariant(this.type);
				} else if (boat instanceof ModBoat modBoat){
					modBoat.setVariant(this.type);
				}

				boat.setYRot(player.getYRot());
				if (!level.noCollision(boat, boat.getBoundingBox())) {
					return InteractionResultHolder.fail(itemstack);
				} else {
					if (!level.isClientSide) {
						level.addFreshEntity(boat);
						level.gameEvent(player, GameEvent.ENTITY_PLACE, hitresult.getLocation());
						itemstack.consume(1, player);
					}

					player.awardStat(Stats.ITEM_USED.get(this));
					return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
				}
			} else {
				return InteractionResultHolder.pass(itemstack);
			}
		}
	}

	private Boat getBoat(Level level, HitResult hitResult, ItemStack stack, Player player) {
		Vec3 vec3 = hitResult.getLocation();
		Boat boat = (
			this.hasChest ?
				new ModChestBoat(level, vec3.x, vec3.y, vec3.z) :
				new ModBoat(level, vec3.x, vec3.y, vec3.z)
		);
		if (level instanceof ServerLevel serverlevel) {
			EntityType.createDefaultStackConfig(serverlevel, stack, player).accept(boat);
		}

		return boat;
	}
}