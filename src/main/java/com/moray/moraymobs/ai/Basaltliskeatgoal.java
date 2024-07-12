package com.moray.moraymobs.ai;

import com.moray.moraymobs.entity.Basaltlisk;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.MagmaCube;

public class Basaltliskeatgoal extends Goal {
   Basaltlisk basaltlisk;

   public Basaltliskeatgoal(Basaltlisk basaltlisk){
       this.basaltlisk=basaltlisk;
   }



    public boolean canContinueToUse() {

        LivingEntity entity= this.basaltlisk.getTarget();


        if (entity == null){
            return false;
        }


        return entity.isAlive()&&this.basaltlisk.position().distanceTo(entity.position())<3&&this.basaltlisk.hasLineOfSight(entity);
    }



    @Override
    public void tick() {
       LivingEntity entity= this.basaltlisk.getTarget();

       if (entity!=null){

       if (entity.isAlive()&&entity instanceof MagmaCube magmaCube&&!this.basaltlisk.has_eaten()){
         int size =magmaCube.getSize();
         if (size<1){
     float x_pull= (float) ((magmaCube.getX()-basaltlisk.getX())*1.2);
     float y_pull= (float) ((magmaCube.getY()-basaltlisk.getY())*1.2);
     float z_pull= (float) ((magmaCube.getZ()-basaltlisk.getZ())*1.2);
      magmaCube.setDeltaMovement(x_pull,y_pull,z_pull);
      basaltlisk.set_eaten(true);
      magmaCube.remove(Entity.RemovalReason.KILLED);}
       }


       if (entity.isAlive()&&this.basaltlisk.hasLineOfSight(entity)&&this.basaltlisk.distanceTo(entity)<3){

           entity.hurt(this.basaltlisk.damageSources().generic(),2.0f);  //may want to add a cooldown
       }




       }




        super.tick();
    }

    @Override
    public boolean canUse() {
        LivingEntity livingEntity= this.basaltlisk.getTarget();
        if (livingEntity ==null){
            return false;
        }

        return basaltlisk.position().distanceTo(livingEntity.position())<3&&this.basaltlisk.hasLineOfSight(livingEntity);
    }
}
