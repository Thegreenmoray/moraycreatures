package com.moray.moraymobs.item;

import com.moray.moraymobs.registries.Mobregistries;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class Moraybuckets extends MobBucketItem {
    public Moraybuckets(Supplier<? extends EntityType<?>> entitySupplier,Fluid fluid, Properties properties) {
        super(entitySupplier, ()->fluid, ()->SoundEvents.BUCKET_EMPTY_FISH,properties.stacksTo(1));
    }

    public void checkExtraContent(@Nullable Player pPlayer, Level pLevel, ItemStack pContainerStack, BlockPos pPos) {
        if (pLevel instanceof ServerLevel) {
            this.spawnfish((ServerLevel)pLevel, pContainerStack, pPos);
            pLevel.gameEvent(pPlayer, GameEvent.ENTITY_PLACE, pPos);
        }

    }

    protected void playEmptySound(@Nullable Player pPlayer, LevelAccessor pLevel, BlockPos pPos) {
        pLevel.playSound(pPlayer, pPos, this.getEmptySound(), SoundSource.NEUTRAL, 1.0F, 1.0F);
    }

    private void spawnfish(ServerLevel pServerLevel, ItemStack pBucketedMobStack, BlockPos pPos) {
        Entity entity = this.getFishType().spawn(pServerLevel, pBucketedMobStack, (Player)null, pPos, MobSpawnType.BUCKET, true, false);
        if (entity instanceof Bucketable bucketable) {
            bucketable.loadFromBucketTag(pBucketedMobStack.getOrCreateTag());
            bucketable.setFromBucket(true);
        }

    }

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        //if (this.getFishType() == ) {
         //   CompoundTag compoundtag = pStack.getTag();
         //   if (compoundtag != null && compoundtag.contains("BucketVariantTag", 3)) {
          //      int i = compoundtag.getInt("BucketVariantTag");
           //     ChatFormatting[] achatformatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};


          //      for(int j = 0; j < TropicalFish.COMMON_VARIANTS.size(); ++j) {
           //         if (i == ((TropicalFish.Variant)TropicalFish.COMMON_VARIANTS.get(j)).getPackedId()) {
            //            pTooltipComponents.add(Component.translatable(TropicalFish.getPredefinedName(j)).withStyle(achatformatting));
                        return;
            //        }
           //     }

         //   }
       // }

    }


}
