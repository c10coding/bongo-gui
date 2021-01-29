package net.dohaw.bongogui;

import net.dohaw.corelib.Config;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class BaseConfig extends Config {

    public BaseConfig(JavaPlugin plugin) {
        super(plugin, "config.yml");
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


}
