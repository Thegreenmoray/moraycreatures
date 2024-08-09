package com.moray.moraymobs.ai;

import com.moray.moraymobs.entity.living.monster.Moray;
import com.moray.moraymobs.entity.living.monster.Morayjaw;
import com.moray.moraymobs.registries.Mobregistries;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class Grabjawgoal extends Goal {
   Moray moray;
   int time;
   int jaw;
   Morayjaw morayjaw;
   public Grabjawgoal(Moray moray, int time){
       this.moray=moray;
  this.time=time;
   }

    @Override
    public void start() {
       jaw=-1;

        super.start();
    }

    @Override
    public void stop() {
      jaw=-1;
moray.settimer(0);
        super.stop();
    }

    @Override
    public boolean canContinueToUse() {
        return jaw<time;
    }


   public Morayjaw createjaw(){
       return new Morayjaw(this.moray.level());
   }


    @Override
    public void tick() {
        LivingEntity entity=this.moray.getTarget();

        jaw++;

        if (entity!=null) {

           if(jaw==0){
               morayjaw=createjaw();
                morayjaw.setParent(moray);
              this.moray.level().addFreshEntity(morayjaw);
           morayjaw.setanimation(1);
           moray.setanimation(2);
            }

             if (jaw < 12&&jaw>8&&moray.distanceTo(entity)<=4.5) {
              entity.startRiding(morayjaw);
                 entity.hurt(this.moray.damageSources().generic(),5);
             }
if (jaw==13){
morayjaw.remove(Entity.RemovalReason.DISCARDED);
    entity.stopRiding();
    moray.setanimation(0);
morayjaw.setanimation(0);
}

     }
        super.tick();
    }

    @Override
    public boolean canUse() {
        LivingEntity entity=moray.getTarget();

        if (entity==null){
            return false;
        }

       return this.moray.gettimer()>=50&&moray.distanceTo(entity)<=4.5;
    }
}
