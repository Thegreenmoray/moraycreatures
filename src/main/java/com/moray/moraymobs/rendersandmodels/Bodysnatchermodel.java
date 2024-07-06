package com.moray.moraymobs.rendersandmodels;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.Body_Snatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class Bodysnatchermodel extends GeoModel<Body_Snatcher> {
    @Override
    public void setCustomAnimations(Body_Snatcher animatable, long instanceId, AnimationState<Body_Snatcher> animationState) {
        CoreGeoBone head= getAnimationProcessor().getBone("Head");

        if (head != null){
            EntityModelData entityModelData=animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityModelData.headPitch()* Mth.DEG_TO_RAD);
            head.setRotZ(entityModelData.headPitch()* Mth.DEG_TO_RAD);

        }
    }

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
