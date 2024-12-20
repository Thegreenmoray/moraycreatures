package com.moray.moraymobs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;

public class EndGrass extends BushBlock {


    public EndGrass(Properties pProperties) {
        super(pProperties);
    }

    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return  pState.is(Blocks.END_STONE);}


}
