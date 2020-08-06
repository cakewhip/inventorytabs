package com.kqp.inventorytabs.tabs.provider;

import com.kqp.inventorytabs.tabs.tab.*;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Provides tabs for vanilla blocks.
 * Ender chest has it's own since there should only be one at a time.
 */
public class VanillaBlockTabProvider extends GenericBlockTabProvider {
    private Set<Block> blocks = new HashSet();

    public VanillaBlockTabProvider() {
        blocks.add(Blocks.FURNACE);
        blocks.add(Blocks.CARTOGRAPHY_TABLE);
        blocks.add(Blocks.CRAFTING_TABLE);
        blocks.add(Blocks.ENCHANTING_TABLE);
        blocks.add(Blocks.GRINDSTONE);
        blocks.add(Blocks.LOOM);
        blocks.add(Blocks.SMITHING_TABLE);
        blocks.add(Blocks.STONECUTTER);
        blocks.add(Blocks.BARREL);
        blocks.add(Blocks.BLAST_FURNACE);
        blocks.add(Blocks.SMOKER);
        blocks.add(Blocks.BREWING_STAND);

        Registry.BLOCK.forEach(block -> {
            if (block instanceof AnvilBlock) {
                blocks.add(block);
            }
        });
    }

    public void removeBlock(Block block) {
        blocks.remove(block);
    }

    public Set<Block> getBlocks() {
        return this.blocks;
    }

    @Override
    public boolean matches(World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);

        if (blocks.contains(blockState.getBlock())) {
            return true;
        }

        return false;
    }

    @Override
    public Tab createTab(World world, BlockPos pos) {
        return new GenericBlockTab(world.getBlockState(pos).getBlock(), pos);
    }
}
