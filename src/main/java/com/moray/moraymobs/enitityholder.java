package com.moray.moraymobs;

import com.moray.moraymobs.registries.Mobregistries;
import com.moray.moraymobs.rendersandmodels.Bodysnatcherrenderer;
import com.moray.moraymobs.rendersandmodels.Opossumrenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class enitityholder {



    public static void setup(FMLClientSetupEvent event){
        EntityRenderers.register(Mobregistries.BODY_SNATCHER.get(), Bodysnatcherrenderer::new);
        EntityRenderers.register(Mobregistries.OPOSSUM.get(), Opossumrenderer::new);
    }






}
