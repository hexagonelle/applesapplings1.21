package net.hexagonelle.applesaplings.content;

import net.hexagonelle.applesaplings.content.registers.EntityTypeRegistry;
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
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;
import java.util.function.Supplier;

import static net.hexagonelle.applesaplings.content.registers.BlockRegistry.BLOCK_MAP;
import static net.hexagonelle.applesaplings.content.registers.ItemRegistry.ITEM_MAP;

public class ModBoat extends Boat {

	public @NotNull Item getDropItem() {
//		return getModVariant().boatItem.get();
		return ITEM_MAP.get(getModVariant().name + "_boat").get();
	}

	public enum Type implements StringRepresentable {
		APPLE("apple"),
		ORANGE("orange");





		public final String name;
		private final Supplier<Block> planksSupplier;
		final Supplier<Item> boatItem;
		public final Supplier<Item> chestBoatItem;
		public static final StringRepresentable.EnumCodec<ModBoat.Type> CODEC = StringRepresentable.fromEnum(ModBoat.Type::values);
		private static final IntFunction<ModBoat.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

		Type(String woodTypeId){

			Supplier<Block> planks = BLOCK_MAP.get(woodTypeId + "_planks");
//			Supplier<Item> boatItem = ITEM_MAP.get(woodTypeId + "_boat");
//			Supplier<Item> chestBoatItem = ITEM_MAP.get(woodTypeId + "_chest_boat");

			Supplier<Item> boatItem = () -> Items.AIR;
			Supplier<Item> chestBoatItem = () -> Items.AIR;

			this.name = woodTypeId;
			this.planksSupplier = planks;
			this.boatItem = boatItem;
			this.chestBoatItem = chestBoatItem;
		}

		public @NotNull String getSerializedName() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}

		public Block getPlanks() {
			return this.planksSupplier.get();
		}

		public String toString() {
			return this.name;
		}

		public static ModBoat.Type byId(int id) {
			return BY_ID.apply(id);
		}

		public static ModBoat.Type byName(String name) {
			return CODEC.byName(name, APPLE);
		}
	}

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

		builder.define(DATA_ID_TYPE, ModBoat.Type.APPLE.ordinal());
//		builder.define(DATA_ID_TYPE, ModBoat.Type.ORANGE.ordinal());

//		builder.define(DATA_ID_TYPE,getModVariant().ordinal());

	};

	public void setVariant(Type variant) {
		this.entityData.set(DATA_ID_TYPE, variant.ordinal());
	}

	public Type getModVariant() {
		return Type.byId(this.entityData.get(DATA_ID_TYPE));
	}

}
