package com.moray.moraymobs.registries;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.item.Beetlearmor;
import com.moray.moraymobs.item.Brainitem;
import com.moray.moraymobs.item.Eelwhip;
import com.moray.moraymobs.item.Morayarmormaterials;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class Itemregististeries {

    final public static DeferredRegister<Item> ITEM=DeferredRegister.create(
            ForgeRegistries.ITEMS, MorayMobs.MODID);

    final public static RegistryObject<Item> OPPOSUM_SPAWN_EGG=ITEM.register
            ("spawn_opposum",()-> new ForgeSpawnEggItem(Mobregistries.OPOSSUM,0xAEB2AF,0xf1e7de,new Item.Properties()));

    final public static RegistryObject<Item> BODYSNATCHER_SPAWN_EGG=ITEM.register
            ("spawn_body_snatcher",()-> new ForgeSpawnEggItem(Mobregistries.BODY_SNATCHER,0xf3b5b8,0xc41f26,new Item.Properties()));

    final public static RegistryObject<Item> BASALTlISK_SPAWN_EGG=ITEM.register
            ("spawn_basaltlisk",()-> new ForgeSpawnEggItem(Mobregistries.BASALTISK,0x575d5e,0x545058,new Item.Properties()));

    final public static RegistryObject<Item> VOLCANOBACK_SPAWN_EGG=ITEM.register
            ("spawn_volcanoback",()-> new ForgeSpawnEggItem(Mobregistries.VOLCANOBACK,0x652828,0xc65626,new Item.Properties()));
    final public static RegistryObject<Item> MORAY_SPAWN_EGG=ITEM.register
            ("spawn_moray",()-> new ForgeSpawnEggItem(Mobregistries.MORAY,0xbcbcbc,0xadabad,new Item.Properties()));



    final public static RegistryObject<Item> BRAIN=ITEM.register("brain",
           ()->new Brainitem(new Item.Properties().stacksTo(8)));

    final public static RegistryObject<Item> JAW=ITEM.register("jawloose",
            ()->new Item(new Item.Properties().stacksTo(64)));

    final public static RegistryObject<Item> BEETLE_SCALE=ITEM.register("scale",
            ()->new Item(new Item.Properties().stacksTo(64)));

    final public static RegistryObject<Item> BEETLE_HELMET=ITEM.register("beetlemask",
            ()->new Beetlearmor(Morayarmormaterials.BEETLE, ArmorItem.Type.HELMET,new Item.Properties().fireResistant()));

    final public static RegistryObject<Item> BEETLE_CHESTPLATE=ITEM.register("beetlechestplate",
            ()->new Beetlearmor(Morayarmormaterials.BEETLE, ArmorItem.Type.CHESTPLATE,new Item.Properties().fireResistant()));

    final public static RegistryObject<Item> BEETLE_LEGGINGS=ITEM.register("beetleleggings",
            ()->new Beetlearmor(Morayarmormaterials.BEETLE, ArmorItem.Type.LEGGINGS,new Item.Properties().fireResistant()));

    final public static RegistryObject<Item> BEETLE_BOOTS=ITEM.register("beetleboots",
            ()->new Beetlearmor(Morayarmormaterials.BEETLE, ArmorItem.Type.BOOTS,new Item.Properties().fireResistant()));

    final public static RegistryObject<Item> EEL_WHIP=ITEM.register("eelwhip",
            ()->new Eelwhip(Tiers.WOOD,0, 0.9F,new Item.Properties().durability(450)));



    public static void register(IEventBus bus){
        ITEM.register(bus);
    }

}
