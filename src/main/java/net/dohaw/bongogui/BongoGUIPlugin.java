package net.dohaw.bongogui;

import lombok.Getter;
import net.dohaw.corelib.CoreLib;
import net.dohaw.corelib.JPUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/*
    Made by c10coding on Github.
    Email: caleb.ja.owens@gmail.com
    Created on 1/29/2021.
    Hello future person :)
 */
public final class BongoGUIPlugin extends JavaPlugin {

    public static ItemStack compass;
    @Getter private BaseConfig baseConfig;
    @Getter private GuiManager guiManager;
    @Getter private String compassMenuKey;

    @Getter private BukkitTask compassChecker;

    @Override
    public void onEnable() {

        CoreLib.setInstance(this);

        JPUtils.validateFiles("config.yml");
        this.baseConfig = new BaseConfig(this);
        loadFields();
        JPUtils.registerEvents(new PlayerWatcher(this));
        JPUtils.registerCommand("bongogui", new BongoCommand(this));

    }

    @Override
    public void onDisable() { }

    public ItemStack getCompass(){
        return compass.clone();
    }

    public void loadFields(){
        this.compassMenuKey = baseConfig.getCompassMenuKey();
        compass = BongoUtils.createCompass(baseConfig);
        this.compassChecker = new CompassChecker().runTaskTimer(this, 0L, 100L);
        this.guiManager = new GuiManager(this);
        guiManager.loadGuis();
    }

}
