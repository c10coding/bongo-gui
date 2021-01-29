package net.dohaw.bongogui;

import net.dohaw.corelib.Config;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class BaseConfig extends Config {

    public BaseConfig(JavaPlugin plugin) {
        super(plugin, "config.yml");
        config.options().header("THIS IS A TEST");
        config.options().copyHeader(true);
    }

    public String getCompassDisplayName(){
        return config.getString("Compass Display Name");
    }

    public List<String> getCompassLore(){
        return config.getStringList("Compass Lore");
    }

    public ConfigurationSection getMenus(){
        return config.getConfigurationSection("Menus");
    }

    public String getCompassMenuKey(){
        return config.getString("Compass Menu", null);
    }

}
