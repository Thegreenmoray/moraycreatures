package com.moray.moraymobs.rendersandmodels;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.Moraybody;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Moraytailmodel extends GeoModel<Moraybody> {
    @Override
    public ResourceLocation getModelResource(Moraybody moraybody) {
        return new ResourceLocation(MorayMobs.MODID,"geo/eelbody.geo.json");

    }

    @Override
    public ResourceLocation getTextureResource(Moraybody moraybody) {
        return new ResourceLocation(MorayMobs.MODID,"textures/entity/eelbody.png");

    }

    @Override
    public ResourceLocation getAnimationResource(Moraybody moraybody) {
        return new ResourceLocation(MorayMobs.MODID,"animations/moray.animations.json");

    }
}
