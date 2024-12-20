package com.moray.moraymobs.registries;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.effect.Stun;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Effectregisteries {

    public static final DeferredRegister<MobEffect> MOB_EFFECT =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MorayMobs.MODID);

    final public static RegistryObject<MobEffect> STUN=MOB_EFFECT.register("stun",()->
            new Stun(MobEffectCategory.HARMFUL,0xD6D84F));




    public static void register(IEventBus eventBus) {
        MOB_EFFECT.register(eventBus);
    }
}
