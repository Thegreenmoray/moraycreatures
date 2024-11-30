package com.moray.moraymobs.rendersandmodels.model;


import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.living.monster.Soulcatcher;
import com.moray.moraymobs.entity.projectiles.Soulpiece;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Soulballmodel extends GeoModel<Soulpiece> {

    @Override
    public ResourceLocation getModelResource(Soulpiece soulpiece) {
        return new ResourceLocation(MorayMobs.MODID,"geo/soulcatcher.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Soulpiece soulpiece) {
        return new ResourceLocation(MorayMobs.MODID,"textures/entity/soulcatcher.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Soulpiece soulpiece) {
        return new ResourceLocation(MorayMobs.MODID,"animations/soulcatcher.animation.json");

    }
}
