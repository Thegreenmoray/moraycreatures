package com.moray.moraymobs.entity.abstractentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;
import java.util.function.Predicate;

public abstract class Abstractfishmoray extends WaterAnimal implements Bucketable {

    private static final EntityDataAccessor<Boolean> FROM_BUCKET;

    protected Abstractfishmoray(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new Abstractfishmoray.FishMoveControl(this);
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return pSize.height * 0.65F;
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    public int getMaxSpawnClusterSize() {
        return 8;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BUCKET, false);
    }





    public boolean fromBucket() {
        return (Boolean)this.entityData.get(FROM_BUCKET);
    }

    public void setFromBucket(boolean pFromBucket) {
        this.entityData.set(FROM_BUCKET, pFromBucket);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("FromBucket", this.fromBucket());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFromBucket(pCompound.getBoolean("FromBucket"));
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25));
        GoalSelector var10000 = this.goalSelector;
        Predicate var10009 = EntitySelector.NO_SPECTATORS;
        Objects.requireNonNull(var10009);
        var10000.addGoal(2, new AvoidEntityGoal(this, Player.class, 8.0F, 1.6, 1.4, var10009::test));
        this.goalSelector.addGoal(4, new Abstractfishmoray.FishSwimGoal(this));
    }

    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.01F, pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(pTravelVector);
        }

    }

    public void aiStep() {
        if (!this.isInWater() && this.onGround() && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), 0.4000000059604645, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
            this.setOnGround(false);
            this.hasImpulse = true;
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
        }

        super.aiStep();
    }

    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        return (InteractionResult)Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
    }

    public void saveToBucketTag(ItemStack pStack) {
        Bucketable.saveDefaultDataToBucketTag(this, pStack);
    }

    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);
    }

    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    protected boolean canRandomSwim() {
        return true;
    }

    protected abstract SoundEvent getFlopSound();

    protected SoundEvent getSwimSound() {
        return SoundEvents.FISH_SWIM;
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
    }

    static {
        FROM_BUCKET = SynchedEntityData.defineId(Abstractfishmoray.class, EntityDataSerializers.BOOLEAN);
    }

    private static class FishMoveControl extends MoveControl {
        private final Abstractfishmoray fish;

        FishMoveControl(Abstractfishmoray pFish) {
            super(pFish);
            this.fish = pFish;
        }

        public void tick() {
            if (this.fish.isEyeInFluid(FluidTags.WATER)) {
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0, 0.005, 0.0));
            }

            if (this.operation == Operation.MOVE_TO && !this.fish.getNavigation().isDone()) {
                float $$0 = (float)(this.speedModifier * this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.fish.setSpeed(Mth.lerp(0.125F, this.fish.getSpeed(), $$0));
                double $$1 = this.wantedX - this.fish.getX();
                double $$2 = this.wantedY - this.fish.getY();
                double $$3 = this.wantedZ - this.fish.getZ();
                if ($$2 != 0.0) {
                    double $$4 = Math.sqrt($$1 * $$1 + $$2 * $$2 + $$3 * $$3);
                    this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0, (double)this.fish.getSpeed() * ($$2 / $$4) * 0.1, 0.0));
                }

                if ($$1 != 0.0 || $$3 != 0.0) {
                    float $$5 = (float)(Mth.atan2($$3, $$1) * 57.2957763671875) - 90.0F;
                    this.fish.setYRot(this.rotlerp(this.fish.getYRot(), $$5, 90.0F));
                    this.fish.yBodyRot = this.fish.getYRot();
                }

            } else {
                this.fish.setSpeed(0.0F);
            }
        }
    }

    static class FishSwimGoal extends RandomSwimmingGoal {
        private final Abstractfishmoray fish;

        public FishSwimGoal(Abstractfishmoray pFish) {
            super(pFish, 1.0, 40);
            this.fish = pFish;
        }

        public boolean canUse() {
            return this.fish.canRandomSwim() && super.canUse();
        }
    }
}


