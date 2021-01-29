package net.dohaw.bongogui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerWatcher implements Listener {

    private BongoGUIPlugin plugin;

    public PlayerWatcher(BongoGUIPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onDropBongoCompass(PlayerDropItemEvent e){
        if(BongoUtils.isBongoCompass(e.getItemDrop().getItemStack())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if(!BongoUtils.hasCompass(player)){
            ItemStack compass = plugin.getCompass().clone();
            player.getInventory().addItem(compass);
        }
    }

    @EventHandler
    public void onPlayerUseCompass(PlayerInteractEvent e){
        if(BongoUtils.isBongoCompass(e.getItem())){
            Player player = e.getPlayer();
            String compassMenuKey = plugin.getCompassMenuKey();
            CustomGuiMenu menu = plugin.getGuiManager().getMenu(compassMenuKey);
            menu.initializeItems(player);
            menu.openInventory(player);
        }
    }

}
