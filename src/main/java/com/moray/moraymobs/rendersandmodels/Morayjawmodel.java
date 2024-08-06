package com.moray.moraymobs.rendersandmodels;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.Morayjaw;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Morayjawmodel extends GeoModel<Morayjaw>  {


    @Override
    public ResourceLocation getModelResource(Morayjaw morayjaw) {
        return new ResourceLocation(MorayMobs.MODID,"geo/morayjaw.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Morayjaw morayjaw) {
        return new ResourceLocation(MorayMobs.MODID,"textures/entity/morayjaw.png");


    }

    @Override
    public ResourceLocation getAnimationResource(Morayjaw morayjaw) {
        return new ResourceLocation(MorayMobs.MODID,"animations/eel.bite.json");

    }
}
