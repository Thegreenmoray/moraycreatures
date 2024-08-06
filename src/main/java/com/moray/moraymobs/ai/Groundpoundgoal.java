package com.moray.moraymobs.ai;

import com.moray.moraymobs.entity.Volcanoback;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.List;

public class Groundpoundgoal extends Goal {
   Volcanoback volcanoback;
  int groundpoundtime;
  int amounttime;

   public Groundpoundgoal(Volcanoback volcanoback,int groundpoundtime){
       this.volcanoback=volcanoback;
  this.groundpoundtime=groundpoundtime;
   }


    @Override
    public void tick() {
        --groundpoundtime;


        if (groundpoundtime<=30){

        }
        if (groundpoundtime==10){

            for(int i = 0; i < 20; ++i) {
                    this.volcanoback.level().addParticle(ParticleTypes.ASH, this.volcanoback.getRandomX(3), this.volcanoback.getRandomY(), this.volcanoback.getRandomZ(3), 0.0, 0.0, 0.0);
                }


          List<Entity> entities=this.volcanoback.level().getEntities(this.volcanoback,this.volcanoback.getBoundingBox().inflate(4), Entity::onGround);
        for (Entity entity:entities){
            if(entity instanceof LivingEntity){
                entity.hurt(this.volcanoback.damageSources().generic(),10f);
            }
        }
        }


    }

    @Override
    public void start() {
       amounttime=0;

   }

    @Override
    public boolean canUse() {

       if (volcanoback.getTarget()==null){
           return false;
       }



        return this.volcanoback.distanceTo(this.volcanoback.getTarget())<3&&volcanoback.getgroundpound()>100;
    }

    @Override
    public boolean canContinueToUse() {
        return groundpoundtime > amounttime;
    }

    @Override
    public void stop() {
      amounttime=0;
volcanoback.setgroundpound(0);
   }
}
