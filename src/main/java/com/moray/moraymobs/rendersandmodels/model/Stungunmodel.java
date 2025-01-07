package com.moray.moraymobs.rendersandmodels.model;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.item.Stungun;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Stungunmodel extends GeoModel<Stungun> {
    @Override
    public ResourceLocation getModelResource(Stungun stungun) {
        return new ResourceLocation(MorayMobs.MODID,"geo/stungun.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Stungun stungun) {
        return new ResourceLocation(MorayMobs.MODID,"textures/item/stungun.png");

    }

    @Override
    public ResourceLocation getAnimationResource(Stungun stungun) {
        return new ResourceLocation(MorayMobs.MODID,"animations/stungun.animation.json");
    }
}

