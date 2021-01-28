package net.dohaw.customgui;

import lombok.Getter;
import net.dohaw.corelib.CoreLib;
import net.dohaw.corelib.JPUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomGUIPlugin extends JavaPlugin {

    @Getter private BaseConfig baseConfig;

    @Override
    public void onEnable() {
        CoreLib.setInstance(this);
        JPUtils.validateFiles("config.yml");
        this.baseConfig = new BaseConfig(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
