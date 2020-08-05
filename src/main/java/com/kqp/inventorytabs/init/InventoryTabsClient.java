package com.kqp.inventorytabs.init;

import com.kqp.inventorytabs.api.TabProviderRegistry;
import com.kqp.inventorytabs.network.SyncClientConfigS2C;
import com.kqp.inventorytabs.tabs.TabManager;
import com.kqp.inventorytabs.interf.TabManagerContainer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class InventoryTabsClient implements ClientModInitializer {
    public static boolean serverDoSightCheckFlag = true;

    @Override
    public void onInitializeClient() {
        TabProviderRegistry.init();

        SyncClientConfigS2C.register();

        // Handle state of tab managerInventoryTabsClient
        ClientTickEvents.START_WORLD_TICK.register(world -> {
            TabManagerContainer tabManagerContainer = (TabManagerContainer) MinecraftClient.getInstance();

            tabManagerContainer.getTabManager().update();
        });
    }
}
