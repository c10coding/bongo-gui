package net.dohaw.bongogui;

import org.bukkit.Bukkit;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

public class CommandCompletionPrompt extends StringPrompt {

    private String msg, command;

    public CommandCompletionPrompt(String msg, String command){
        this.msg = msg;
        this.command = command;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return msg;
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        String commandToRun = command.replace("%input%", input);
        Player player = (Player) context.getForWhom();
        player.sendRawMessage("Command ran: /" + commandToRun);
        Bukkit.dispatchCommand(player, commandToRun);
        return null;
    }
}
