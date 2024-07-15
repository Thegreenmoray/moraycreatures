package com.moray.moraymobs.entity;

import com.moray.moraymobs.ai.Basaltliskeatgoal;
import com.moray.moraymobs.registries.Mobregistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.UUID;

public class Basaltlisk extends Animal implements NeutralMob, GeoEntity {
    private static final EntityDataAccessor<Boolean> EATEN=SynchedEntityData.defineId(Basaltlisk.class,EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Byte> TOUNGE=SynchedEntityData.defineId(Basaltlisk.class,EntityDataSerializers.BYTE);
    private final AnimatableInstanceCache Cache = GeckoLibUtil.createInstanceCache(this);

    private static final UniformInt PERSISTENT_ANGER_TIME= TimeUtil.rangeOfSeconds(20, 39);
    private int remainingPersistentAngerTime;
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;


  public byte gettoungetime(){
      return this.entityData.get(TOUNGE);
  }

  public void settoungetime(byte toungetime){
      this.entityData.set(TOUNGE,toungetime);
  }

public boolean has_eaten(){
    return this.entityData.get(EATEN);
}

public void set_eaten(boolean eaten){
    this.entityData.set(EATEN,eaten);
}

    public Basaltlisk(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.LAVA, -1.0F);

    }

    protected @NotNull PathNavigation createNavigation(Level pLevel) {
        return new WallClimberNavigation(this, pLevel);
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 40.0).add(Attributes.MOVEMENT_SPEED, 0.2).add(Attributes.ATTACK_DAMAGE, 3.0).add(Attributes.FOLLOW_RANGE, 16.0);
    }

    public boolean isOnFire() {
        return false;
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.addPersistentAngerSaveData(pCompound);
     pCompound.putBoolean("eat",this.has_eaten());
     pCompound.putByte("tounge",this.gettoungetime());
  }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.readPersistentAngerSaveData(this.level(), pCompound);
        this.set_eaten(pCompound.getBoolean("eat"));
       this.settoungetime(pCompound.getByte("tounge"));
  }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(5, new TemptGoal(this, 1.25, Ingredient.of(new ItemLike[]{
                Items.MAGMA_CREAM}), false));
        this.goalSelector.addGoal(1,new Basaltliskeatgoal(this));
        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MagmaCube.class, true, false));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
this.entityData.define(EATEN,false);
this.entityData.define(TOUNGE,(byte)0);
  }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return Mobregistries.BASALTISK.get().create(serverLevel);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    @Override
    public void setRemainingPersistentAngerTime(int i) {
        this.remainingPersistentAngerTime = i;
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID uuid) {
        this.persistentAngerTarget = uuid;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    public  boolean isFood(ItemStack itemStack){
        return itemStack.is(Items.MAGMA_CREAM);
    }


  //  @Override
   // public boolean isMultipartEntity() {
   //     return super.isMultipartEntity(); honeslty this may be considered later
   // }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this,
                "Controller",this::animations));
    }

    private PlayState animations(AnimationState<Basaltlisk> basaltliskAnimationState) {

    if (this.gettoungetime()==1){
        basaltliskAnimationState.getController().setAnimation(RawAnimation.begin().then("eat", Animation.LoopType.PLAY_ONCE));
        return PlayState.CONTINUE;
    }

       if(basaltliskAnimationState.isMoving()){
           basaltliskAnimationState.getController().setAnimation(RawAnimation.begin().then("basaltlisk.walk", Animation.LoopType.LOOP));
           return PlayState.CONTINUE;
       }
       if (!basaltliskAnimationState.isMoving()){
           basaltliskAnimationState.getController().setAnimation(RawAnimation.begin().then("basaltlisk.idle", Animation.LoopType.LOOP));
           return PlayState.CONTINUE;
       }


        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return Cache;
    }
}
