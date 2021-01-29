package net.dohaw.bongogui;

import net.dohaw.corelib.helpers.ItemStackHelper;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class BongoUtils {

    public static String pdcCompassKey = "bongo_compass";

    public static ItemStack createCompass(BaseConfig baseConfig){

        ItemStack compass = new ItemStack(Material.COMPASS);
        String displayName = baseConfig.getCompassDisplayName();
        List<String> lore = baseConfig.getCompassLore();
        ItemMeta meta = compass.getItemMeta();

        assert meta != null;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(NamespacedKey.minecraft(pdcCompassKey), PersistentDataType.INTEGER, 1);
        meta.setLore(lore);
        meta.setDisplayName(displayName);
        compass.setItemMeta(meta);

        ItemStackHelper.addGlowToItem(compass);
        return compass;

    }

    public static boolean hasCompass(Player player){
        PlayerInventory playerInv = player.getInventory();
        ItemStack[] contents = playerInv.getContents();
        for(ItemStack is : contents){
            if(isBongoCompass(is)){
                return true;
            }
        }
        return false;
    }

    public static int getNumCompasses(Player player){
        PlayerInventory playerInv = player.getInventory();
        ItemStack[] contents = playerInv.getContents();
        int amount = 0;
        for(ItemStack is : contents){
            if(isBongoCompass(is)){
                amount++;
            }
        }
        return amount;
    }

    public static boolean isBongoCompass(ItemStack stack){
        ItemMeta meta = stack.getItemMeta();
        if(meta != null){
            PersistentDataContainer pdc = meta.getPersistentDataContainer();
            return pdc.has(NamespacedKey.minecraft(pdcCompassKey), PersistentDataType.INTEGER);
        }
        return false;
    }

    public static void ensureOneCompass(Player player){
        PlayerInventory inv = player.getInventory();
        for(ItemStack is : inv.getContents()){
            if(isBongoCompass(is)){
                is.setAmount(1);
            }
        }
    }

}
