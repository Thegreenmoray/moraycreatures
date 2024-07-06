package com.moray.moraymobs.rendersandmodels;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.entity.Opossum;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class Opossummodel extends GeoModel<Opossum> {
    @Override
    public ResourceLocation getModelResource(Opossum opossum) {
        return new ResourceLocation(MorayMobs.MODID,"geo/opposum.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Opossum opossum) {
        return new ResourceLocation(MorayMobs.MODID,"textures/entity/opossum.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Opossum opossum) {
        return new ResourceLocation(MorayMobs.MODID,"animations/opposum.animation.json");
    }

    public void setCustomAnimations(Opossum animatable, long instanceId, AnimationState<Opossum> animationState) {

        CoreGeoBone head= getAnimationProcessor().getBone("head");

   if (head != null){
       EntityModelData entityModelData=animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        head.setRotX(entityModelData.headPitch()* Mth.DEG_TO_RAD);
        head.setRotZ(entityModelData.headPitch()* Mth.DEG_TO_RAD);

   }

    }



}
