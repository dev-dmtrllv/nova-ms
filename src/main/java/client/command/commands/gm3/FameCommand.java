/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.Stat;
import client.command.Command;

public class FameCommand extends Command {
    {
        setDescription("Set new fame value of a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 2) {
            player.yellowMessage("Syntax: !fame <playername> <gainfame>");
            return;
        }

        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim != null) {
            victim.setFame(Integer.parseInt(params[1]));
            victim.updateSingleStat(Stat.FAME, victim.getFame());
            player.message("FAME given.");
        } else {
            player.message("Player '" + params[0] + "' could not be found.");
        }
    }
}
