package com.moray.moraymobs.ai;

import com.moray.moraymobs.entity.living.monster.Moray;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class Eelmeeleeattack extends MeleeAttackGoal {
    public Eelmeeleeattack(Moray moray) {
        super(moray,0.7, true);

    }
}
