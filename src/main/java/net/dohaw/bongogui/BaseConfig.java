package net.dohaw.bongogui;

import net.dohaw.corelib.Config;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class BaseConfig extends Config {

    public BaseConfig(JavaPlugin plugin) {
        super(plugin, "config.yml");
        config.options().copyHeader(true);
    }

    public String getActivatorDisplayName(){
        return config.getString("Activator Display Name");
    }

    public List<String> getActivatorLore(){
        return config.getStringList("Activator Lore");
    }

    public ConfigurationSection getMenus(){
        return config.getConfigurationSection("Menus");
    }

    public String getActivatorMenuKey(){
        return config.getString("Activator Menu", null);
    }

    public String getActivatorMaterial(){
        return config.getString("Activator Material", "WRITABLE_BOOK");
    }

}
