package com.moray.moraymobs.datagen;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.registries.Blockregistrires;
import com.moray.moraymobs.registries.Itemregististeries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class MorayItemModelProvider extends ItemModelProvider {
    public MorayItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MorayMobs.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
withExistingParent(Itemregististeries.OPPOSUM_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
withExistingParent(Itemregististeries.BODYSNATCHER_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
withExistingParent(Itemregististeries.BASALTlISK_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
withExistingParent(Itemregististeries.VOLCANOBACK_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
withExistingParent(Itemregististeries.MORAY_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
withExistingParent(Itemregististeries.PADDLE_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
withExistingParent(Itemregististeries.SOULCATCHER_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
    simpleItem(Itemregististeries.BRAIN);
     simpleItem(Itemregististeries.JAW);
     simpleItem(Itemregististeries.BEETLE_SCALE);
     simpleItem(Itemregististeries.BEETLE_HELMET);
        simpleItem(Itemregististeries.BEETLE_CHESTPLATE);
        simpleItem(Itemregististeries.BEETLE_LEGGINGS);
        simpleItem(Itemregististeries.BEETLE_BOOTS);
        simpleItem(Itemregististeries.BASALT_CRYSTAL);
   simpleItem(Itemregististeries.BUCKETED_PADDLEFISH);
   simpleItem(Itemregististeries.PADDLEFISH_FOOD);
    simpleItem(Itemregististeries.SOULJEWEL);
    simpleItem(Itemregististeries.SOULBEADRING);
simpleItemblock(Blockregistrires.END_CELSOSIA);
simpleItemblock(Blockregistrires.END_GRASS);
simpleItem(Itemregististeries.END_SEED);
simpleItem(Itemregististeries.SHULKERBERRY);
    }



    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MorayMobs.MODID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemblock(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MorayMobs.MODID,"block/" + item.getId().getPath()));
    }
}
