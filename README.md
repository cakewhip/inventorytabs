# InventoryTabs
Adds tabs to access nearby blocks.

## Devs
### Importing
To add **Inventory Tabs** to your project, you need to add ``https://jitpack.io`` as a repo and ``com.github.cakewhip:inventorytabs:mcA.B.C-vX.Y.Z`` as a dependency. For example:
```
repositories {
	maven {
		url "https://jitpack.io"
	}
}

dependencies {
	modImplementation "com.github.cakewhip:inventorytabs:mcA.B.C-vX.Y.Z"
}
```
See the releases page for available versions.

### Adding Custom Tabs
The first step to adding a custom tab is creating a class that implements the ``Tab`` interface. This represents the tab that players will see, and it also controls what happens when the tab is clicked.

The next step is to register a ``TabProvider`` using the ``TabProviderRegistry``. ``TabProvider`` objects are called every tick in order to populate the list of tabs available to the player. The list is not managed by the mod, so be sure to check for duplicates yourself. The ``GenericBlockTabProvider`` class is provided, and serves as a basis for adding tabs for blocks that open handled screens (it also checks for duplicates for you). Feel free to see the ``EnderChestTabProvider`` and ``VanillaBlockTabProvider`` classes for implementation details.

Now, your handled screen needs to update the ``TabManager``, which you can grab a reference of by calling ``TabManager#getInstance``. In the ``init`` method (not your constructor), you **must** call ``TabManager$#onScreenOpen``. After doing that, you need to do a check to see if your screen was opened via tab or other means. If it **wasn't** opened via tab (check by calling ``TabManager#screenOpenedViaTab``), call ``TabManager#onOpenTab`` with your ``Tab`` object. See ``VanillaScreenTabAdder`` for more details on how to do this. You can also adjust the y-axis positioning of the bottom row of tabs by setting ``TabRenderer#bottomRowYOffset`` (there is a reference to a ``TabRenderer`` object in the ``TabManager``).

Finally, there are some methods to call for rendering and managing the tabs. In the ``render`` method before anything is drawn, call ``TabRenderer#renderBackground``. In the ``drawBackground``, call ``TabRenderer#renderForeground`` and ``TabRenderer#renderHoverTooltips``. In the ``mouseClicked`` method, call ``TabManager#mouseClicked``.