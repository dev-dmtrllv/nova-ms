/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;

public class ServerMessageCommand extends Command {
    {
        setDescription("Set scrolling server message.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        c.getWorldServer().setServerMessage(player.getLastCommandMessage());
    }
}
