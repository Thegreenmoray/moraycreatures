package com.moray.moraymobs.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;


public class Stun extends MobEffect {

    public Stun(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if(!pLivingEntity.level().isClientSide()){
          pLivingEntity.setJumping(false);

           pLivingEntity.teleportTo(pLivingEntity.getX(),pLivingEntity.getY(),pLivingEntity.getZ());
           pLivingEntity.setDeltaMovement(0,-0.05,0);

       }


    super.applyEffectTick(pLivingEntity,pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}

