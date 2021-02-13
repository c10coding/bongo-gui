package net.dohaw.bongogui;

import lombok.Builder;
import lombok.Getter;
import org.bukkit.Material;

import java.util.List;
import java.util.UUID;

@Builder
public class GuiSlotInfo {

    @Getter private int numSlot;
    @Getter SlotActionWrapper actionWrapper;
    @Getter private Material material;
    @Getter private UUID playerHeadUUID;
    @Getter private int amount;
    @Getter private String displayName;
    @Getter private List<String> lore;

    public String toString(){
        return "SLOT: " + numSlot + " | MATERIAL: " + material + " | ";
    }

}
