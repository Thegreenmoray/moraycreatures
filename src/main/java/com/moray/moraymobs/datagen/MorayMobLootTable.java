package com.moray.moraymobs.datagen;

import com.moray.moraymobs.registries.Itemregististeries;
import com.moray.moraymobs.registries.Mobregistries;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Stream;

public class MorayMobLootTable extends EntityLootSubProvider {


   protected MorayMobLootTable() {
       super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        this.add(Mobregistries.OPOSSUM.get(), LootTable.lootTable());
   this.add(Mobregistries.BASALTISK.get(),LootTable.lootTable());
   this.add(Mobregistries.BODY_SNATCHER.get(),LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Itemregististeries.BRAIN.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0,1))))));
  this.add(Mobregistries.VOLCANOBACK.get(),LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Itemregististeries.BEETLE_SCALE.get())).apply(SetItemCountFunction.setCount(UniformGenerator.between(5,11))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0,1))).when(LootItemKilledByPlayerCondition.killedByPlayer())));
   this.add(Mobregistries.MORAY.get(),LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,4))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0,1))).when(LootItemKilledByPlayerCondition.killedByPlayer()))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Itemregististeries.JAW.get())).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.25F, 0.01F))));
    this.add(Mobregistries.PADDLEFISH.get(),LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Itemregististeries.PADDLEFISH_FOOD.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,1))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0,1))))));
  this.add(Mobregistries.SOULCATCHER.get(),LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Itemregististeries.SOULJEWEL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1,1))).when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.1f,0.75f)))));
   }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return Mobregistries.ENTITY_TYPE.getEntries().stream().map(RegistryObject::get);
    }}

