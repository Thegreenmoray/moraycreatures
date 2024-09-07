package com.moray.moraymobs.ai;

import com.moray.moraymobs.entity.living.monster.Robinhood;
import net.minecraft.world.entity.ai.goal.Goal;

public class Robinhoodstalking extends Goal {

    Robinhood robinhood;

Robinhoodstalking(Robinhood robinhood){
    this.robinhood=robinhood;
}

    @Override
    public boolean canContinueToUse() {


      //is stalking
        return super.canContinueToUse();
    }

    @Override
    public boolean canUse() {
        return false;
    }
}
