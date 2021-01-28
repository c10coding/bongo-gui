package net.dohaw.customgui;

import net.dohaw.corelib.Config;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class BaseConfig extends Config {

    public BaseConfig(JavaPlugin plugin) {
        super(plugin, "config.yml");
    }

    public String getBookName(){
        return config.getString("Book Name");
    }

    public ConfigurationSection getMenus(){
        return config.getConfigurationSection("Menus");
    }


}
