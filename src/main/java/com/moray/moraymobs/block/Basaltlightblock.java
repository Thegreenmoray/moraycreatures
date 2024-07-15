package com.moray.moraymobs.block;

import com.moray.moraymobs.tags.MorayKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;


public class Basaltlightblock extends Block {
    public Basaltlightblock(Properties pProperties) {
        super(pProperties);
    }
@SuppressWarnings("")
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

    if (!pLevel.isClientSide()) {


            if ( pPlayer.blockPosition().closerThan(pPos, 3)) {
                List<Entity> light = pPlayer.level().getEntities(pPlayer, pPlayer.getBoundingBox().inflate(6), e -> e.getType().is(MorayKeys.IS_SPOTTABLE));

           for (Entity entity:light){
              LivingEntity entity1 =(LivingEntity)entity;
           entity1.addEffect(new MobEffectInstance(MobEffects.GLOWING,1222,0));
           }
                return InteractionResult.PASS;
            }
        }


    return InteractionResult.FAIL;
}}
//will work on later, light/brightness is given in the builder