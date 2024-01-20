/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;

public class UnJailCommand extends Command {
    {
        setDescription("Free a player from jail.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !unjail <playername>");
            return;
        }

        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim != null) {
            if (victim.getJailExpirationTimeLeft() <= 0) {
                player.message("This player is already free.");
                return;
            }
            victim.removeJailExpirationTime();
            victim.message("By lack of concrete proof you are now unjailed. Enjoy freedom!");
            player.message(victim.getName() + " was unjailed.");
        } else {
            player.message("Player '" + params[0] + "' could not be found.");
        }
    }
}
