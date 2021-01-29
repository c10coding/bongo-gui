package net.dohaw.bongogui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CompassChecker extends BukkitRunnable {

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()){
            if(BongoUtils.getNumCompasses(player) > 1){
                BongoUtils.ensureOneCompass(player);
            }
        }
    }

}