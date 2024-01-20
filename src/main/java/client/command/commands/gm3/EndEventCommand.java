/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class EndEventCommand extends Command {
    {
        setDescription("Close entry for ongoing event.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        c.getChannelServer().setEvent(null);
        player.dropMessage(5, "You have ended the event. No more players may join.");
    }
}
