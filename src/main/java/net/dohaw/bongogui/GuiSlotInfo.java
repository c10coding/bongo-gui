package net.dohaw.bongogui;

import lombok.Builder;
import lombok.Getter;
import org.bukkit.Material;

import java.util.List;

@Builder
public class GuiSlotInfo {

    @Getter private int numSlot;
    @Getter private List<String> commandsRanOnClick;
    @Getter private String guiOpenedOnClick;
    @Getter private Material material;
    @Getter private int amount;
    @Getter private String displayName;
    @Getter private List<String> lore;

}
