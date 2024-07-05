package com.moray.moraymobs.tags;

import com.moray.moraymobs.MorayMobs;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;


public class MorayKeys {


public static final TagKey<EntityType<?>> ASSIMILABLE= registerEntityTag("assimilable");


    private static TagKey<EntityType<?>>  registerEntityTag(String s){
    return TagKey.create(Registries.ENTITY_TYPE,new ResourceLocation(MorayMobs.MODID,s));
    }



}
