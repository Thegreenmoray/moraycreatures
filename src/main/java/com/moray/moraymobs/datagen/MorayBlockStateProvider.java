package com.moray.moraymobs.datagen;

import com.moray.moraymobs.MorayMobs;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class MorayBlockStateProvider extends BlockStateProvider {
    public MorayBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MorayMobs.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}