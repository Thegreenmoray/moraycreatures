package com.moray.moraymobs.entity;


import com.moray.moraymobs.ai.Groundpoundgoal;
import com.moray.moraymobs.ai.VolcanobackMeleeAttackGoal;
import com.moray.moraymobs.entity.projectiles.Fireheap;
import com.moray.moraymobs.registries.Mobregistries;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Volcanoback extends Monster implements GeoEntity {
    private final AnimatableInstanceCache Cache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Integer> ANIMATION= SynchedEntityData.defineId(Volcanoback.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ANIMATION_ATTACK_TIMER= SynchedEntityData.defineId(Volcanoback.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GROUNDPOUND_TIMER= SynchedEntityData.defineId(Volcanoback.class, EntityDataSerializers.INT);

    public final int START_ANIMATION_TIMER = 24;
    public boolean isslashing=false;
    public boolean starttimer=false;
    public boolean issmashing=false;

    public int getanimationable(){
        return this.entityData.get(ANIMATION);
    }

    public void setanimationable(int animation){
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
        this.moveControl =new MoveControl(this);
    this.xpReward=50;
    }

    protected @NotNull PathNavigation createNavigation(Level pLevel) {
        return new GroundPathNavigation(this, pLevel);
    }




    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 100.0).add(Attributes.MOVEMENT_SPEED, 0.5f).add(Attributes.ARMOR,10f).add(Attributes.ATTACK_DAMAGE,12f).add(Attributes.FOLLOW_RANGE,20);
    }

    protected void registerGoals() {
      this.goalSelector.addGoal(2,new VolcanobackMeleeAttackGoal(this,0.5f,false));
    //    this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.5){
       //     @Override
        //    public boolean canUse() {
       //         return super.canUse()&&(this.mob instanceof Volcanoback volcanoback&&volcanoback.getanimationable()==0&&volcanoback.getgroundpound()=<100);
       //     }
       // });
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
this.goalSelector.addGoal(3, new Groundpoundgoal(this,50));
 }

    @Override
    public void aiStep() {

      setgroundpound(getgroundpound()+1);

     int spewchance =this.random.nextInt(20);



     if (getgroundpound()>100){
         isslashing= false;
     starttimer=false;
     }

     if (starttimer){
     if (getanimation_timer()==0&&(isslashing)){
         starttimer=false;
         setanimationtimer(0);
          setanimationable(getanimationable()-1);
     }
         if (this.getanimation_timer() > 0) {
             this.setanimationtimer(this.getanimation_timer() - 1);
         }
     }

     if (isslashing&&getanimationable()==0){
         this.setanimationable(1);
     }


      //  if (spewchance==5){
       //     spawn_projectiles();
      //  }



        super.aiStep();
    }

    private void spawn_projectiles(){

    float radians =this.random.nextInt(360)* Mth.DEG_TO_RAD;

      Fireheap fireheap= new Fireheap(this.level());

      fireheap.setPos(this.getX(),this.getY()+5,this.getZ());
   Vec3 vec3 =new Vec3(this.getX()+(2*Mth.sin(radians)),10,this.getZ()+(2*Mth.cos(radians)));
 fireheap.setDeltaMovement(vec3.normalize().scale(0.1));
   fireheap.setOwner(this);
  level().addFreshEntity(fireheap);

    }


    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    public boolean isInvulnerableTo(DamageSource source) {
        return source.is(DamageTypeTags.IS_PROJECTILE) || super.isInvulnerableTo(source);
    }

    @Override
    public void knockback(double pStrength, double pX, double pZ) {

    }

    @Override
    public boolean isPushable() {
        return false;
    }

    public boolean hurt(DamageSource damageSource, float amount){

        if (damageSource.is(DamageTypes.EXPLOSION)){
          amount  =  (amount * 0.85f);
          return super.hurt(damageSource,amount);
        }

        return super.hurt(damageSource,amount);
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this,
                "Controller",this::animations));}

    private PlayState animations(AnimationState<Volcanoback> volcanobackAnimationState) {

        if(getanimationable()==2&&getanimation_timer()>0){
            volcanobackAnimationState.getController().setAnimation(RawAnimation.begin().then("pound.volcanoback", Animation.LoopType.PLAY_ONCE));
            return PlayState.CONTINUE;
        }

        if (getanimationable()==1&&getanimation_timer()>0){
            volcanobackAnimationState.getController().setAnimation(RawAnimation.begin().then("swipe.volcanoback", Animation.LoopType.PLAY_ONCE));
            return PlayState.CONTINUE;
        }

        if (this.getHealth()<=0){
            setanimationable(0);
            volcanobackAnimationState.getController().setAnimation(RawAnimation.begin().then("volcanoback.death", Animation.LoopType.HOLD_ON_LAST_FRAME));
            return PlayState.CONTINUE;
        }

        if (volcanobackAnimationState.isMoving()){
            volcanobackAnimationState.getController().setAnimation(RawAnimation.begin().then("volcanoback.walk", Animation.LoopType.LOOP)); ///volcanoback.walk
            return PlayState.CONTINUE;
        }

        if (!volcanobackAnimationState.isMoving()){
            volcanobackAnimationState.getController().setAnimation(RawAnimation.begin().then("volcanoback.idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return Cache;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ANIMATION, 0);
        this.entityData.define(ANIMATION_ATTACK_TIMER,0);
        this.entityData.define(GROUNDPOUND_TIMER,0);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("animation", this.getanimationable());
        pCompound.putInt("animationtimer",this.getanimation_timer());
       pCompound.putInt("groundpound",this.getgroundpound());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setanimationable(pCompound.getInt("animation"));
        this.setanimationtimer(pCompound.getInt("animationtimer"));
         this.setgroundpound(pCompound.getInt("groundpound"));
    }

}
