package net.dohaw.bongogui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerWatcher implements Listener {

    private BongoGUIPlugin plugin;

    public PlayerWatcher(BongoGUIPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onDropActivator(PlayerDropItemEvent e){
        if(BongoUtils.isBongoActivator(e.getItemDrop().getItemStack())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if(!BongoUtils.hasActivator(player)){
            ItemStack activator = plugin.getGuiActivator().clone();
            player.getInventory().addItem(activator);
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        Player player = e.getPlayer();
        if(BongoUtils.hasActivator(player)){
            BongoUtils.removeActivator(player);
        }
    }

    @EventHandler
    public void onUseActivator(PlayerInteractEvent e){
        Action action = e.getAction();
        if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){
            if(BongoUtils.isBongoActivator(e.getItem())){
                Player player = e.getPlayer();
                String activatorMenuKey = plugin.getActivatorMenuKey();
                if(activatorMenuKey != null){
                    CustomGuiMenu menu = plugin.getGuiManager().getMenu(activatorMenuKey);
                    menu.initializeItems(player);
                    menu.openInventory(player);
                }else{
                    player.sendMessage("There has been an error trying to open the menu. Please contact an administrator...");
                    throw new NullPointerException("The GUI Activator menu key in your BongoGUI config isn't set! Please fix it...");
                }
            }
        }
    }

    @EventHandler
    public void onPlaceActivator(InventoryClickEvent e){
        if(!(e.getInventory() instanceof CustomGuiMenu)){
            Inventory clickedInv = e.getClickedInventory();
            if(clickedInv != null){
                if(!(clickedInv instanceof PlayerInventory)){
                    ItemStack cursor = e.getCursor();
                    if(cursor != null){
                        if(BongoUtils.isBongoActivator(cursor)){
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

}
