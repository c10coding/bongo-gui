package net.dohaw.bongogui;

import lombok.Getter;
import net.dohaw.corelib.CoreLib;
import net.dohaw.corelib.JPUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.UUID;

/*
    Made by c10coding on Github.
    Email: caleb.ja.owens@gmail.com
    Created on 1/29/2021.
    Hello future person :)
 */
public final class BongoGUIPlugin extends JavaPlugin {

    public HashSet<UUID> nonShowers = new HashSet<>();

    public static ItemStack guiActivator;
    @Getter private BaseConfig baseConfig;
    @Getter private GuiManager guiManager;
    @Getter private String activatorMenuKey;

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

    public ItemStack getGuiActivator(){
        return guiActivator.clone();
    }

    public void loadFields(){
        this.activatorMenuKey = baseConfig.getActivatorMenuKey();
        guiActivator = BongoUtils.createGuiActivator(this);
        this.compassChecker = new ActivatorChecker(this).runTaskTimer(this, 0L, 100L);
        this.guiManager = new GuiManager(this);
        guiManager.loadGuis();
    }

}
