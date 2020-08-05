package com.kqp.inventorytabs.interf;

import com.kqp.inventorytabs.tabs.TabManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * Interface for holding the tab manager.
 * Gets injected into {@link net.minecraft.client.MinecraftClient}.
 */
@Environment(EnvType.CLIENT)
public interface TabManagerContainer {
    void setTabManager(TabManager tabManager);

    TabManager getTabManager();
}
