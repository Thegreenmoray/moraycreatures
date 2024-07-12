package com.moray.moraymobs.rendersandmodels;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.Body_Snatcher;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;


public class Bodysnatchermodel extends GeoModel<Body_Snatcher> {


    @Override
    public ResourceLocation getModelResource(Body_Snatcher bodySnatcher) {
        return new ResourceLocation(MorayMobs.MODID,"geo/bodysnatcher.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Body_Snatcher bodySnatcher) {
        return new ResourceLocation(MorayMobs.MODID,"textures/entity/bodysnatcher.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Body_Snatcher bodySnatcher) {
        return new ResourceLocation(MorayMobs.MODID,"animations/bodysnatcher.animation.json");
    }
}
