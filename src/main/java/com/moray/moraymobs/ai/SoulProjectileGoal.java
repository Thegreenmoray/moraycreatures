package com.moray.moraymobs.ai;

import com.moray.moraymobs.entity.living.monster.Soulcatcher;
import com.moray.moraymobs.entity.projectiles.Soulpiece;
import com.moray.moraymobs.registries.Mobregistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class SoulProjectileGoal extends Goal {
    Soulcatcher soulcatcher;
int timer;
int count;
    public SoulProjectileGoal(Soulcatcher soulcatcher,int timer){
        this.soulcatcher=soulcatcher;
        this.timer=timer;
    }

    @Override
    public void start() {
        count=0;
    }

    @Override
    public void stop() {
        count=0;
    }

    @Override
    public boolean canContinueToUse() {
        return timer > count;
    }


    @Override
    public void tick() {

       LivingEntity entity=this.soulcatcher.getTarget();
++count;
       if(entity!=null) {


if (count==14){
            Soulpiece soulpiece = new Soulpiece(this.soulcatcher.level());


            double d0 = entity.getX() - this.soulcatcher.getX();
            double d1 = entity.getY(0.3333333333333333) - soulpiece.getY();
            double d2 = entity.getZ() - this.soulcatcher.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            soulpiece.shoot(d0, d1 + d3 * 0.20000000298023224, d2, 1.6F, (float) (14 - this.soulcatcher.level().getDifficulty().getId() * 4));
            this.soulcatcher.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.soulcatcher.getRandom().nextFloat() * 0.4F + 0.8F));
            this.soulcatcher.level().addFreshEntity(soulpiece);}
        }


    }

    @Override
    public boolean canUse() {
        LivingEntity livingEntity=this.soulcatcher.getTarget();
       if (livingEntity==null){
           return false;
       }

        return this.soulcatcher.gettimer()>50&&this.soulcatcher.getbeamtimer()<100;
    }
}
