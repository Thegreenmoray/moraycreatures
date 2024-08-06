package com.moray.moraymobs.rendersandmodels;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.Volcanoback;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Volcanobackmodel extends GeoModel<Volcanoback> {
    @Override
    public ResourceLocation getModelResource(Volcanoback volcanoback) {
        return new ResourceLocation(MorayMobs.MODID,"geo/volcanoback.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Volcanoback volcanoback) {
        return new ResourceLocation(MorayMobs.MODID,"textures/entity/volcanoback.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Volcanoback volcanoback) {
        return new ResourceLocation(MorayMobs.MODID,"animations/volcanoback.animation.json");
    }
}
