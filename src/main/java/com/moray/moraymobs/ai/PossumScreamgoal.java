package com.moray.moraymobs.ai;

import com.moray.moraymobs.entity.Opossum;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class PossumScreamgoal extends Goal {
int screaming;
    Opossum opossum;
int maxtime;
    public PossumScreamgoal(Opossum possum,int scream_time) {
  this.opossum=possum;
      this.screaming=scream_time;
      this.maxtime=scream_time;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }

    public boolean canContinueToUse() {

        LivingEntity entity =opossum.getTarget();



        if (entity==null||this.opossum.isfainted()){
            return false;
        }

        if (entity instanceof Player player&&player.getMainHandItem().is(Items.ROTTEN_FLESH)){
            return false;
        }



        return screaming>0;
    }


    @Override
    public void start(){
     this.screaming=this.maxtime;
    this.opossum.setScream(true);
    }
    @Override
    public void stop() {
       if (screaming<=0){
          opossum.setScream(false);
           opossum.setFainted(true);
       }

        opossum.setScream(false);
       this.opossum.setTarget(null);
    }

    @Override
    public void tick() {
screaming--;
        opossum.setDeltaMovement(Vec3.ZERO);


if (screaming%10==0){
        this.opossum.playSound(SoundEvents.CAT_HISS,0.2f,3f);}



      if (this.opossum.getTarget() != null){
        double $$3 = this.opossum.getTarget().getX() - this.opossum.getX();
        double $$4 = this.opossum.getTarget().getZ() - this.opossum.getZ();
        this.opossum.setYRot(-((float)Mth.atan2($$3, $$4)) * Mth.RAD_TO_DEG);
        this.opossum.yBodyRot = this.opossum.getYRot();}



        super.tick();
    }



    @Override
    public boolean canUse() {
        LivingEntity entity =opossum.getTarget();

        if (entity ==null){
            return false;
        }

if (this.opossum.isfainted()){
    return false;
}

    return opossum.position().distanceTo(this.opossum.getTarget().position())<5&&!(entity instanceof Player player&&player.getMainHandItem().is(Items.ROTTEN_FLESH));
    }
}
