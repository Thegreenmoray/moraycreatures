package com.moray.moraymobs.registries;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.item.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


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

    final public static RegistryObject<Item> PADDLE_SPAWN_EGG=ITEM.register
            ("spawn_lava_paddlefish",()-> new ForgeSpawnEggItem(Mobregistries.PADDLEFISH,0xff2500,0xe56520,new Item.Properties()));
    final public static RegistryObject<Item> SOULCATCHER_SPAWN_EGG=ITEM.register
            ("spawn_soulcatcher",()-> new ForgeSpawnEggItem(Mobregistries.SOULCATCHER,0x00FFFF,0x964B00,new Item.Properties()));

    final public static RegistryObject<Item> BOWFIN_SPAWN_EGG=ITEM.register
            ("spawn_endbowfin",()-> new ForgeSpawnEggItem(Mobregistries.BOWFIN,0xEEF6B4,0x3f1b40,new Item.Properties()));

    final public static RegistryObject<Item> PRONGHORN_SPAWN_EGG=ITEM.register
            ("spawn_pronghorn",()-> new ForgeSpawnEggItem(Mobregistries.PRONGHORN,0xcfa141,0xb39671,new Item.Properties()));

    final public static RegistryObject<Item>  THRESHERSHARK_SPAWN_EGG=ITEM.register
            ("spawn_thresher",()-> new ForgeSpawnEggItem(Mobregistries.THRESHER,0xADD8E6,0xFFFFFF,new Item.Properties()));

    final public static RegistryObject<Item> BRAIN=ITEM.register("brain",
           ()->new Brainitem(new Item.Properties().stacksTo(8)));

    final public static RegistryObject<Item> JAW=ITEM.register("jawloose",
            ()->new Item(new Item.Properties().stacksTo(64)));

    final public static RegistryObject<Item> END_SEED=ITEM.register("shulkerberryseed",
            ()->new ItemNameBlockItem(Blockregistrires.SHULKERFRUIT_CROP.get(),new Item.Properties().stacksTo(64)));

    final public static RegistryObject<Item> SHULKERBERRY=ITEM.register("shulkerberry",
            ()->new Shulkerfruit(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(3).saturationMod(3).build()).stacksTo(64)));

    final public static RegistryObject<Item> RAW_BOWFIN=ITEM.register("rawbowfin",
            ()->new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).meat().saturationMod(3).build()).stacksTo(64)));

    final public static RegistryObject<Item> COOKED_BOWFIN=ITEM.register("cookedbowfin",
            ()->new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).meat().saturationMod(7).build()).stacksTo(64)));

    final public static RegistryObject<Item> RAW_PRONGHORN=ITEM.register("rawpronghorn",
            ()->new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).meat().saturationMod(2).build()).stacksTo(64)));

    final public static RegistryObject<Item> COOKED_PRONGHORN=ITEM.register("cookedpronghorn",
            ()->new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).meat().saturationMod(10).build()).stacksTo(64)));



    final public static RegistryObject<Item> MOSS=ITEM.register("chorousmoss",
            ()->new PlaceOnWaterBlockItem(Blockregistrires.PADDED_MOSS.get(),
                    new Item.Properties().stacksTo(64)));


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
            ()->new Eelwhip(Tiers.WOOD,3, 0.000000001F,new Item.Properties().durability(450)));

    final public static RegistryObject<Item> BASALT_CRYSTAL=ITEM.register("basalt_crystal",
            ()->new Cagedbasalitisk(new Item.Properties().stacksTo(64)));

    final public static RegistryObject<Item> BUCKETED_PADDLEFISH=ITEM.register("paddlefishbucket",
           ()->new Moraybuckets( Mobregistries.PADDLEFISH,Fluids.LAVA,new Item.Properties()));

    final public static RegistryObject<Item> BUCKETED_BOWFIN=ITEM.register("bowfinbucket",
            ()->new Moraybuckets( Mobregistries.BOWFIN,Fluids.WATER,new Item.Properties()));

    final public static RegistryObject<Item> PADDLEFISH_FOOD=ITEM.register("paddlefishfood",
            ()->new Item(new Item.Properties().fireResistant().food(new FoodProperties.Builder().nutrition(6).saturationMod(3).meat().build())));

    final public static RegistryObject<Item> SOULJEWEL=ITEM.register("soulbead",
            ()->new Item(new Item.Properties().stacksTo(64)));

    final public static RegistryObject<Item> SOULBEADRING=ITEM.register("soulbeamjewel",
            ()->new Soulbeamweapon(new Item.Properties().durability(100)));

    final public static RegistryObject<Item> ANTLER=ITEM.register("pronghornantler",
            ()->new Item(new Item.Properties().stacksTo(64)));

    final public static RegistryObject<Item> BOTTLE_OF_SHOCK=ITEM.register("glassofstunwave",
            ()->new Item(new Item.Properties().stacksTo(64)));

    final public static RegistryObject<Item> STUNGUN=ITEM.register("stungun",
            ()->new Stungun(new Item.Properties().durability(50)));


    public static void register(IEventBus bus){
        ITEM.register(bus);
    }

}
