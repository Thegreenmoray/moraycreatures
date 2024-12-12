package com.moray.moraymobs.entity.living.animal;

import com.moray.moraymobs.entity.abstractentity.Abstractfishmoray;
import com.moray.moraymobs.registries.Itemregististeries;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class LavaPaddleFish extends Abstractfishmoray implements GeoEntity {
  //  private static final EntityDataAccessor<Integer>  DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(LavaPaddleFish.class, EntityDataSerializers.INT);
//variants will be added eventally
    private final AnimatableInstanceCache Cache = GeckoLibUtil.createInstanceCache(this);

    public LavaPaddleFish(EntityType<? extends Abstractfishmoray> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward=5;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractFish.createMobAttributes().add(Attributes.MAX_HEALTH,10).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.FOLLOW_RANGE,10);
    }

  //  private void setPackedVariant(int pPackedVariant) {
   //     this.entityData.set(DATA_ID_TYPE_VARIANT, pPackedVariant);
   // }

  //  private int getPackedVariant() {
   //     return this.entityData.get(DATA_ID_TYPE_VARIANT);
   // }


    protected void handleAirSupply(int pAirSupply) {
        if (this.isAlive() && (!this.isInLava())) {
            this.setAirSupply(pAirSupply - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(this.damageSources().drown(), 2.0F);
            }
        } else {
            this.setAirSupply(300);
        }



    }


    public static boolean checkPaddlefishSpawnRules(EntityType<LavaPaddleFish> pfish, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        BlockPos.MutableBlockPos blockpos$mutable = pPos.mutable();

        do {
            blockpos$mutable.move(Direction.UP);
        } while(pLevel.getFluidState(blockpos$mutable).is(FluidTags.LAVA));

        return pLevel.getBlockState(blockpos$mutable).isAir();
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return source.is(DamageTypes.FALL) || source.is(DamageTypes.IN_WALL) ||source.is(DamageTypes.LAVA) ||super.isInvulnerableTo(source);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
     //   this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
     //   pCompound.putInt("Variant", this.getPackedVariant());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
     //   this.setPackedVariant(pCompound.getInt("Variant"));
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.TROPICAL_FISH_FLOP;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(Itemregististeries.BUCKETED_PADDLEFISH.get());
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this,
                "Controller",this::animations));
    }

    private PlayState animations(AnimationState<LavaPaddleFish> lavaPaddleFishAnimationState) {

       if (!lavaPaddleFishAnimationState.isMoving()){
           lavaPaddleFishAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.paddlefish.idle", Animation.LoopType.LOOP));
           return PlayState.CONTINUE;
       }

        if (lavaPaddleFishAnimationState.isMoving()){
            lavaPaddleFishAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.paddlefish.swim", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return Cache;
    }
}
