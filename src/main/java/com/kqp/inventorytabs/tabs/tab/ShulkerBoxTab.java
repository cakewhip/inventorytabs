package com.kqp.inventorytabs.tabs.tab;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.mob.ShulkerLidCollisions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

/**
 * Tab for shulker boxes.
 */
public class ShulkerBoxTab extends GenericBlockTab {
    public ShulkerBoxTab(Block block, BlockPos blockPos) {
        super(block, blockPos);
    }

    @Override
    public boolean shouldBeRemoved() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        BlockEntity blockEntity = player.world.getBlockEntity(blockPos);

        if (blockEntity instanceof ShulkerBoxBlockEntity) {
            BlockState blockState = player.world.getBlockState(blockPos);
            Direction direction = blockState.get(ShulkerBoxBlock.FACING);

            if (((ShulkerBoxBlockEntity) blockEntity).getAnimationStage() == ShulkerBoxBlockEntity.AnimationStage.CLOSED) {
                if (!player.world.doesNotCollide(ShulkerLidCollisions.getLidCollisionBox(blockPos, direction))) {
                    return true;
                }
            }
        }

        return super.shouldBeRemoved();
    }
}
