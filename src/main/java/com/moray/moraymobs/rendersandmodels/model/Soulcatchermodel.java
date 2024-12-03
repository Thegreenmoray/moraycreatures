package com.moray.moraymobs.rendersandmodels.model;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.living.animal.LavaPaddleFish;
import com.moray.moraymobs.entity.living.monster.Soulcatcher;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Soulcatchermodel extends GeoModel<Soulcatcher> {

    @Override
    public ResourceLocation getModelResource(Soulcatcher soulcatcher) {
        return new ResourceLocation(MorayMobs.MODID,"geo/soulcatcher.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Soulcatcher soulcatcher) {
        return new ResourceLocation(MorayMobs.MODID,"textures/entity/soulcatcher.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Soulcatcher soulcatcher) {
        return new ResourceLocation(MorayMobs.MODID,"animations/soulcatcher.animation.json");

    }

}
