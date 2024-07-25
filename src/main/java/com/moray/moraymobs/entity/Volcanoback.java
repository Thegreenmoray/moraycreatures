package com.moray.moraymobs.entity;


import com.moray.moraymobs.ai.VolcanobackMeleeAttackGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Volcanoback extends Monster {

    private static final EntityDataAccessor<Integer> ANIMATION= SynchedEntityData.defineId(Volcanoback.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ANIMATION_ATTACK_TIMER= SynchedEntityData.defineId(Volcanoback.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GROUNDPOUND_TIMER= SynchedEntityData.defineId(Volcanoback.class, EntityDataSerializers.INT);

    public final int START_ANIMATION_TIMER = 30;

    public int getanimation(){
        return this.entityData.get(ANIMATION);
    }

    public void setanimation(int animation){
        this.entityData.set(ANIMATION,animation);
    }

    public int getanimation_timer(){
        return this.entityData.get(ANIMATION_ATTACK_TIMER);
    }

    public void setanimationtimer(int toungetime){
        this.entityData.set(ANIMATION_ATTACK_TIMER,toungetime);
    }

    public int getgroundpound(){
        return this.entityData.get(GROUNDPOUND_TIMER);
    }

    public void setgroundpound(int groundpound){
        this.entityData.set(GROUNDPOUND_TIMER,groundpound);
    }

    public Volcanoback(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    this.setMaxUpStep(1.0f);
   this.xpReward=50;
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 100.0).add(Attributes.MOVEMENT_SPEED, 0.10).add(Attributes.ARMOR,10f);
    }

    protected void registerGoals() {
      this.goalSelector.addGoal(2,new VolcanobackMeleeAttackGoal(this,0.1f,true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));


    }

    private void spawn_projectiles(){

    float radians =this.random.nextInt(360)* Mth.DEG_TO_RAD;

   //projectile declartion
        this.setPos(this.getOnPos().getCenter());
   Vec3 vec3 =new Vec3(2*Mth.sin(radians),10,2*Mth.cos(radians));
  this.setDeltaMovement(vec3.normalize().scale(0.1));
    }


    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    public boolean isInvulnerableTo(DamageSource source) {
        return  source.is(DamageTypeTags.IS_PROJECTILE) || super.isInvulnerableTo(source);
    }


    public boolean hurt(DamageSource damageSource,float amount){

        if (damageSource.is(DamageTypes.EXPLOSION)){
          amount  =  (amount * 0.85f);
          return super.hurt(damageSource,amount);
        }

        return super.hurt(damageSource,amount);
    }


}
