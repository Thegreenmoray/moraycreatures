package com.moray.moraymobs;
import com.moray.moraymobs.registries.Mobregistries;
import com.moray.moraymobs.rendersandmodels.render.*;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class Enitityholder {



    public static void setup(FMLClientSetupEvent event){
        EntityRenderers.register(Mobregistries.BODY_SNATCHER.get(), Bodysnatcherrenderer::new);
        EntityRenderers.register(Mobregistries.OPOSSUM.get(), Opossumrenderer::new);
        EntityRenderers.register(Mobregistries.BASALTISK.get(), Basaltliskrender::new);
        EntityRenderers.register(Mobregistries.VOLCANOBACK.get(), Volcanobackrender::new);
    EntityRenderers.register(Mobregistries.FIREHEAP.get(), LavaRender::new);
   EntityRenderers.register(Mobregistries.MORAY.get(), Morayrender::new);
    EntityRenderers.register(Mobregistries.MORAYJAW.get(), Morayjawrender::new);
    EntityRenderers.register(Mobregistries.PADDLEFISH.get(),Paddlefishrender::new);
    }






}
