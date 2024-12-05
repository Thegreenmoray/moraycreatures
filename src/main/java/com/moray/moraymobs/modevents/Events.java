package com.moray.moraymobs.modevents;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.living.animal.Basaltlisk;
import com.moray.moraymobs.entity.living.animal.LavaPaddleFish;
import com.moray.moraymobs.entity.living.animal.Opossum;
import com.moray.moraymobs.entity.living.monster.*;
import com.moray.moraymobs.item.Beetlearmor;
import com.moray.moraymobs.registries.Itemregististeries;
import com.moray.moraymobs.registries.Mobregistries;
import com.moray.moraymobs.tags.MorayKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MorayMobs.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class Events {


@SubscribeEvent
public static void entityattrubitonevent(EntityAttributeCreationEvent event){
    event.put(Mobregistries.BODY_SNATCHER.get(), Body_Snatcher.createAttributes().build());
event.put(Mobregistries.OPOSSUM.get(), Opossum.createAttributes().build());
    event.put(Mobregistries.BASALTISK.get(), Basaltlisk.createAttributes().build());
event.put(Mobregistries.VOLCANOBACK.get(), Volcanoback.createAttributes().build());
event.put(Mobregistries.MORAY.get(), Moray.createAttributes().build());
event.put(Mobregistries.MORAYJAW.get(), Morayjaw.createAttributes().build());
event.put(Mobregistries.PADDLEFISH.get(), LavaPaddleFish.createAttributes().build());
event.put(Mobregistries.SOULCATCHER.get(), Soulcatcher.createAttributes().build());
}



    @Mod.EventBusSubscriber(modid = MorayMobs.MODID)
 public static class deathspawn{

       @SubscribeEvent
        public static void armorevent(LivingHurtEvent event) {
            if (event.getEntity() instanceof Player player){
                ItemStack boots = player.getInventory().getArmor(0);
                ItemStack leggings = player.getInventory().getArmor(1);
                ItemStack breastplate = player.getInventory().getArmor(2);
                ItemStack helmet = player.getInventory().getArmor(3);
                if (boots.is(Itemregististeries.BEETLE_BOOTS.get())&&
                        leggings.is(Itemregististeries.BEETLE_LEGGINGS.get())&&
                        breastplate.is(Itemregististeries.BEETLE_CHESTPLATE.get())&&
                        helmet.is(Itemregististeries.BEETLE_HELMET.get())&&
                        event.getSource().is(DamageTypeTags.IS_PROJECTILE)){
                    float sourcereduction = (float) (event.getAmount()*0.5);
                    player.hurt(event.getSource(),sourcereduction);
                }

            }
        }




    @SubscribeEvent
     public static void onmobdeath(LivingDeathEvent event){
         LivingEntity entity = event.getEntity();

      boolean biol  = (entity.getType().is(MorayKeys.ASSIMILABLE));
         if ((biol&&!entity.isBaby())||entity instanceof Player){

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
