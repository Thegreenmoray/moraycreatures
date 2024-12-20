package com.moray.moraymobs.datagen;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.registries.Blockregistrires;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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
blockWithItem(Blockregistrires.BASALTLAMP);
blockWithItem(Blockregistrires.BLOCK_OF_SCALES);
simpleBlockWithItem(Blockregistrires.END_CELSOSIA.get(), models().cross(blockTexture(Blockregistrires.END_CELSOSIA.get()).getPath(),
                blockTexture(Blockregistrires.END_CELSOSIA.get())).renderType("cutout"));
        simpleBlockWithItem(Blockregistrires.END_CELSOSIA_POTTED.get(), models().singleTexture("potted_endercelosia", new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(Blockregistrires.END_CELSOSIA.get())).renderType("cutout"));
        simpleBlockWithItem(Blockregistrires.END_GRASS.get(), models().cross(blockTexture(Blockregistrires.END_GRASS.get()).getPath(),
                blockTexture(Blockregistrires.END_GRASS.get())).renderType("cutout"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
