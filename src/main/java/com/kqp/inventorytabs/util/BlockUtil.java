package com.kqp.inventorytabs.util;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

public class BlockUtil {
    public static BlockHitResult getLineOfSight(BlockPos pos, PlayerEntity player, double distance) {
        World world = player.world;
        BlockState blockState = world.getBlockState(pos);
        double distanceSquared = distance * distance;

        Vec3d playerHead = player.getPos().add(0D, player.getEyeHeight(player.getPose()), 0D);
        
        Vec3d center = new Vec3d(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
        
        BlockHitResult hitResult = getBlockHitResult(playerHead, center, distanceSquared, world, pos, blockState);
        
        if (hitResult == null) {
            Vec3d baseCorner = new Vec3d(pos.getX() + 0.25D, pos.getY() + 0.25D, pos.getZ() + 0.25D);

            for (int x = 0; x < 2; x++) {
                for (int y = 0; y < 2; y++) {
                    for (int z = 0; z < 2; z++) {
                        Vec3d corner = baseCorner.add(x * 0.5D, y * 0.5D, z * 0.5D);

                        hitResult = getBlockHitResult(playerHead, corner, distanceSquared, world, pos, blockState);

                        if (hitResult != null) {
                            break;
                        }
                    }
                }
            }
        }

        if (hitResult != null) {
            if (hitResult.getBlockPos().equals(pos)) {
                return hitResult;
            }
        }

        return null;
    }
    
    private static BlockHitResult getBlockHitResult(Vec3d playerHead, Vec3d blockVec, double distanceSquared, World world, BlockPos pos, BlockState blockState) {
        if (blockVec.subtract(playerHead).lengthSquared() >= distanceSquared) {
            return null;
        }

        BlockHitResult result = world.rayTrace(new RayTraceContext(
                playerHead,
                blockVec,
                RayTraceContext.ShapeType.OUTLINE,
                RayTraceContext.FluidHandling.NONE,
                MinecraftClient.getInstance().player
        ));

        if (result != null && result.getType() == HitResult.Type.BLOCK && result.getBlockPos().equals(pos)) {
            return result;
        }

        return null;
    }
}
