package com.moray.moraymobs.rendersandmodels;

import com.moray.moraymobs.entity.Volcanoback;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Volcanobackrender extends GeoEntityRenderer<Volcanoback> {
    public Volcanobackrender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Volcanobackmodel());
    }


    // stops the vanilla death animation, allows animantion to play
    @Override
    protected float getDeathMaxRotation(Volcanoback entityLivingBaseIn) {
        return 0.0F;
    }

}
