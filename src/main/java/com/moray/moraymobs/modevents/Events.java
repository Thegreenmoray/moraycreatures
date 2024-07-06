package com.moray.moraymobs.modevents;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.Body_Snatcher;
import com.moray.moraymobs.entity.Opossum;
import com.moray.moraymobs.registries.Mobregistries;
import com.moray.moraymobs.tags.MorayKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MorayMobs.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class Events {


@SubscribeEvent
public static void entityattrubitonevent(EntityAttributeCreationEvent event){
    event.put(Mobregistries.BODY_SNATCHER.get(), Body_Snatcher.createAttributes().build());
event.put(Mobregistries.OPOSSUM.get(),Opossum.createAttributes().build());
}









    @Mod.EventBusSubscriber(modid = MorayMobs.MODID)
 public static class deathspawn{
     @SubscribeEvent
     public static void onmobdeath(LivingDeathEvent event){
         LivingEntity entity = event.getEntity();


         if (entity.getType().is(MorayKeys.ASSIMILABLE)){

             int point = entity.level().random.nextInt(10);


              if (point==0){
                  Body_Snatcher snatcher= Mobregistries.BODY_SNATCHER.get().create(event.getEntity().level());

                  snatcher.moveTo(entity.position());

                  entity.level().addFreshEntity(snatcher);

             entity.level().playSound((Player)null, BlockPos.containing(entity.position()),  SoundEvents.ZOMBIE_BREAK_WOODEN_DOOR, SoundSource.BLOCKS, 0.7F, 0.9F + entity.level().random.nextFloat() * 0.2F);
              }
         }


 }

}

}
