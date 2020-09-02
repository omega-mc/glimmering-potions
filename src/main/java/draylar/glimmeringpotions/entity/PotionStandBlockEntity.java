package draylar.glimmeringpotions.entity;

import draylar.glimmeringpotions.registry.Blocks;
import draylar.glimmeringpotions.registry.Entities;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class PotionStandBlockEntity extends BlockEntity implements BlockEntityClientSerializable {

    private ItemStack potionStack = ItemStack.EMPTY;

    public PotionStandBlockEntity() {
        super(Entities.POTION_STAND);
    }

    public ItemStack getPotion() {
        return potionStack;
    }

    public void setPotion(ItemStack potionStack) {
        this.potionStack = potionStack;

        if(!world.isClient) {
            sync();
        }
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        CompoundTag stackData = tag.getCompound("StackData");
        potionStack = ItemStack.fromTag(stackData);
        super.fromTag(state, tag);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        CompoundTag stackData = new CompoundTag();
        potionStack.toTag(stackData);

        tag.put("StackData", stackData);
        return super.toTag(tag);
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        fromTag(Blocks.POTION_STAND.getDefaultState(), tag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        return toTag(tag);
    }
}
