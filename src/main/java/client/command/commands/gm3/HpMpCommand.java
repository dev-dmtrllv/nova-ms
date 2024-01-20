/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class HpMpCommand extends Command {
    {
        setDescription("Set HP/MP of a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        Character victim = player;
        int statUpdate = 1;

        if (params.length == 2) {
            victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
            statUpdate = Integer.parseInt(params[1]);
        } else if (params.length == 1) {
            statUpdate = Integer.parseInt(params[0]);
        } else {
            player.yellowMessage("Syntax: !hpmp [<playername>] <value>");
        }

        if (victim != null) {
            victim.updateHpMp(statUpdate);
        } else {
            player.message("Player '" + params[0] + "' could not be found on this world.");
        }
    }
}
