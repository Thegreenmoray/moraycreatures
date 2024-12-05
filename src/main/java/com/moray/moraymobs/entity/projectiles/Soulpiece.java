package com.moray.moraymobs.entity.projectiles;

import com.moray.moraymobs.entity.living.monster.Volcanoback;
import com.moray.moraymobs.registries.Mobregistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Soulpiece extends AbstractHurtingProjectile implements GeoEntity {

    private final AnimatableInstanceCache Cache = GeckoLibUtil.createInstanceCache(this);

int timer=50;
    public Soulpiece(Level pLevel) {
        super(Mobregistries.SOULPROJECTILE.get(), pLevel);
    }


    @Override
    public void tick() {
        super.tick();

 Vec3 de =getDeltaMovement();
        setDeltaMovement(de.x(),de.y()-0.001,de.z());
timer--;

if (timer<=0){
    remove(RemovalReason.DISCARDED);
}
    }

    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level().isClientSide) {
            Entity entity = pResult.getEntity();
            if(!(entity instanceof Volcanoback)){
                entity.hurt(this.damageSources().generic(),4);
                remove(RemovalReason.DISCARDED);
            }}}

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        remove(RemovalReason.DISCARDED);
    }

    public Soulpiece(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this,
                "Controller",this::animations));
    }

    private PlayState animations(AnimationState<Soulpiece> soulpieceAnimationState) {
        soulpieceAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.soul.spin", Animation.LoopType.PLAY_ONCE));


        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return Cache;
    }
}
