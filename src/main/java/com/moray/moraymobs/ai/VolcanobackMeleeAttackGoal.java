package com.moray.moraymobs.ai;

import com.moray.moraymobs.entity.Volcanoback;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;

import java.util.EnumSet;
import java.util.List;

public class VolcanobackMeleeAttackGoal extends Goal {

    final double speedModifier;
  final boolean followingTargetEvenIfNotSeen;
    Volcanoback volcanoback;
    boolean isinattack=false;
    int animantion_time=0;
    private Path path;
    private double pathedTargetX;
    private double pathedTargetY;
    private double pathedTargetZ;
    private int ticksUntilNextPathRecalculation;
    private int ticksUntilNextAttack;
    private long lastCanUseCheck;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;


   //largely copied from melee attack but with some changes
    public VolcanobackMeleeAttackGoal(Volcanoback volcanoback, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        this.speedModifier = pSpeedModifier;
        this.followingTargetEvenIfNotSeen = pFollowingTargetEvenIfNotSeen;
        this.volcanoback=volcanoback;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }


    @Override
    public void tick() {
        LivingEntity livingentity = this.volcanoback.getTarget();
        if (livingentity != null) {
            this.volcanoback.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
            double d0 = this.volcanoback.getPerceivedTargetDistanceSquareForMeleeAttack(livingentity);
            this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
            animantion_time--;

          //  this.volcanoback.getNavigation().moveTo(this.path, this.speedModifier);
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

                if (!this.volcanoback.getNavigation().moveTo(livingentity, this.speedModifier)) {
                    this.ticksUntilNextPathRecalculation += 15;
                }

                this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation);
            }
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);  //this should be changed



            this.checkAndPerformAttack(livingentity, d0);
    }




    }



    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        double d0 = this.getAttackReachSqr(pEnemy);
        if (pDistToEnemySqr <= d0 && this.ticksUntilNextAttack <= 0) {
            this.resetCooldown(20);
            if (this.animantion_time < 0) {
                this.volcanoback.setanimationtimer(this.volcanoback.START_ANIMATION_TIMER);
                animantion_time = this.volcanoback.getanimation_timer();
                this.volcanoback.starttimer = true;
                isinattack = true;
                return;
            }}

          if (animantion_time==10){this.volcanoback.isslashing = true;}

            if (isinattack && animantion_time==0) {
                this.volcanoback.getNavigation().stop();
                List<Entity> damage = this.volcanoback.level().getEntities(this.volcanoback, this.volcanoback.getBoundingBox().inflate(2), e ->  e instanceof LivingEntity);
                for (Entity entity : damage) {
                   if (this.volcanoback.position().normalize().dot(entity.position().normalize()) >= 0.0){
                    this.volcanoback.doHurtTarget(entity);}
                }
                isinattack = false;
                animantion_time = -1;
            }

    }

    protected void resetCooldown(int ticks) {
        this.ticksUntilNextAttack= this.adjustedTickDelay(ticks);
    }


    public boolean canUse() {
        long i = this.volcanoback.level().getGameTime();

        if (this.volcanoback.getgroundpound()>100){
            return false;
        }
        if (i - this.lastCanUseCheck < 20L) {
            this.lastCanUseCheck -= 20;
            return false;
        } else {
            this.lastCanUseCheck = i;
            LivingEntity livingentity = this.volcanoback.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else {
                this.path = this.volcanoback.getNavigation().createPath(livingentity, 0);
                if (this.path != null&&this.volcanoback.isslashing) {
                    return true;
                }
                else {
                   return this.getAttackReachSqr(livingentity) >= this.volcanoback.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                }
            }
        }
    }

    public boolean canContinueToUse() {
        LivingEntity livingentity = this.volcanoback.getTarget();

       if (this.volcanoback.getgroundpound()>100){
           return false;
       }

        if (livingentity == null) {
            return false;
        } else if (!livingentity.isAlive()) {
            return false;
        } else if (!this.followingTargetEvenIfNotSeen) {
            return !this.volcanoback.getNavigation().isDone();
        } else if (!this.volcanoback.isWithinRestriction(livingentity.blockPosition())) {
            return false;
        } else {
            return !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player)livingentity).isCreative();
        }
    }

    public void start() {

       this.volcanoback.getNavigation().moveTo(this.path, this.speedModifier);
        this.volcanoback.setAggressive(true);
        this.ticksUntilNextPathRecalculation = 0;
        this.ticksUntilNextAttack = 0;
    }

    public void stop() {
        LivingEntity livingentity = this.volcanoback.getTarget();
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
            this.volcanoback.setTarget((LivingEntity)null);
        }
        this.volcanoback.setAggressive(false);
        this.volcanoback.getNavigation().stop();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    protected double getAttackReachSqr(LivingEntity pAttackTarget) {
        return (double)(this.volcanoback.getBbWidth() * this.volcanoback.getBbWidth() + pAttackTarget.getBbWidth());
    }

}

