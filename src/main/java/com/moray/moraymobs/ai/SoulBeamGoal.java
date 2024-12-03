package com.moray.moraymobs.ai;

import com.moray.moraymobs.entity.living.monster.Soulcatcher;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SoulBeamGoal extends Goal {
    Soulcatcher soulcatcher;
    int timer;
    int count;
    Vec3 vec3d;
    Vec3 vec3d2;
    Vec3 vec3d3;
    public SoulBeamGoal(Soulcatcher soulcatcher,int timer){
        this.soulcatcher=soulcatcher;
        this.timer=timer;
    }

    @Override
    public void start() {
count=0;
        this.soulcatcher.setanimation(30);
    }

    @Override
    public void stop() {
count=0;
this.soulcatcher.setBeamtimer(0);
    }

    @Override
    public void tick() {
        LivingEntity livingEntity=this.soulcatcher.getTarget();

        if (livingEntity!=null){
            ++count;
            if (count<30){
   float x_diff= (float) (livingEntity.getX()-this.soulcatcher.getX());
   float z_diff=(float) (livingEntity.getZ()-this.soulcatcher.getZ());
    this.soulcatcher.setYRot((float) Mth.atan2(z_diff,x_diff));
}
if (count==20){
    vec3d = this.soulcatcher.position().add(0.0D, 0.200000023841858D, 0.0D);
    vec3d2 = livingEntity.getEyePosition().subtract(vec3d);//subtract preuiosly
    vec3d3 = vec3d2.normalize();
}


if (count==30){
//from warden sonic boom

    for (int i = 1; i < Mth.floor(vec3d2.length()) + 7; ++i) {
            Vec3 vec3d4 = vec3d.add(vec3d3.scale((double) i));
            ((ServerLevel) this.soulcatcher.level()).sendParticles(ParticleTypes.SOUL, vec3d4.x, vec3d4.y+0.5 , vec3d4.z, 3, this.soulcatcher.getRandom().nextFloat(), this.soulcatcher.getRandom().nextFloat(), this.soulcatcher.getRandom().nextFloat(), 0.0D);
  if (livingEntity.position().distanceTo(vec3d4)<2){
   livingEntity.hurt(this.soulcatcher.damageSources().sonicBoom(this.soulcatcher),10);}
     }
        }}
    }

    @Override
    public boolean canContinueToUse() {
        return timer > count;
    }


    @Override
    public boolean canUse() {
        LivingEntity livingEntity=this.soulcatcher.getTarget();
        if (livingEntity==null){
            return false;
        }

        return this.soulcatcher.gettimer()<50&&this.soulcatcher.getbeamtimer()>140;
    }
}
