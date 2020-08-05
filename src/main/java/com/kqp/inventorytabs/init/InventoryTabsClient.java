package com.kqp.inventorytabs.init;

import com.kqp.inventorytabs.api.TabProviderRegistry;
import com.kqp.inventorytabs.network.SyncClientConfigS2C;
import com.kqp.inventorytabs.tabs.TabManager;
import com.kqp.inventorytabs.interf.TabManagerContainer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.util.Identifier;

public class InventoryTabsClient implements ClientModInitializer {
    public static boolean serverDoSightCheckFlag = true;

    @Override
    public void onInitializeClient() {
        TabProviderRegistry.init();

        SyncClientConfigS2C.register();

        // Handle state of tab managerInventoryTabsClient
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            TabManagerContainer tabManagerContainer = (TabManagerContainer) client;

            if (client.world != null) {
                if (tabManagerContainer.getTabManager() == null) {
                    tabManagerContainer.setTabManager(new TabManager(client.player));
                    tabManagerContainer.getTabManager().init();
                } else {
                    tabManagerContainer.getTabManager().update();
                }
            } else {
                tabManagerContainer.setTabManager(null);
            }
        });
    }
}
