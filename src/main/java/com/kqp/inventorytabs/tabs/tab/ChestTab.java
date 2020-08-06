package com.kqp.inventorytabs.tabs.tab;

import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;

/**
 * Tab for chests
 */
public class ChestTab extends SimpleBlockTab {
    public ChestTab(Block block, BlockPos blockPos) {
        super(block, blockPos);
    }

    @Override
    public boolean shouldBeRemoved() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (ChestBlock.isChestBlocked(player.world, blockPos)) {
            return true;
        }

        return super.shouldBeRemoved();
    }
}
