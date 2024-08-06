package com.moray.moraymobs.entity;

import com.moray.moraymobs.registries.Mobregistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;
import java.util.UUID;

public class Moray extends Monster implements GeoEntity {

    private final AnimatableInstanceCache Cache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Integer> ANIMATION= SynchedEntityData.defineId(Moray.class, EntityDataSerializers.INT);


    private static final EntityDataAccessor<Optional<UUID>> CHILD_UUID = SynchedEntityData.defineId(Moray.class, EntityDataSerializers.OPTIONAL_UUID);

Morayjaw morayjaw;

Moraybody moraybody;
    public Moray(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new MorayMoveControl(this);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.hasUUID("ChildUUID")) {
            this.setChildId(compound.getUUID("ChildUUID"));
        }
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.getChildId() != null) {
            compound.putUUID("ChildUUID", this.getChildId());
        }
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CHILD_UUID, Optional.empty());
    }


    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH,25).add(Attributes.FOLLOW_RANGE, 20.0).add(Attributes.MOVEMENT_SPEED, 0.3).add(Attributes.ATTACK_DAMAGE, 5.0).add(Attributes.ARMOR, 2.0);
    }

    protected void registerGoals() {
       this.goalSelector.addGoal(1, new MoraySwimGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

    }

    public Entity getChild() {
        UUID id = getChildId();
        if (id != null && !this.level().isClientSide) {
            return ((ServerLevel) level()).getEntity(id);
        }
        return null;
    }

    @Nullable
    public UUID getChildId() {
        return this.entityData.get(CHILD_UUID).orElse(null);
    }

    public void setChildId(@Nullable UUID uniqueId) {
        this.entityData.set(CHILD_UUID, Optional.ofNullable(uniqueId));
    }


    @Override
    public boolean isInvulnerableTo(DamageSource pSource) {
        return pSource.is(DamageTypes.DROWN) || pSource.is(DamageTypes.IN_WALL)||super.isInvulnerableTo(pSource);
    }

    @Override
    public void aiStep() {
        if (!this.level().isClientSide) {

            final Entity connectee=getChild();
       if (connectee==null){
            morayjaw = new Morayjaw(this.level(),this);

            moraybody = new Moraybody(this.level(),this);
setNoGravity(false);
      morayjaw.setParent(this);
      moraybody.setParent(this);
        this.setChildId(moraybody.getUUID());
        moraybody.setPos(this.getX(),this.getY(),this.getZ());
        morayjaw.setPos(this.getX(),this.getY(),this.getZ());
                level().addFreshEntity(morayjaw);
                level().addFreshEntity(moraybody);}}


        super.aiStep();
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }


    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this,
                "Controller",this::animations));
    }

    private PlayState animations(AnimationState<Moray> morayAnimationState) {






       if (!morayAnimationState.isMoving()){
           morayAnimationState.getController().setAnimation(RawAnimation.begin().then("moray.idle", Animation.LoopType.LOOP));
return PlayState.CONTINUE;
       }



        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return Cache;
    }


    private static class MorayMoveControl extends MoveControl {
        private final Moray moray;

        MorayMoveControl(Moray moray) {
            super(moray);
            this.moray = moray;
        }

        public void tick() {
            if (this.moray.isEyeInFluid(FluidTags.WATER)) {
                this.moray.setDeltaMovement(this.moray.getDeltaMovement().add(0.0, 0.005, 0.0));
            }

            if (this.operation == Operation.MOVE_TO && !this.moray.getNavigation().isDone()) {
                float $$0 = (float)(this.speedModifier * this.moray.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.moray.setSpeed(Mth.lerp(0.125F, this.moray.getSpeed(), $$0));
                double $$1 = this.wantedX - this.moray.getX();
                double $$2 = this.wantedY - this.moray.getY();
                double $$3 = this.wantedZ - this.moray.getZ();
                if ($$2 != 0.0) {
                    double $$4 = Math.sqrt($$1 * $$1 + $$2 * $$2 + $$3 * $$3);
                    this.moray.setDeltaMovement(this.moray.getDeltaMovement().add(0.0, (double)this.moray.getSpeed() * ($$2 / $$4) * 0.1, 0.0));
                }

                if ($$1 != 0.0 || $$3 != 0.0) {
                    float $$5 = (float)(Mth.atan2($$3, $$1) * 57.2957763671875) - 90.0F;
                    this.moray.setYRot(this.rotlerp(this.moray.getYRot(), $$5, 90.0F));
                    this.moray.yBodyRot = this.moray.getYRot();
                }

            } else {
                this.moray.setSpeed(0.0F);
            }
        }
    }



    static class MoraySwimGoal extends RandomSwimmingGoal {
        private final Moray moray;

        public MoraySwimGoal(Moray moray) {
            super(moray, 1.0, 40);
            this.moray = moray;
        }
        protected boolean canRandomSwim() {
            return true;
        }
        public boolean canUse() {
            return this.canRandomSwim() && super.canUse();
        }
    }


    }







