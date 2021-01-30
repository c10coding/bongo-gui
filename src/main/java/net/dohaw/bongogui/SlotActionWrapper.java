package net.dohaw.bongogui;

import lombok.Getter;

import java.util.List;

public class SlotActionWrapper {

    @Getter private List<String> commandsToExecute;
    @Getter private String guiKeyToOpen;
    @Getter private boolean willCloseOnClick;
    @Getter private PromptCompletionWrapper promptCompletionWrapper;

    public SlotActionWrapper(List<String> commandsToExecute, String guiKeyToOpen, boolean willCloseOnClick, PromptCompletionWrapper promptCompletionWrapper){
        this.commandsToExecute = commandsToExecute;
        this.guiKeyToOpen = guiKeyToOpen;
        this.willCloseOnClick = willCloseOnClick;
        this.promptCompletionWrapper = promptCompletionWrapper;
    }

}
