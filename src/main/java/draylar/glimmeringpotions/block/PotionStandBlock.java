package draylar.glimmeringpotions.block;

import draylar.glimmeringpotions.entity.PotionStandBlockEntity;
import draylar.glimmeringpotions.item.base.UsePotionItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PotionStandBlock extends Block implements BlockEntityProvider {

    private final VoxelShape SHAPE = Block.createCuboidShape(5, 0, 5, 11, 2, 11);

    public PotionStandBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new PotionStandBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            // ensure our BE instance is the right type
            if(blockEntity instanceof PotionStandBlockEntity) {
                PotionStandBlockEntity potionStand = (PotionStandBlockEntity) blockEntity;
                ItemStack heldStack = player.getStackInHand(hand);

                // insert potion into stand
                if(potionStand.getPotion().isEmpty() && (heldStack.getItem() instanceof PotionItem || heldStack.getItem() instanceof UsePotionItem)) {
                    potionStand.setPotion(heldStack.copy());
                    heldStack.decrement(heldStack.getCount());
                    return ActionResult.SUCCESS;
                }

                // retrieve potion from stand
                if(!potionStand.getPotion().isEmpty() && heldStack.isEmpty()) {
                    player.setStackInHand(hand, potionStand.getPotion().copy());
                    potionStand.setPotion(ItemStack.EMPTY);
                    return ActionResult.SUCCESS;
                }
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PotionStandBlockEntity) {
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), ((PotionStandBlockEntity) blockEntity).getPotion());
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }
}
