package com.moray.moraymobs.modevents;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.tags.MorayKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class Events {

    @Mod.EventBusSubscriber(modid = MorayMobs.MODID)
 public static class deathspawn{
     @SubscribeEvent
     public static void onmobdeath(LivingDeathEvent event){
         LivingEntity entity = event.getEntity();


      boolean get= entity.getType().is(MorayKeys.ASSIMILABLE);

         if (get){

             int point = entity.level().random.nextInt(10);


            //  if (point==0){
             entity.level().playSound((Player)null, BlockPos.containing(entity.position()),  SoundEvents.ZOMBIE_BREAK_WOODEN_DOOR, SoundSource.BLOCKS, 0.7F, 0.9F + entity.level().random.nextFloat() * 0.2F);

             // }
         }


 }

}

}
