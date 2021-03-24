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

        if(sender instanceof Player){
            Player pSender = (Player) sender;
            if(args[0].equalsIgnoreCase("show") && args.length == 1 && sender.hasPermission("bongogui.show")){

                if(plugin.nonShowers.contains(pSender.getUniqueId())){
                    BongoUtils.ensureOneActivator(pSender);
                    plugin.nonShowers.remove(pSender.getUniqueId());
                    sender.sendMessage("The Bongo activator will show up in your inventory!");
                }else{
                    sender.sendMessage("You are already showing the Bongo activator!");
                }

            }else if(args[0].equalsIgnoreCase("vanish") && args.length == 1 && sender.hasPermission("bongogui.vanish")){
                BongoUtils.removeActivator(pSender);
                if(!plugin.nonShowers.contains(pSender.getUniqueId())){
                    plugin.nonShowers.add(pSender.getUniqueId());
                    sender.sendMessage("The Bongo activator will not be in your inventory anymore!");
                }else{
                    sender.sendMessage("The Bongo activator is already gone!");
                }
            }

        }else{
            sender.sendMessage("Only players can use this command!");
        }

        return false;
    }

}
