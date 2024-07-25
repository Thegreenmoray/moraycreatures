package com.moray.moraymobs.ai;

import com.moray.moraymobs.entity.Volcanoback;
import net.minecraft.world.entity.ai.goal.Goal;

public class Groundpoundgoal extends Goal {
   Volcanoback volcanoback;
  int groundpoundtime;
  int amounttime;

   public Groundpoundgoal(Volcanoback volcanoback,int groundpoundtime){
       this.volcanoback=volcanoback;
  this.groundpoundtime=groundpoundtime;
   }

    @Override
    public void start() {
       amounttime=100;
    this.volcanoback.setanimation(2);
   }

    @Override
    public boolean canUse() {
        return false;
    }

    @Override
    public void stop() {
      amounttime=100;
      this.volcanoback.setanimation(0);
    }
}
