package com.moray.moraymobs.ai;

import com.moray.moraymobs.entity.Volcanoback;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;

import java.util.List;

public class VolcanobackMeleeAttackGoal extends MeleeAttackGoal {

    final double speedModifier;
  final boolean followingTargetEvenIfNotSeen;
    Volcanoback volcanoback;
    boolean isinattack=false;
    private Path path;
    private double pathedTargetX;
    private double pathedTargetY;
    private double pathedTargetZ;
    private int ticksUntilNextPathRecalculation;
    private int ticksUntilNextAttack;
    int animantion_time=30;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;

   //largely copied from melee attack but with some changes
    public VolcanobackMeleeAttackGoal(Volcanoback volcanoback, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(volcanoback, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.speedModifier = pSpeedModifier;
        this.followingTargetEvenIfNotSeen = pFollowingTargetEvenIfNotSeen;
        this.volcanoback=volcanoback;
    }


    @Override
    public void tick() {
        LivingEntity livingentity = this.volcanoback.getTarget();
        if (livingentity != null) {
            this.volcanoback.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
            double d0 = this.volcanoback.getPerceivedTargetDistanceSquareForMeleeAttack(livingentity);
            this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);

        if (!isinattack){
            this.mob.getNavigation().moveTo(this.path, this.speedModifier);
            if ((this.followingTargetEvenIfNotSeen || this.volcanoback.getSensing().hasLineOfSight(livingentity)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0 && this.pathedTargetY == 0.0 && this.pathedTargetZ == 0.0 || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0 || this.volcanoback.getRandom().nextFloat() < 0.05F)) {
                this.pathedTargetX = livingentity.getX();
                this.pathedTargetY = livingentity.getY();
                this.pathedTargetZ = livingentity.getZ();
                this.ticksUntilNextPathRecalculation = 4 + this.volcanoback.getRandom().nextInt(7);
                if (this.canPenalize) {
                    this.ticksUntilNextPathRecalculation += this.failedPathFindingPenalty;
                    if (this.volcanoback.getNavigation().getPath() != null) {
                        Node finalPathPoint = this.volcanoback.getNavigation().getPath().getEndNode();
                        if (finalPathPoint != null && livingentity.distanceToSqr((double)finalPathPoint.x, (double)finalPathPoint.y, (double)finalPathPoint.z) < 1.0) {
                            this.failedPathFindingPenalty = 0;
                        } else {
                            this.failedPathFindingPenalty += 10;
                        }
                    } else {
                        this.failedPathFindingPenalty += 10;
                    }
                }

                if (d0 > 1024.0) {
                    this.ticksUntilNextPathRecalculation += 10;
                } else if (d0 > 256.0) {
                    this.ticksUntilNextPathRecalculation += 5;
                }

                if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier)) {
                    this.ticksUntilNextPathRecalculation += 15;
                }

                this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation);
            }
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);  //this should be changed
           animantion_time--;


            this.checkAndPerformAttack(livingentity, d0);
    }


        if (isinattack){
           animantion_time--;
            checkAndPerformAttack(livingentity,d0);
        }
        }

    }



    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        double d0 = this.getAttackReachSqr(pEnemy);
        if (pDistToEnemySqr <= d0 && this.ticksUntilNextAttack <= 0) {
            this.volcanoback.getNavigation().stop();


          // if(this.volcanoback.getanimationtimer()<=0) {
        //       this.volcanoback.setAttackAnimTimer(this.volcanoback.START_ANIMATION_TIMER);
               animantion_time = this.volcanoback.START_ANIMATION_TIMER;
               isinattack = true;

               //play animation
               //hold
               //begin to drop after a certain point
        //   }

        if (animantion_time<=10){
      //      this.volcanoback.setAnimation(1);
        }


        }
            if(isinattack&&animantion_time<=0){

            this.resetAttackCooldown();
                List<Entity> damage=this.volcanoback.level().getEntities(this.volcanoback,this.volcanoback.getBoundingBox().inflate(2),e-> this.volcanoback.position().normalize().dot(e.position().normalize())>=0.0&&e instanceof LivingEntity);

           for (Entity entity:damage){
            this.mob.doHurtTarget(entity);}  //rough idea
            isinattack=false;
        }


    }



}

