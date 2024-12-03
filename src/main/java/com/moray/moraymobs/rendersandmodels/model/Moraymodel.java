package com.moray.moraymobs.rendersandmodels.model;


import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.living.monster.Moray;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Moraymodel extends GeoModel<Moray> {


    @Override
    public ResourceLocation getModelResource(Moray moray) {
        return new ResourceLocation(MorayMobs.MODID,"geo/eelhead.geo.json");

    }

    @Override
    public ResourceLocation getTextureResource(Moray moray) {
        return new ResourceLocation(MorayMobs.MODID,"textures/entity/eelbody.png");

    }

    @Override
    public ResourceLocation getAnimationResource(Moray moray) {
        return new ResourceLocation(MorayMobs.MODID,"animations/moray.animation.json");

    }
}
