package com.kqp.inventorytabs.mixin.client;

import com.kqp.inventorytabs.interf.TabManagerContainer;
import com.kqp.inventorytabs.tabs.TabManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class TabManagerContainerImplementer implements TabManagerContainer {
    private final TabManager tabManager = new TabManager();

    @Override
    public TabManager getTabManager() {
        return tabManager;
    }
}
