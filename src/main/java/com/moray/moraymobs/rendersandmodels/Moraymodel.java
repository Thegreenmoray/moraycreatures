package com.moray.moraymobs.rendersandmodels;


import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.Moray;
import com.moray.moraymobs.entity.Moraybody;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Moraymodel extends GeoModel<Moray> {


    @Override
    public ResourceLocation getModelResource(Moray moraybody) {
        return new ResourceLocation(MorayMobs.MODID,"geo/eelhead.geo.json");

    }

    @Override
    public ResourceLocation getTextureResource(Moray moraybody) {
        return new ResourceLocation(MorayMobs.MODID,"textures/entity/head.png");

    }

    @Override
    public ResourceLocation getAnimationResource(Moray moraybody) {
        return new ResourceLocation(MorayMobs.MODID,"animations/eel.idle.json");

    }
}
