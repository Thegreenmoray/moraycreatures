package com.moray.moraymobs.rendersandmodels;


import com.moray.moraymobs.entity.Moray;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Morayrender extends GeoEntityRenderer<Moray> {
    public Morayrender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Moraymodel());
    }





}
