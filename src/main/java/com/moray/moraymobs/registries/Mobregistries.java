package com.moray.moraymobs.registries;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.living.animal.Basaltlisk;
import com.moray.moraymobs.entity.living.animal.Opossum;
import com.moray.moraymobs.entity.living.monster.Body_Snatcher;
import com.moray.moraymobs.entity.living.monster.Moray;
import com.moray.moraymobs.entity.living.monster.Morayjaw;
import com.moray.moraymobs.entity.living.monster.Volcanoback;
import com.moray.moraymobs.entity.projectiles.Fireheap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Mobregistries {
  final public static DeferredRegister<EntityType<?>> ENTITY_TYPE=DeferredRegister.create(
          ForgeRegistries.ENTITY_TYPES, MorayMobs.MODID);



    final public static RegistryObject<EntityType<Body_Snatcher>> BODY_SNATCHER=
            ENTITY_TYPE.register("bodysnatcher",()->EntityType.Builder.of(Body_Snatcher::new, MobCategory.MONSTER)
                    .sized(0.5F,2).build(new ResourceLocation(MorayMobs.MODID,"bodysnatcher").toString()));

    final public static RegistryObject<EntityType<Opossum>> OPOSSUM=
            ENTITY_TYPE.register("opossum",()->EntityType.Builder.of(Opossum::new, MobCategory.CREATURE)
                    .sized(1,0.5F).build(new ResourceLocation(MorayMobs.MODID,"opossum").toString()));

    final public static RegistryObject<EntityType<Basaltlisk>> BASALTISK=
            ENTITY_TYPE.register("basaltlisk",()->EntityType.Builder.of(Basaltlisk::new, MobCategory.CREATURE)
                    .sized(2,0.5F).fireImmune().build(new ResourceLocation(MorayMobs.MODID,"basaltlisk").toString()));

    final public static RegistryObject<EntityType<Volcanoback>> VOLCANOBACK=
            ENTITY_TYPE.register("volcanoback",()->EntityType.Builder.of(Volcanoback::new, MobCategory.CREATURE)
                    .sized(4,2F).fireImmune().build(new ResourceLocation(MorayMobs.MODID,"volcanoback").toString()));

  final public static RegistryObject<EntityType<Fireheap>> FIREHEAP=
          ENTITY_TYPE.register("fireheap",()->EntityType.Builder.<Fireheap>of(Fireheap::new, MobCategory.MISC)
                  .sized(2,0.5F).fireImmune().build(new ResourceLocation(MorayMobs.MODID,"fireheap").toString()));

  final public static RegistryObject<EntityType<Moray>> MORAY=
          ENTITY_TYPE.register("boneymoray",()->EntityType.Builder.of(Moray::new, MobCategory.CREATURE)
                  .sized(3,1F).build(new ResourceLocation(MorayMobs.MODID,"bonymoray").toString()));

  final public static RegistryObject<EntityType<Morayjaw>> MORAYJAW=
          ENTITY_TYPE.register("boneymorayjaw",()->EntityType.Builder.<Morayjaw>of(Morayjaw::new, MobCategory.MISC)
                  .sized(0.5f,0.5F).build(new ResourceLocation(MorayMobs.MODID,"bonymorayjaw").toString()));



  public static void register(IEventBus bus){
    ENTITY_TYPE.register(bus);
}

  @SubscribeEvent
  public static void initializeAttributes(EntityAttributeCreationEvent event) {

    SpawnPlacements.register(MORAY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
    SpawnPlacements.register(OPOSSUM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);

  }
}
