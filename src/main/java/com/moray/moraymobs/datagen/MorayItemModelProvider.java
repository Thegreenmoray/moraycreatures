package com.moray.moraymobs.datagen;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.registries.Itemregististeries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
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
    simpleItem(Itemregististeries.BRAIN);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MorayMobs.MODID,"item/" + item.getId().getPath()));
    }
}
