package net.dohaw.customgui;

import lombok.Builder;
import org.bukkit.Material;

import java.util.List;

@Builder
public class GuiSlotInfo {

    private int numSlot;
    private List<String> commandsRanOnClick;
    private String guiOpenedOnClick;
    private Material material;
    private int amount;
    private String displayName;
    private List<String> lore;

}
