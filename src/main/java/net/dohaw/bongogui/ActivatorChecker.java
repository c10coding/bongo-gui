package net.dohaw.bongogui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ActivatorChecker extends BukkitRunnable {

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            int numActivators = BongoUtils.getNumActivators(player);
            if(numActivators > 1 || numActivators == 0){
                BongoUtils.ensureOneActivator(player);
            }
        }
    }

}
