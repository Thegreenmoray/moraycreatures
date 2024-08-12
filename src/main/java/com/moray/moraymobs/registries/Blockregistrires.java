package com.moray.moraymobs.registries;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.block.Basaltlightblock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
