package com.moray.moraymobs.rendersandmodels;

import com.moray.moraymobs.entity.Morayjaw;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Morayjawrender extends GeoEntityRenderer<Morayjaw> {
    public Morayjawrender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Morayjawmodel());
    }







}
