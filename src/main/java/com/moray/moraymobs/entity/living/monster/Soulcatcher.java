package com.moray.moraymobs.entity.living.monster;

import com.moray.moraymobs.ai.SoulBeamGoal;
import com.moray.moraymobs.ai.SoulCatcherFloatAroundGoal;
import com.moray.moraymobs.ai.SoulProjectileGoal;
import com.moray.moraymobs.entity.projectiles.Soulpiece;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Soulcatcher extends FlyingMob implements GeoEntity {
    public Soulcatcher(EntityType<Soulcatcher> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
moveControl=new SoulcatcherMoveControl(this);

    }
    private final AnimatableInstanceCache Cache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Byte> ANIMATION= SynchedEntityData.defineId(Soulcatcher.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> TIMER= SynchedEntityData.defineId(Soulcatcher.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BEAMTIMER= SynchedEntityData.defineId(Soulcatcher.class, EntityDataSerializers.INT);



    public int getbeamtimer(){
        return this.entityData.get(BEAMTIMER);
    }
    public void setBeamtimer(int anime){
        this.entityData.set(BEAMTIMER,anime);
    }


    public byte getanimation(){
        return this.entityData.get(ANIMATION);
    }
    public void setanimation(byte anime){
        this.entityData.set(ANIMATION,anime);
    }

    public int gettimer(){
        return this.entityData.get(TIMER);
    }
    public void settimer(int timer){
        this.entityData.set(TIMER,timer);
    }


    @Override
    public void tick() {
        super.tick();
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        this.setanimation(compound.getByte("animation"));
        this.settimer(compound.getInt("timer"));
        this.setBeamtimer(compound.getInt("soulanimation"));
        super.readAdditionalSaveData(compound);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        compound.putByte("animation",this.getanimation());
        compound.putInt("timer",this.gettimer());
        compound.putInt( "soulanimation",this.getbeamtimer());

        super.addAdditionalSaveData(compound);
    }


    protected void defineSynchedData() {
        this.entityData.define(ANIMATION, (byte)0);
        this.entityData.define(TIMER,0);
    this.entityData.define(BEAMTIMER,0);
        super.defineSynchedData();
    }

    protected PathNavigation createNavigation(Level pLevel) {
        return new FlyingPathNavigation(this, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH,35).add(Attributes.FOLLOW_RANGE, 25.0).add(Attributes.FLYING_SPEED,0.6);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (p_289460_) -> {
            return Math.abs(p_289460_.getY() - this.getY()) <= 4.0;}));
        this.goalSelector.addGoal(5, new SoulCatcherFloatAroundGoal(this));
   this.goalSelector.addGoal(2,new SoulProjectileGoal(this,15));
    this.goalSelector.addGoal(3,new SoulBeamGoal(this,30));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this,
                "Controller",this::animations));
    }

    private PlayState animations(AnimationState<Soulcatcher> soulcatcherAnimationState) {



        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return Cache;
    }

//may want to modify this.
    private static class SoulcatcherMoveControl extends MoveControl {
        private final Soulcatcher soulcatcher;
        private int floatDuration;

        public SoulcatcherMoveControl(Soulcatcher soulcatcher) {
            super(soulcatcher);
            this.soulcatcher = soulcatcher;
        }

        public void tick() {
            if (this.operation == Operation.MOVE_TO) {
                if (this.floatDuration-- <= 0) {
                    this.floatDuration += this.soulcatcher.getRandom().nextInt(5) + 2;
                    Vec3 $$0 = new Vec3(this.wantedX - this.soulcatcher.getX(), this.wantedY - this.soulcatcher.getY(), this.wantedZ - this.soulcatcher.getZ());
                    double $$1 = $$0.length();
                    $$0 = $$0.normalize();
                    if (this.canReach($$0, Mth.ceil($$1))) {
                        this.soulcatcher.setDeltaMovement(this.soulcatcher.getDeltaMovement().add($$0.scale(0.1)));
                    } else {
                        this.operation = Operation.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3 pPos, int pLength) {
            AABB $$2 = this.soulcatcher.getBoundingBox();

            for(int $$3 = 1; $$3 < pLength; ++$$3) {
                $$2 = $$2.move(pPos);
                if (!this.soulcatcher.level().noCollision(this.soulcatcher, $$2)) {
                    return false;
                }
            }

            return true;
        }
    }


}
