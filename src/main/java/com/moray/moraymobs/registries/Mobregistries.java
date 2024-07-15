package com.moray.moraymobs.registries;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.Basaltlisk;
import com.moray.moraymobs.entity.Body_Snatcher;
import com.moray.moraymobs.entity.Opossum;
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
                    .sized(0.5F,2).fireImmune().build(new ResourceLocation(MorayMobs.MODID,"bodysnatcher").toString()));

    final public static RegistryObject<EntityType<Opossum>> OPOSSUM=
            ENTITY_TYPE.register("opossum",()->EntityType.Builder.of(Opossum::new, MobCategory.CREATURE)
                    .sized(1,0.5F).fireImmune().build(new ResourceLocation(MorayMobs.MODID,"opossum").toString()));

    final public static RegistryObject<EntityType<Basaltlisk>> BASALTISK=
            ENTITY_TYPE.register("basaltlisk",()->EntityType.Builder.of(Basaltlisk::new, MobCategory.CREATURE)
                    .sized(2,0.5F).fireImmune().build(new ResourceLocation(MorayMobs.MODID,"basaltlisk").toString()));

public static void register(IEventBus bus){
    ENTITY_TYPE.register(bus);
}
}
