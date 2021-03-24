package net.dohaw.bongogui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ActivatorChecker extends BukkitRunnable {

    private BongoGUIPlugin plugin;

    public ActivatorChecker(BongoGUIPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            if(!plugin.nonShowers.contains(player.getUniqueId())){
                int numActivators = BongoUtils.getNumActivators(player);
                if(numActivators > 1 || numActivators == 0){
                    BongoUtils.ensureOneActivator(player);
                }
            }
        }
    }

}
