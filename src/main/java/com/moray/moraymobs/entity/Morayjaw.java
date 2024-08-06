package com.moray.moraymobs.entity;


import com.moray.moraymobs.registries.Mobregistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Morayjaw extends Monster implements GeoEntity {

    private final AnimatableInstanceCache Cache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Integer> ANIMATION= SynchedEntityData.defineId(Morayjaw.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Optional<UUID>> PARENT_UUID = SynchedEntityData.defineId(Morayjaw.class, EntityDataSerializers.OPTIONAL_UUID);


    @Nullable
    public UUID getParentId() {
        return this.entityData.get(PARENT_UUID).orElse(null);
    }

    public void setParentId(@Nullable UUID uniqueId) {
        this.entityData.set(PARENT_UUID, Optional.ofNullable(uniqueId));
    }
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.getParentId() != null) {
            compound.putUUID("ParentUUID", this.getParentId());
        }

    }


    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.hasUUID("ParentUUID")) {
            this.setParentId(compound.getUUID("ParentUUID"));
        }

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PARENT_UUID, Optional.empty());}

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 25.0D).add(Attributes.MOVEMENT_SPEED, 0.15F);
    }

    public Morayjaw(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public Morayjaw( Level pLevel,LivingEntity parent) {
        super(Mobregistries.MORAYJAW.get(), pLevel);
        this.setParent(parent);
    }
    @Override
    public boolean isPushable() {
        return false;
    }
    public void pushEntities() {
        List<Entity> entities = this.level().getEntities(this, this.getBoundingBox().expandTowards(0.2D, 0.0D, 0.2D));
        Entity parent = this.getParent();
        if (parent != null) {
            entities.stream().filter(entity -> entity != parent && !(entity instanceof Morayjaw) && entity.isPushable()).forEach(entity -> entity.push(parent));
        }
    }

    @Override
    public void tick() {

        Entity leader = this.getParent();
        if (leader!=null) {
            float angle_in_radians = leader.getYRot() * Mth.DEG_TO_RAD;
            setNoGravity(true);
            float xspin = -Mth.sin(angle_in_radians);
            float zspin = Mth.cos(angle_in_radians);

            float x_float = (float) (leader.getX() + -0.01*xspin);
            float z_float = (float) (leader.getZ() + -0.01*zspin);

            this.setPos(x_float, leader.getY(), z_float);
            this.position().add(-0.01*xspin,0,-0.01*zspin);
            this.setXRot(leader.getXRot());
            this.setYRot(leader.yRotO);
            this.yHeadRot = this.getYRot();
            this.yBodyRot = this.yRotO;
        }

if (shouldNotExist()){
    remove(RemovalReason.KILLED);
}


        this.pushEntities();
        super.tick();
    }



    public boolean hurt(DamageSource source, float damage) {
        final Entity parent = getParent();

        return parent != null && parent.hurt(source, (float) (damage*.5));
    }


    public boolean shouldNotExist() {
        Entity parent = getParent();

        if (parent==null){return false;}
        return !parent.isAlive();
    }



    public Entity getParent() {
        UUID id = getParentId();
        if (id != null && !this.level().isClientSide) {
            return ((ServerLevel) level()).getEntity(id);
        }
        return null;
    }

    public void setParent(Entity entity) {
        this.setParentId(entity.getUUID());
    }


    @Override
    public void setRot(float yaw, float pitch) {
        super.setRot(yaw, pitch);
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this,
                "Controller",this::animations));
    }

    private PlayState animations(AnimationState<Morayjaw> morayjawAnimationState) {



        return PlayState.CONTINUE;}

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return Cache;
    }
}
