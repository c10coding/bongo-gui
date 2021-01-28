package net.dohaw.customgui;

import net.dohaw.corelib.menus.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CustomGuiMenu extends Menu {

    private List<GuiSlotInfo> slotInfo;

    public CustomGuiMenu(JavaPlugin plugin, Menu previousMenu, String menuTitle, int numSlots, List<GuiSlotInfo> slotInfo) {
        super(plugin, previousMenu, menuTitle, numSlots);
        this.slotInfo = slotInfo;
    }

    @Override
    public void initializeItems(Player p) {

    }

    @Override
    protected void onInventoryClick(InventoryClickEvent e) {

    }
}
