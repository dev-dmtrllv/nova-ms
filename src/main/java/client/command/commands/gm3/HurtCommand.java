/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class HurtCommand extends Command {
    {
        setDescription("Nearly kill a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim != null) {
            victim.updateHp(1);
        } else {
            player.message("Player '" + params[0] + "' could not be found.");
        }
    }
}
