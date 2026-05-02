package net.hexagonelle.applesaplings.entities.custom;


import net.hexagonelle.applesaplings.entities.EntityTypeRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static net.hexagonelle.applesaplings.items.ItemRegistry.ITEM_MAP;

public class ModChestBoat extends ChestBoat {
	private static final EntityDataAccessor<Integer> DATA_ID_TYPE =
		SynchedEntityData.defineId(ModChestBoat.class, EntityDataSerializers.INT);

	public ModChestBoat(EntityType<? extends ChestBoat> entityType, Level level) {
		super(entityType, level);
	}

	public ModChestBoat(Level level, double pX, double pY, double pZ) {
		this(EntityTypeRegistry.MOD_CHEST_BOAT.get(), level);
		this.setPos(pX, pY, pZ);
		this.xo = pX;
		this.yo = pY;
		this.zo = pZ;
	}

	@Override
	public @NotNull Item getDropItem() {
		Item item;
		switch (getModVariant()) {
			case APPLEWOOD:
				item = ITEM_MAP.get("applewood_chest_boat").get();
				break;
			default:
				item = ITEM_MAP.get("applewood_chest_boat").get();
		}
		return item;
	}

	public void setVariant(ModBoat.Type variant) {
		this.entityData.set(DATA_ID_TYPE, variant.ordinal());
	}


	@Override
	protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_ID_TYPE, ModBoat.Type.APPLEWOOD.ordinal());
	};

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		compound.putString("Type", this.getModVariant().getSerializedName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		if (compound.contains("Type", 8)) {
			this.setVariant(ModBoat.Type.byName(compound.getString("Type")));
		}
	}

	public ModBoat.Type getModVariant() {
		return ModBoat.Type.byId(this.entityData.get(DATA_ID_TYPE));
	}

//	public enum Type implements StringRepresentable, IExtensibleEnum {
//		APPLEWOOD(BLOCK_MAP.get("applewood_planks").get(), "applewood");
//
//		private final String name;
//		/** @deprecated */
//		@Deprecated
//		private final Block planks;
//		private final Supplier<Block> planksSupplier;
//		final Supplier<Item> boatItem;
//		final Supplier<Item> chestBoatItem;
//		private final Supplier<Item> stickItem;
//		private final boolean raft;
//		public static final StringRepresentable.EnumCodec<ModChestBoat.Type> CODEC = StringRepresentable.fromEnum(ModChestBoat.Type::values);
//		private static final IntFunction<ModChestBoat.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
//
//
//		Type(Block planks, String name) {
//			this(planks, name, false);
//		}
//
//		Type(Block planks, String name, boolean raft) {
//			this.name = name;
//			this.planks = planks;
//			this.planksSupplier = () -> planks;
//			this.boatItem = () -> Items.AIR;
//			this.chestBoatItem = () -> Items.AIR;
//			this.stickItem = () -> Items.STICK;
//			this.raft = raft;
//		}
//
//		Type(Supplier<Block> planks, String name, Supplier<Item> boatItem, Supplier<Item> chestBoatItem, Supplier<Item> stickItem, boolean raft) {
//			this.name = name;
//			this.planks = Blocks.AIR;
//			this.planksSupplier = planks;
//			this.boatItem = boatItem;
//			this.chestBoatItem = chestBoatItem;
//			this.stickItem = stickItem;
//			this.raft = raft;
//		}
//
//		public @NotNull String getSerializedName() {
//			return this.name;
//		}
//
//		public String getName() {
//			return this.name;
//		}
//
//		public Block getPlanks() {
//			return (Block)this.planksSupplier.get();
//		}
//
//		public Item getSticks() {
//			return (Item)this.stickItem.get();
//		}
//
//		public boolean isRaft() {
//			return this.raft;
//		}
//
//		public String toString() {
//			return this.name;
//		}
//
//		public static ModChestBoat.Type byId(int id) {
//			return BY_ID.apply(id);
//		}
//
//		public static ModChestBoat.Type byName(String name) {
//			return CODEC.byName(name, APPLEWOOD);
//		}
//	}

}
