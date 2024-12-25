package com.moray.moraymobs.datagen;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.block.Shulkerberrycrop;
import com.moray.moraymobs.registries.Blockregistrires;
import com.moray.moraymobs.registries.Itemregististeries;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.registries.RegistryObject;
import java.util.Set;
import java.util.function.Function;

public class MorayBlockLootTables  extends BlockLootSubProvider {
    public MorayBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
    this.dropSelf(Blockregistrires.BASALTLAMP.get());
   this.dropSelf(Blockregistrires.BLOCK_OF_SCALES.get());
   this.dropSelf(Blockregistrires.END_CELSOSIA.get());
   this.dropSelf(Blockregistrires.PADDED_MOSS.get());
   this.add(Blockregistrires.END_CELSOSIA_POTTED.get(),createPotFlowerItemTable(Blockregistrires.END_CELSOSIA.get()));
   this.add(Blockregistrires.END_GRASS.get(),this.createendGrassDrops(Blockregistrires.END_GRASS.get()));

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(Blockregistrires.SHULKERFRUIT_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(Shulkerberrycrop.AGE, 5));

        this.add(Blockregistrires.SHULKERFRUIT_CROP.get(), createCropDrops(Blockregistrires.SHULKERFRUIT_CROP.get(), Itemregististeries.SHULKERBERRY.get(),
                Itemregististeries.END_SEED.get() , lootitemcondition$builder));

    }
    protected LootTable.Builder createendGrassDrops(Block pBlock) {
        return createShearsDispatchTable(pBlock, this.applyExplosionDecay(pBlock, ((LootPoolSingletonContainer.Builder<?>) LootItem.lootTableItem(Itemregististeries.END_SEED.get()).when(LootItemRandomChanceCondition.randomChance(0.125F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))));
    }




    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Blockregistrires.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }}
