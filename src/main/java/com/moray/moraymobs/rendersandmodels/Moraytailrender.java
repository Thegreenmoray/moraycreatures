package com.moray.moraymobs.rendersandmodels;

import com.moray.moraymobs.entity.Moraybody;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Moraytailrender extends GeoEntityRenderer<Moraybody> {
    public Moraytailrender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Moraytailmodel());
    }
}
