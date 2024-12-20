package com.moray.moraymobs.datagen;

import com.moray.moraymobs.registries.Blockregistrires;
import com.moray.moraymobs.registries.Itemregististeries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
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
   this.dropSelf(Blockregistrires.END_CELSOSIA.get());
   this.add(Blockregistrires.END_CELSOSIA_POTTED.get(),createPotFlowerItemTable(Blockregistrires.END_CELSOSIA.get()));

   this.add(Blockregistrires.END_GRASS.get(),this.createendGrassDrops(Blockregistrires.END_GRASS.get()));
    }
    protected LootTable.Builder createendGrassDrops(Block pBlock) {
        return createShearsDispatchTable(pBlock, this.applyExplosionDecay(pBlock, ((LootPoolSingletonContainer.Builder<?>) LootItem.lootTableItem(Itemregististeries.END_SEED.get()).when(LootItemRandomChanceCondition.randomChance(0.125F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Blockregistrires.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }}
