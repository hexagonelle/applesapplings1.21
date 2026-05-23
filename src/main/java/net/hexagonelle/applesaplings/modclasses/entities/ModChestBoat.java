package net.hexagonelle.applesaplings.modclasses.entities;


import net.hexagonelle.applesaplings.content.registers.EntityTypeRegistry;
import net.hexagonelle.applesaplings.content.ModBoat;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

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
	public @NotNull Item getDropItem() { return getModVariant().chestBoatItem.get(); }

	public void setVariant(ModBoat.Type variant) {
		this.entityData.set(DATA_ID_TYPE, variant.ordinal());
	}


	@Override
	protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
		super.defineSynchedData(builder);

		for(ModBoat.Type type : ModBoat.Type.values()){
			builder.define(DATA_ID_TYPE,type.ordinal());
		}

		builder.define(DATA_ID_TYPE, ModBoat.Type.APPLE.ordinal());

		builder.define(DATA_ID_TYPE,getModVariant().ordinal());

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

}
