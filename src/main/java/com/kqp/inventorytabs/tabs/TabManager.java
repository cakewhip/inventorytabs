package com.kqp.inventorytabs.tabs;

import com.kqp.inventorytabs.api.TabProviderRegistry;
import com.kqp.inventorytabs.interf.TabManagerContainer;
import com.kqp.inventorytabs.mixin.client.accessor.HandledScreenAccessor;
import com.kqp.inventorytabs.tabs.render.TabRenderInfo;
import com.kqp.inventorytabs.tabs.render.TabRenderer;
import com.kqp.inventorytabs.tabs.tab.PlayerTab;
import com.kqp.inventorytabs.tabs.tab.Tab;
import com.kqp.inventorytabs.util.MouseUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages everything related to tabs.
 *
 */
@Environment(EnvType.CLIENT)
public class TabManager {
    public final List<Tab> tabs;
    public Tab currentTab;

    private HandledScreen currentScreen;
    public int currentPage = 0;
    public boolean tabOpenedRecently;

    public final TabRenderer tabRenderer;

    public TabManager() {
        this.tabs = new ArrayList();
        this.tabRenderer = new TabRenderer(this);

        tabs.add(new PlayerTab());
    }

    public void update() {
        refreshAvailableTabs();

        for (int i = 0; i < tabs.size(); i++) {
            if (tabs.get(i).shouldBeRemoved(MinecraftClient.getInstance().player)) {
                tabs.remove(i);
                i--;
            }
        }
    }

    public void setCurrentTab(Tab tab) {
        this.currentTab = tab;
    }

    private void refreshAvailableTabs() {
        TabProviderRegistry.getTabProviders().forEach(tabProvider -> {
            tabProvider.addAvailableTabs(MinecraftClient.getInstance().player, tabs);
        });
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int guiWidth = ((HandledScreenAccessor) currentScreen).getBackgroundWidth();
            int guiHeight = ((HandledScreenAccessor) currentScreen).getBackgroundHeight();
            int x = (currentScreen.width - guiWidth) / 2;
            int y = (currentScreen.height - guiHeight) / 2;

            if (mouseX > x && mouseX < x + guiWidth
                    && mouseY > y && mouseY < y + guiHeight) {
                return false;
            }

            // Check back button
            if (new Rectangle(x - TabRenderer.BUTTON_WIDTH - 4, y - 16, TabRenderer.BUTTON_WIDTH, TabRenderer.BUTTON_HEIGHT).contains(mouseX, mouseY)) {
                if (canGoBack()) {
                    setCurrentPage(currentPage - 1);
                    MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                    return true;
                }
            }

            // Check forward button
            if (new Rectangle(x + guiWidth + 4, y - 16, TabRenderer.BUTTON_WIDTH, TabRenderer.BUTTON_HEIGHT).contains(mouseX, mouseY)) {
                if (canGoForward()) {
                    setCurrentPage(currentPage + 1);
                    MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));

                    return true;
                }
            }

            TabRenderInfo[] tabRenderInfos = tabRenderer.getTabRenderInfos();

            for (int i = 0; i < tabRenderInfos.length; i++) {
                TabRenderInfo tabRenderInfo = tabRenderInfos[i];

                if (tabRenderInfo != null) {
                    if (tabRenderInfo.tabReference != currentTab) {
                        Rectangle rect = new Rectangle(
                                tabRenderInfo.x,
                                tabRenderInfo.y,
                                tabRenderInfo.texW,
                                tabRenderInfo.texH
                        );

                        if (rect.contains(mouseX, mouseY)) {
                            onTabClick(tabRenderInfo.tabReference);

                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public void onScreenOpen(HandledScreen screen) {
        setCurrentScreen(screen);
        tabRenderer.bottomRowYOffset = 0;
        MouseUtil.tryPop();
    }

    public void onTabClick(Tab tab) {
        MouseUtil.push();

        tabOpenedRecently = true;

        onOpenTab(tab);

        tab.open(MinecraftClient.getInstance().player);
    }

    public void onOpenTab(Tab tab) {
        setCurrentTab(tab);
        setCurrentPage(pageOf(tab));
    }

    public int pageOf(Tab tab) {
        int index = tabs.indexOf(tab);

        return index / (getMaxRowLength() * 2);
    }

    public int getMaxRowLength() {
        int guiWidth = ((HandledScreenAccessor) currentScreen).getBackgroundWidth();
        int maxRowLength = guiWidth / (TabRenderer.TAB_WIDTH + 1);

        return maxRowLength;
    }

    public void setCurrentScreen(HandledScreen screen) {
        this.currentScreen = screen;
    }

    public HandledScreen getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentPage(int page) {
        if (page > 0 && tabs.size() < getMaxRowLength() * 2) {
            System.err.println("Not enough tabs to paginate, ignoring");

            return;
        }

        this.currentPage = page;
    }

    public boolean screenOpenedViaTab() {
        if (tabOpenedRecently) {
            tabOpenedRecently = false;

            return true;
        }

        return false;
    }

    public int getMaxPages() {
        return tabs.size() / (getMaxRowLength() * 2 + 1);
    }

    public boolean canGoBack() {
        return currentPage != 0;
    }

    public boolean canGoForward() {
        return currentPage < getMaxPages();
    }

    public static TabManager getInstance() {
        return ((TabManagerContainer) MinecraftClient.getInstance()).getTabManager();
    }
}
