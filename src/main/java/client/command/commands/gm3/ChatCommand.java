/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class ChatCommand extends Command {
    {
        setDescription("Toggle white GM chat.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        player.toggleWhiteChat();
        player.message("Your chat is now " + (player.getWhiteChat() ? " white" : "normal") + ".");
    }
}
