package net.dohaw.bongogui;

import lombok.Getter;

public class PromptCompletionWrapper {

    @Getter private String prompt, command;

    PromptCompletionWrapper(String prompt, String command){
        this.prompt = prompt;
        this.command = command;
    }

}
