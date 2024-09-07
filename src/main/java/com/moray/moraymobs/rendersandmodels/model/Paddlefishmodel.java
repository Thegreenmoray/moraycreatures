package com.moray.moraymobs.rendersandmodels.model;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.living.animal.LavaPaddleFish;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Paddlefishmodel extends GeoModel<LavaPaddleFish> {
    @Override
    public ResourceLocation getModelResource(LavaPaddleFish lavaPaddleFish) {
        return new ResourceLocation(MorayMobs.MODID,"geo/lavapaddlefish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LavaPaddleFish lavaPaddleFish) {
        return new ResourceLocation(MorayMobs.MODID,"textures/entity/paddlefish1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LavaPaddleFish lavaPaddleFish) {
        return new ResourceLocation(MorayMobs.MODID,"animations/lavapaddlefish.animation.json");

    }
}
