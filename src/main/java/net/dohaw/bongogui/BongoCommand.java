package net.dohaw.bongogui;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BongoCommand implements CommandExecutor {

    private BongoGUIPlugin plugin;

    public BongoCommand(BongoGUIPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equalsIgnoreCase("reload") && args.length == 1 && sender.hasPermission("bongogui.reload")){

            for(Player player : Bukkit.getOnlinePlayers()){
                BongoUtils.removeActivator(player);
            }

            plugin.getBaseConfig().reloadConfig();
            plugin.getCompassChecker().cancel();
            plugin.loadFields();
            sender.sendMessage("You have reloaded the BongoGUI config!");
        }
        return false;
    }

}
