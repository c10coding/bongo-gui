package net.dohaw.bongogui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerWatcher implements Listener {

    public PlayerWatcher(){ }

    @EventHandler
    public void onDropBongoCompass(PlayerDropItemEvent e){
        if(BongoUtils.isBongoCompass(e.getItemDrop().getItemStack())){
            e.setCancelled(true);
        }
    }

}
