package net.dohaw.bongogui;

import lombok.Getter;

import java.util.List;

public class SlotActionWrapper {

    @Getter private List<String> commandsToExecute;
    @Getter private String guiKeyToOpen;

    public SlotActionWrapper(List<String> commandsToExecute, String guiKeyToOpen){
        this.commandsToExecute = commandsToExecute;
        this.guiKeyToOpen = guiKeyToOpen;
    }

}
