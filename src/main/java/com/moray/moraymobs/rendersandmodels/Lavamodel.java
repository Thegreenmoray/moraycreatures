package com.moray.moraymobs.rendersandmodels;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.projectiles.Fireheap;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Lavamodel extends GeoModel<Fireheap> {

    @Override
    public ResourceLocation getModelResource(Fireheap fireheap) {
        return new ResourceLocation(MorayMobs.MODID,"geo/lavaheap.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Fireheap fireheap) {
        return new ResourceLocation(MorayMobs.MODID,"textures/entity/firetexture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Fireheap fireheap) {
        return null;
    }
}
