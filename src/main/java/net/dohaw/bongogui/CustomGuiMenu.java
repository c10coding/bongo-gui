package net.dohaw.bongogui;

import net.dohaw.corelib.JPUtils;
import net.dohaw.corelib.menus.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;

public class CustomGuiMenu extends Menu implements Listener {

    private Map<Integer, SlotActionWrapper> actionPerSlot = new HashMap<>();
    private List<GuiSlotInfo> slotInfo;
    private Material fillerMat;

    public CustomGuiMenu(JavaPlugin plugin, String menuTitle, int numSlots, List<GuiSlotInfo> slotInfo, Material fillerMat) {
        super(plugin, null, menuTitle, numSlots);
        this.slotInfo = slotInfo;
        this.fillerMat = fillerMat;
        JPUtils.registerEvents(this);
    }

    @Override
    public void initializeItems(Player p) {

        for(GuiSlotInfo info : slotInfo){

            int slot = info.getNumSlot();
            String displayName = info.getDisplayName();
            Material mat = info.getMaterial();
            int amount = info.getAmount();
            List<String> lore = info.getLore();

            if(mat != Material.PLAYER_HEAD){
                inv.setItem(slot, createGuiItem(mat, displayName, amount, lore));
            }else{
                ItemStack playerHead = getPlayerHead(info.getPlayerHeadUUID());
                inv.setItem(slot, createGuiItem(playerHead, displayName, lore));
            }

            this.actionPerSlot.put(slot, info.getActionWrapper());
        }

        if(fillerMat != null){
            setFillerMaterial(fillerMat);
            fillMenu(false);
        }

    }

    private ItemStack getPlayerHead(UUID uuid){

        boolean isNewVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");

        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        ItemStack item = new ItemStack(type, 1);

        if (!isNewVersion)
            item.setDurability((short) 3);

        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));

        item.setItemMeta(meta);

        return item;

    }

    @EventHandler
    @Override
    protected void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        int slotClicked = e.getSlot();
        ItemStack clickedItem = e.getCurrentItem();

        Inventory clickedInventory = e.getClickedInventory();
        Inventory topInventory = player.getOpenInventory().getTopInventory();

        if (clickedInventory == null) return;
        if (!topInventory.equals(inv)) return;
        if (clickedItem == null && e.getCursor() == null) return;

        if(clickedInventory.equals(inv)){

            e.setCancelled(true);

            SlotActionWrapper actionWrapper = actionPerSlot.getOrDefault(slotClicked, null);
            if(actionWrapper != null){

                boolean willCloseOnClick = actionWrapper.isWillCloseOnClick();
                if(willCloseOnClick){
                    player.closeInventory();
                }

                PromptCompletionWrapper pcw = actionWrapper.getPromptCompletionWrapper();
                String promptMessage = pcw.getPrompt();
                String completionCommand = pcw.getCommand();
                if(promptMessage != null && completionCommand != null){
                    player.closeInventory();
                    ConversationFactory cf = new ConversationFactory(plugin);
                    Conversation conv = cf.withFirstPrompt(new CommandCompletionPrompt((BongoGUIPlugin) plugin, promptMessage, completionCommand)).withLocalEcho(false).buildConversation(player);
                    conv.begin();
                }

                List<String> commandsRan = actionWrapper.getCommandsToExecute();
                if(commandsRan != null ){
                    if(!commandsRan.isEmpty()){
                        for(String command : commandsRan){
                            if(command.contains("/")){
                                command = command.replace("/", "");
                            }
                            if(command.contains("%player%")){
                                command = command.replace("%player%", player.getName());
                            }
                            player.performCommand(command);
                        }
                    }
                }

                String guiKeyToOpen = actionWrapper.getGuiKeyToOpen();
                if(guiKeyToOpen != null){
                    BongoGUIPlugin bongoGUIPlugin = (BongoGUIPlugin) plugin;
                    GuiManager guiManager = bongoGUIPlugin.getGuiManager();
                    CustomGuiMenu menu = guiManager.getMenu(guiKeyToOpen);
                    if(menu != null){
                        menu.initializeItems(player);
                        player.closeInventory();
                        menu.openInventory(player);
                    }else{
                        throw new NullPointerException("The menu key " + guiKeyToOpen + " does not exist! Please check your BongoGUI config!");
                    }
                }

            }

        }

    }

}
