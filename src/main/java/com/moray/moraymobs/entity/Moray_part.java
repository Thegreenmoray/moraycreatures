package com.moray.moraymobs.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.entity.PartEntity;

public class Moray_part extends PartEntity<Moray> {
    public Moray_part(Moray parent) {
        super(parent);
    }

    public void setRot(float pitch, float yaw){
        super.setRot(pitch,yaw);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }
}
