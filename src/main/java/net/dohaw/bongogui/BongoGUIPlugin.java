package net.dohaw.bongogui;

import lombok.Getter;
import net.dohaw.corelib.CoreLib;
import net.dohaw.corelib.JPUtils;
import net.dohaw.corelib.helpers.ItemStackHelper;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class BongoGUIPlugin extends JavaPlugin {

    private ItemStack compass;
    @Getter private BaseConfig baseConfig;

    @Override
    public void onEnable() {
        CoreLib.setInstance(this);
        JPUtils.validateFiles("config.yml");
        this.baseConfig = new BaseConfig(this);
        this.compass = BongoUtils.createCompass(baseConfig);
        JPUtils.registerEvents(new PlayerWatcher());
        new CompassChecker().runTaskTimer(this, 0L, 20L);
    }

    @Override
    public void onDisable() { }

    public ItemStack getCompass(){
        return compass.clone();
    }

}
