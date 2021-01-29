package net.dohaw.bongogui;

import net.dohaw.corelib.menus.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CustomGuiMenu extends Menu {

    private List<GuiSlotInfo> slotInfo;
    private Material fillerMat;

    public CustomGuiMenu(JavaPlugin plugin, String menuTitle, int numSlots, List<GuiSlotInfo> slotInfo, Material fillerMat) {
        super(plugin, null, menuTitle, numSlots);
        this.slotInfo = slotInfo;
        this.fillerMat = fillerMat;
    }

    @Override
    public void initializeItems(Player p) {
        for(GuiSlotInfo info : slotInfo){
            int slot = info.getNumSlot();
            String displayName = info.getDisplayName();
            Material mat = info.getMaterial();
            int amount = info.getAmount();
            List<String> lore = info.getLore();
            inv.setItem(slot, createGuiItem(mat, displayName, amount, lore));
        }
        if(fillerMat != null){
            setFillerMaterial(fillerMat);
        }
    }

    @Override
    protected void onInventoryClick(InventoryClickEvent e) {

    }
}
