package net.dohaw.bongogui;

import lombok.Getter;
import net.dohaw.corelib.helpers.MathHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiManager {

    private BongoGUIPlugin plugin;
    @Getter private Map<String, CustomGuiMenu> menus = new HashMap<>();

    public GuiManager(BongoGUIPlugin plugin){
        this.plugin = plugin;
    }

    public void loadGuis(){

        ConfigurationSection menuSection = plugin.getBaseConfig().getMenus();
        if(menuSection != null){
            List<GuiSlotInfo> menuInfo = new ArrayList<>();
            for(String menuKey : menuSection.getKeys(false)){

                String rootPath = menuKey + ".";;

                ConfigurationSection slotsSection = menuSection.getConfigurationSection(rootPath + "Slots");
                if(slotsSection != null){
                    for(String slotStr : slotsSection.getKeys(false)){
                        if(MathHelper.isInt(slotStr)){

                            int slot = Integer.parseInt(slotStr);
                            rootPath = slot + ".";

                            List<String> lore = slotsSection.getStringList(rootPath + "Lore");
                            List<String> commandsRanOnClick = slotsSection.getStringList(rootPath + "Actions.Commands");
                            int amount = slotsSection.getInt(rootPath + "Amount", 1);

                            String displayName = slotsSection.getString(rootPath + "Display Name", null);
                            String guiOpenedOnClick = slotsSection.getString(rootPath + "Actions.GUI");

                            Material mat = Material.valueOf(slotsSection.getString(rootPath + "Material", "RED_WOOL"));

                            GuiSlotInfo info = GuiSlotInfo.builder()
                                .amount(amount)
                                .commandsRanOnClick(commandsRanOnClick)
                                .lore(lore)
                                .material(mat)
                                .numSlot(slot)
                                .guiOpenedOnClick(guiOpenedOnClick)
                                .displayName(displayName)
                                .build();

                            menuInfo.add(info);

                        }else{
                            throw new IllegalArgumentException("There is a slot that isn't an integer in the gui menu \"" + menuKey + "\"");
                        }
                    }

                }else{
                    throw new NullPointerException("The \"Slots\" sections in your config cannot be found!");
                }

                rootPath = menuKey + ".";
                Material fillerMat = Material.valueOf(menuSection.getString(rootPath + "Filler Material", "AIR"));
                String title = menuSection.getString(rootPath + "Title", "Custom GUI");
                int numSlots = menuSection.getInt(rootPath + "Number of Slots", 9);
                CustomGuiMenu menu = new CustomGuiMenu(plugin, title, numSlots, menuInfo, fillerMat);
                menus.put(menuKey, menu);
                menuInfo = new ArrayList<>();

            }

        }else{
            throw new NullPointerException("The Menus section in your config can't be found!");
        }

    }

    public CustomGuiMenu getMenu(String key){
        return menus.getOrDefault(key, null);
    }

}
