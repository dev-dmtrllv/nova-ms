/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm6;

import client.Character;
import client.Client;
import client.command.Command;

public class GetAccCommand extends Command {
    {
        setDescription("Show account name of an online player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !getacc <playername>");
            return;
        }
        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim != null) {
            player.message(victim.getName() + "'s account name is " + victim.getClient().getAccountName() + ".");
        } else {
            player.message("Player '" + params[0] + "' could not be found on this world.");
        }
    }
}
