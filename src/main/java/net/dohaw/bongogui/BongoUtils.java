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

    public static String pdcActivatorKey = "bongo_activator";
    private static Material activatorMaterial;

    public static ItemStack createGuiActivator(BongoGUIPlugin plugin){

        BaseConfig baseConfig = plugin.getBaseConfig();
        String matStr = baseConfig.getActivatorMaterial().toUpperCase();
        try{
            activatorMaterial = Material.valueOf(matStr);
        }catch(IllegalArgumentException e){
            plugin.getLogger().warning("The Activator Material given is not a valid material! (" + matStr + "). If you wish to see the list of valid materials, here they are: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html");
            plugin.getLogger().warning("By default, the Activator Material has been set to WRITABLE_BOOK");
            activatorMaterial = Material.WRITABLE_BOOK;
        }

        ItemStack activator = new ItemStack(activatorMaterial);
        String displayName = baseConfig.getActivatorDisplayName();
        List<String> lore = baseConfig.getActivatorLore();
        ItemMeta meta = activator.getItemMeta();

        assert meta != null;
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(NamespacedKey.minecraft(pdcActivatorKey), PersistentDataType.INTEGER, 1);
        meta.setLore(lore);
        meta.setDisplayName(displayName);
        activator.setItemMeta(meta);

        ItemStackHelper.addGlowToItem(activator);
        return activator;

    }

    public static boolean hasActivator(Player player){
        PlayerInventory playerInv = player.getInventory();
        ItemStack[] contents = playerInv.getContents();
        for(ItemStack is : contents){
            if(isBongoActivator(is)){
                return true;
            }
        }
        return false;
    }

    public static int getNumActivators(Player player){
        PlayerInventory playerInv = player.getInventory();
        ItemStack[] contents = playerInv.getContents();
        int amount = 0;
        for(ItemStack is : contents){
            if(isBongoActivator(is)){
                amount += is.getAmount();
            }
        }
        return amount;
    }

    public static boolean isBongoActivator(ItemStack stack){
        if(stack != null){
            ItemMeta meta = stack.getItemMeta();
            if(meta != null){
                PersistentDataContainer pdc = meta.getPersistentDataContainer();
                return pdc.has(NamespacedKey.minecraft(pdcActivatorKey), PersistentDataType.INTEGER) && stack.getType() == activatorMaterial;
            }
        }
        return false;
    }

    public static void ensureOneActivator(Player player){
        PlayerInventory inv = player.getInventory();
        for(ItemStack is : inv.getContents()){
            if(isBongoActivator(is)){
                inv.remove(is);
            }
        }
        inv.addItem(BongoGUIPlugin.guiActivator.clone());
    }

    public static void removeActivator(Player player){
        PlayerInventory inv = player.getInventory();
        for(ItemStack is : inv.getContents()){
            if(isBongoActivator(is)){
                inv.remove(is);
            }
        }
    }

}
