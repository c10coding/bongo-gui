package net.dohaw.bongogui;

import lombok.Getter;
import net.dohaw.corelib.CoreLib;
import net.dohaw.corelib.JPUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class BongoGUIPlugin extends JavaPlugin {


    public static ItemStack compass;
    @Getter private BaseConfig baseConfig;
    @Getter private GuiManager guiManager;
    @Getter private String compassMenuKey;

    @Override
    public void onEnable() {

        CoreLib.setInstance(this);

        JPUtils.validateFiles("config.yml");
        this.baseConfig = new BaseConfig(this);

        this.compassMenuKey = baseConfig.getCompassMenuKey();
        compass = BongoUtils.createCompass(baseConfig);
        new CompassChecker().runTaskTimer(this, 0L, 20L);

        this.guiManager = new GuiManager(this);
        guiManager.loadGuis();

        JPUtils.registerEvents(new PlayerWatcher(this));

    }

    @Override
    public void onDisable() { }

    public ItemStack getCompass(){
        return compass.clone();
    }

}
