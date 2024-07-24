package com.moray.moraymobs.entity;

import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class Moray extends Monster {
    Moray_part[] parts=new Moray_body[6];


    protected Moray(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

           for (int i=0;i<parts.length;i++){
               this.parts[i]= i==parts.length-1 ? new Moray_tail(this):new Moray_body(this);

           }


    }


    @Override
    public void aiStep() {

       connect_parts();


        super.aiStep();
    }



    private void connect_parts(){


        for(int k = 0; k < this.parts.length; k++) {

           Entity head= k==0 ? this: this.parts[k-1];


           float x_pos= (float) head.getX();
            float y_pos= (float) head.getY();
            float z_pos= (float) head.getZ();

            float head_angle_in_radians=head.getYRot()* Mth.DEG_TO_RAD;

            float x_want= k+Mth.sin(head_angle_in_radians);
            float z_want=k+Mth.cos(head_angle_in_radians);

            this.parts[k].setPos(x_pos+x_want,y_pos,z_pos+z_want);

         float length=Mth.sqrt(x_want*x_want+z_want*z_want);
          float pitch= (float) Mth.atan2(z_want,x_want)*Mth.DEG_TO_RAD;
         float yaw=(float) Mth.atan2(y_pos,length)*Mth.DEG_TO_RAD;

this.parts[k].setRot(pitch,-yaw);

        }



    }



    @Override
    public boolean isMultipartEntity() {
        return true;
    }



    public void recreateFromPacket(ClientboundAddEntityPacket pPacket) {
        super.recreateFromPacket(pPacket);
    }




    public MobType getMobType() {
        return MobType.UNDEAD;
    }




}
