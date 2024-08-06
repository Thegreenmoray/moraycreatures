package com.moray.moraymobs.registries;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.*;
import com.moray.moraymobs.entity.projectiles.Fireheap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
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
                  .sized(1,1F).build(new ResourceLocation(MorayMobs.MODID,"bonymoray").toString()));

  final public static RegistryObject<EntityType<Morayjaw>> MORAYJAW=
          ENTITY_TYPE.register("boneymorayjaw",()->EntityType.Builder.<Morayjaw>of(Morayjaw::new, MobCategory.MISC)
                  .sized(0.5f,0.5F).build(new ResourceLocation(MorayMobs.MODID,"bonymorayjaw").toString()));


  final public static RegistryObject<EntityType<Moraybody>> MORAYBODY=
          ENTITY_TYPE.register("boneymoraybody",()->EntityType.Builder.<Moraybody>of(Moraybody::new, MobCategory.MISC)
                  .sized(3,2F).build(new ResourceLocation(MorayMobs.MODID,"bonymoraybody").toString()));

  public static void register(IEventBus bus){
    ENTITY_TYPE.register(bus);
}




    }
