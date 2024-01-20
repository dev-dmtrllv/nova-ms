/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class GiveVpCommand extends Command {
    {
        setDescription("Give vote points to a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 2) {
            player.yellowMessage("Syntax: !givevp <playername> <gainvotepoint>");
            return;
        }

        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim != null) {
            victim.getClient().addVotePoints(Integer.parseInt(params[1]));
            player.message("VP given.");
        } else {
            player.message("Player '" + params[0] + "' could not be found.");
        }
    }
}
