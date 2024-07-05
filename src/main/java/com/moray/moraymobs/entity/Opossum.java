package com.moray.moraymobs.entity;

import com.moray.moraymobs.ai.PossumFaintgoal;
import com.moray.moraymobs.ai.PossumScreamgoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class Opossum extends Animal {
    protected static final EntityDataAccessor<Boolean> FAINTED= SynchedEntityData.defineId(Opossum.class, EntityDataSerializers.BOOLEAN);

    protected Opossum(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public boolean isfainted(){
        return this.entityData.get(FAINTED);
    }

    public void setFainted(boolean fainted){
        this.entityData.set(FAINTED,fainted);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FAINTED, false);
    }





    protected void registerGoals() {
        this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(5, new TemptGoal(this, 1.25, Ingredient.of(new ItemLike[]{
                Items.ROTTEN_FLESH}), false));
        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    this.goalSelector.addGoal(2,new PossumScreamgoal(this,50));
    this.goalSelector.addGoal(3, new PossumFaintgoal(this,100));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }







}
