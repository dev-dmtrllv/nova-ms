/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;

public class ClearDropsCommand extends Command {
    {
        setDescription("Clear drops by player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        player.getMap().clearDrops(player);
        player.dropMessage(5, "Cleared dropped items");
    }
}
