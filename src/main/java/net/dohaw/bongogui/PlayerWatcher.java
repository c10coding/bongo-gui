package net.dohaw.bongogui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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
        Action action = e.getAction();
        if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){
            if(BongoUtils.isBongoCompass(e.getItem())){
                Player player = e.getPlayer();
                String compassMenuKey = plugin.getCompassMenuKey();
                if(compassMenuKey != null){
                    CustomGuiMenu menu = plugin.getGuiManager().getMenu(compassMenuKey);
                    menu.initializeItems(player);
                    menu.openInventory(player);
                }else{
                    player.sendMessage("There has been an error trying to open the menu. Please contact an administrator...");
                    throw new NullPointerException("The compass menu key in your BongoGUI config isn't set! Please fix it...");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerPlaceCompass(InventoryClickEvent e){
        if(!(e.getInventory() instanceof CustomGuiMenu)){
            Inventory clickedInv = e.getClickedInventory();
            if(clickedInv != null){
                if(!(clickedInv instanceof PlayerInventory)){
                    ItemStack cursor = e.getCursor();
                    if(cursor != null){
                        if(BongoUtils.isBongoCompass(cursor)){
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

}
