package com.moray.moraymobs.modevents;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.living.animal.*;
import com.moray.moraymobs.entity.living.monster.*;
import com.moray.moraymobs.entity.projectiles.Stunwave;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MorayMobs.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class Events {

    @SubscribeEvent
    public static void entitySpawnRestrictions(SpawnPlacementRegisterEvent event) {
        event.register(Mobregistries.MORAY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(Mobregistries.OPOSSUM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(Mobregistries.VOLCANOBACK.get(),SpawnPlacements.Type.ON_GROUND,Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,Volcanoback::checkMonsterSpawnRuleschance, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(Mobregistries.PADDLEFISH.get(),SpawnPlacements.Type.IN_LAVA,Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,LavaPaddleFish::checkPaddlefishSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(Mobregistries.SOULCATCHER.get(),SpawnPlacements.Type.ON_GROUND,Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,Soulcatcher::checkMonsterSpawnruleschance, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(Mobregistries.PRONGHORN.get(),SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,Animal::checkAnimalSpawnRules,SpawnPlacementRegisterEvent.Operation.REPLACE);
    }


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
event.put(Mobregistries.BOWFIN.get(), Enderbowfin.createAttributes().build());
event.put(Mobregistries.PRONGHORN.get(), Pronghorn.createAttributes().build());
event.put(Mobregistries.THRESHER.get(), Thresher_shark.createAttributes().build());
    }
@Mod.EventBusSubscriber(modid = MorayMobs.MODID)
    public static class itemspawn{

        @SubscribeEvent
        public static void bottle( PlayerInteractEvent.EntityInteractSpecific item){
            Entity entity =item.getTarget();
            Player player=item.getEntity();
            ItemStack $$2 = player.getItemInHand(item.getHand());
            if ($$2.is(Items.GLASS_BOTTLE)&&entity instanceof Stunwave stunwave) {
                ItemStack $$3 = ItemUtils.createFilledResult($$2, player, Itemregististeries.BOTTLE_OF_SHOCK.get().getDefaultInstance());
                player.setItemInHand(item.getHand(), $$3);
           stunwave.remove(Entity.RemovalReason.DISCARDED);
            }
        }

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
