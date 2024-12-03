package com.moray.moraymobs.datagen;

import com.moray.moraymobs.registries.Blockregistrires;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import java.util.Set;

public class MorayBlockLootTables  extends BlockLootSubProvider {
    public MorayBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
    this.dropSelf(Blockregistrires.BASALTLAMP.get());
   this.dropSelf(Blockregistrires.BLOCK_OF_SCALES.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Blockregistrires.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }}
