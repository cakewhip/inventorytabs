package com.kqp.inventorytabs.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import java.nio.DoubleBuffer;

/**
 * Utility class for manipulating the client's mouse position.
 */
@Environment(EnvType.CLIENT)
public class MouseUtil {
    private static double mouseX = -1D, mouseY = -1D;

    public static void push() {
        mouseX = getMouseX();
        mouseY = getMouseY();
    }

    public static void tryPop() {
        if (mouseX != -1D && mouseY != -1D) {
            InputUtil
                .setCursorParameters(MinecraftClient.getInstance().getWindow().getHandle(), 212993,
                    mouseX, mouseY);

            mouseX = -1D;
            mouseY = -1D;
        }
    }

    public static double getMouseX() {
        DoubleBuffer mouseBuf = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(MinecraftClient.getInstance().getWindow().getHandle(), mouseBuf,
            null);

        return mouseBuf.get(0);
    }

    public static double getMouseY() {
        DoubleBuffer mouseBuf = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(MinecraftClient.getInstance().getWindow().getHandle(), null,
            mouseBuf);

        return mouseBuf.get(0);
    }
}
