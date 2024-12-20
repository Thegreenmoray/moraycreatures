package com.moray.moraymobs.registries;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.block.Basaltlightblock;
import com.moray.moraymobs.block.EndFlowerBlock;
import com.moray.moraymobs.block.EndGrass;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Blockregistrires {

        public static final DeferredRegister<Block> BLOCKS =
                DeferredRegister.create(ForgeRegistries.BLOCKS, MorayMobs.MODID);


    public static final RegistryObject<Block> BASALTLAMP=registerBlock("basaltlamp",
            ()->new Basaltlightblock(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).lightLevel((w)->10).instabreak()));

    public static final RegistryObject<Block> BLOCK_OF_SCALES=registerBlock("blockscale",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> END_CELSOSIA=registerBlock("endercelosia",
            ()->new EndFlowerBlock(Effectregisteries.STUN,5,BlockBehaviour.Properties.copy(Blocks.ALLIUM).noCollission().instabreak()));

    public static final RegistryObject<Block> END_CELSOSIA_POTTED=BLOCKS.register("potted_endercelosia",
            ()->new FlowerPotBlock(()->(FlowerPotBlock)Blocks.FLOWER_POT,Blockregistrires.END_CELSOSIA,BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).instabreak()));

    public static final RegistryObject<Block> END_GRASS=registerBlock("chorousgrass",
            ()->new EndGrass(BlockBehaviour.Properties.copy(Blocks.GRASS).noCollission().instabreak()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return Itemregististeries.ITEM.register(name, () -> new BlockItem(block.get(), new Item.Properties().fireResistant()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
