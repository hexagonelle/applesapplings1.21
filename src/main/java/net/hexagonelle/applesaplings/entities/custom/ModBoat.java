package net.hexagonelle.applesaplings.entities.custom;

import net.hexagonelle.applesaplings.entities.EntityTypeRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import net.neoforged.fml.common.asm.enumextension.NamedEnum;
import net.neoforged.fml.common.asm.enumextension.NetworkedEnum;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;
import java.util.function.Supplier;

import static net.hexagonelle.applesaplings.blocks.BlockRegistry.BLOCK_MAP;
import static net.hexagonelle.applesaplings.items.ItemRegistry.ITEM_MAP;

public class ModBoat extends Boat {
	private static final EntityDataAccessor<Integer> DATA_ID_TYPE =
		SynchedEntityData.defineId(ModBoat.class, EntityDataSerializers.INT);

	public ModBoat(EntityType<? extends ModBoat> entityType, Level level) {
		super(entityType, level);
	}

	public ModBoat(Level level, double pX, double pY, double pZ) {
		this(EntityTypeRegistry.MOD_BOAT.get(), level);
		this.setPos(pX, pY, pZ);
		this.xo = pX;
		this.yo = pY;
		this.zo = pZ;
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		if (compound.contains("Type", 8)) {
			this.setVariant(Type.byName(compound.getString("Type")));
		}
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		compound.putString("Type", this.getModVariant().getSerializedName());
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_ID_TYPE, ModBoat.Type.APPLEWOOD.ordinal());
	};

	public void setVariant(Type variant) {
		this.entityData.set(DATA_ID_TYPE, variant.ordinal());
	}

	public Type getModVariant() {
		return Type.byId(this.entityData.get(DATA_ID_TYPE));
	}

	public @NotNull Item getDropItem() {
		Item item;
		switch (getModVariant()) {
			case APPLEWOOD:
				item = ITEM_MAP.get("applewood_boat").get();
				break;
			default:
				item = ITEM_MAP.get("applewood_boat").get();
		}

		return item;
	}

	public enum Type implements StringRepresentable, IExtensibleEnum {
		APPLEWOOD(BLOCK_MAP.get("applewood_planks").get(), "applewood");

		private final String name;
		private final Supplier<Block> planksSupplier;
		final Supplier<Item> boatItem;
		final Supplier<Item> chestBoatItem;
		private final Supplier<Item> stickItem;
		private final boolean raft;
		public static final StringRepresentable.EnumCodec<ModBoat.Type> CODEC = StringRepresentable.fromEnum(ModBoat.Type::values);
		private static final IntFunction<ModBoat.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);


		Type(Block planks, String name) {
			this(planks, name, false);
		}

		Type(Block planks, String name, boolean raft) {
			this.name = name;
			this.planksSupplier = () -> planks;
			this.boatItem = () -> Items.AIR;
			this.chestBoatItem = () -> Items.AIR;
			this.stickItem = () -> Items.STICK;
			this.raft = raft;
		}

		Type(Supplier<Block> planks, String name, Supplier<Item> boatItem, Supplier<Item> chestBoatItem, Supplier<Item> stickItem, boolean raft) {
			this.name = name;
			this.planksSupplier = planks;
			this.boatItem = boatItem;
			this.chestBoatItem = chestBoatItem;
			this.stickItem = stickItem;
			this.raft = raft;
		}

		public @NotNull String getSerializedName() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}

		public Block getPlanks() {
			return (Block)this.planksSupplier.get();
		}

		public Item getSticks() {
			return (Item)this.stickItem.get();
		}

		public boolean isRaft() {
			return this.raft;
		}

		public String toString() {
			return this.name;
		}

		public static ModBoat.Type byId(int id) {
			return BY_ID.apply(id);
		}

		public static ModBoat.Type byName(String name) {
			return CODEC.byName(name, APPLEWOOD);
		}
	}

}
