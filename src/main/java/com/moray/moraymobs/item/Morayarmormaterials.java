package com.moray.moraymobs.item;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.registries.Itemregististeries;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;


import java.util.EnumMap;
import java.util.function.Supplier;

public enum Morayarmormaterials implements ArmorMaterial {

    BEETLE("beetle", 40, (EnumMap)Util.make(new EnumMap(ArmorItem.Type.class), (p_266655_) -> {
        p_266655_.put(ArmorItem.Type.BOOTS, 2);
        p_266655_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266655_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266655_.put(ArmorItem.Type.HELMET, 2);
    }), 10, SoundEvents.ARMOR_EQUIP_NETHERITE, 1.0F, 0F, () -> {
        return Ingredient.of(new ItemLike[]{Itemregististeries.BEETLE_SCALE.get()});
    });

    public static final StringRepresentable.EnumCodec<ArmorMaterials> CODEC = StringRepresentable.fromEnum(ArmorMaterials::values);
    private static final EnumMap HEALTH_FUNCTION_FOR_TYPE = (EnumMap)Util.make(new EnumMap(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    Morayarmormaterials(String pName, int pDurabilityMultiplier, EnumMap pProtectionFunctionForType, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier pRepairIngredient) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.protectionFunctionForType = pProtectionFunctionForType;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = new LazyLoadedValue(pRepairIngredient);
    }


    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return(Integer)HEALTH_FUNCTION_FOR_TYPE.get(type)*this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return (Integer) this.protectionFunctionForType.get(type);
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
