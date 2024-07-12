package com.moray.moraymobs.rendersandmodels;

import com.moray.moraymobs.entity.Body_Snatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Bodysnatcherrenderer extends GeoEntityRenderer<Body_Snatcher> {
    public Bodysnatcherrenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Bodysnatchermodel());
    }
}
