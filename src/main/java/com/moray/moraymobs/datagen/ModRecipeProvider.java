package com.moray.moraymobs.datagen;

import com.moray.moraymobs.MorayMobs;
import com.moray.moraymobs.registries.Blockregistrires;
import com.moray.moraymobs.registries.Itemregististeries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {


    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blockregistrires.BLOCK_OF_SCALES.get())
                .pattern("$$$")
                .pattern("$$$")
                .pattern("$$$").define('$',Itemregististeries.BEETLE_SCALE.get())
                .unlockedBy(getHasName(Itemregististeries.BEETLE_SCALE.get()),has(Itemregististeries.BEETLE_SCALE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,Itemregististeries.BEETLE_SCALE.get(),9)
                .requires(Blockregistrires.BLOCK_OF_SCALES.get())
                .unlockedBy(getHasName(Blockregistrires.BLOCK_OF_SCALES.get()), has(Blockregistrires.BLOCK_OF_SCALES.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Itemregististeries.BEETLE_BOOTS.get())
                .pattern("   ")
                .pattern("$ $")
                .pattern("$ $").define('$',Itemregististeries.BEETLE_SCALE.get())
                .unlockedBy(getHasName(Itemregististeries.BEETLE_SCALE.get()),has(Itemregististeries.BEETLE_SCALE.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Itemregististeries.BEETLE_LEGGINGS.get())
                .pattern("$$$")
                .pattern("$ $")
                .pattern("$ $").define('$',Itemregististeries.BEETLE_SCALE.get())
                .unlockedBy(getHasName(Itemregististeries.BEETLE_SCALE.get()),has(Itemregististeries.BEETLE_SCALE.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Itemregististeries.BEETLE_HELMET.get())
                .pattern("$$$")
                .pattern("$ $")
                .pattern("   ").define('$',Itemregististeries.BEETLE_SCALE.get())
                .unlockedBy(getHasName(Itemregististeries.BEETLE_SCALE.get()),has(Itemregististeries.BEETLE_SCALE.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Itemregististeries.BEETLE_CHESTPLATE.get())
                .pattern("$ $")
                .pattern("$$$")
                .pattern("$$$").define('$',Itemregististeries.BEETLE_SCALE.get())
                .unlockedBy(getHasName(Itemregististeries.BEETLE_SCALE.get()),has(Itemregististeries.BEETLE_SCALE.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Itemregististeries.EEL_WHIP.get())
                .pattern("$  ")
                .pattern(" **")
                .pattern(" *&").define('$',Itemregististeries.JAW.get()).define('*', Items.BONE).define('&',Items.LEATHER)
                .unlockedBy(getHasName(Itemregististeries.JAW.get()),has(Itemregististeries.JAW.get()))
                .save(pWriter);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  MorayMobs.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
