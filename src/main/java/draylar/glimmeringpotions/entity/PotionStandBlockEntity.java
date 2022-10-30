package draylar.glimmeringpotions.entity;

import draylar.glimmeringpotions.registry.GPEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class PotionStandBlockEntity extends BlockEntity {

    private ItemStack potionStack;

    public PotionStandBlockEntity(BlockPos pos, BlockState state) {
        super(GPEntities.POTION_STAND, pos, state);
    }

    public ItemStack getPotion() {
        return potionStack;
    }

    public void setPotion(ItemStack potionStack) {
        this.potionStack = potionStack;

        if(!world.isClient) {
            world.markDirty(getPos());
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        NbtCompound stackData = nbt.getCompound("StackData");
        potionStack = ItemStack.fromNbt(stackData);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        NbtCompound stackData = new NbtCompound();
        potionStack.writeNbt(stackData);
        nbt.put("StackData", stackData);
    }
}
