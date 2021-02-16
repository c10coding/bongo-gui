package net.dohaw.bongogui;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class SlotActionWrapper {

    @Getter private List<String> consoleCommandsRan;
    @Getter private List<String> playerCommandsRan;
    @Getter private String guiKeyToOpen;
    @Getter private boolean willCloseOnClick;
    @Getter private PromptCompletionWrapper promptCompletionWrapper;

}
