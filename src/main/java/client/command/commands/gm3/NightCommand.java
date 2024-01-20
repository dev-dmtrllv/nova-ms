/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class NightCommand extends Command {
    {
        setDescription("Set sky background to black.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        player.getMap().broadcastNightEffect();
        player.yellowMessage("Done.");
    }
}
