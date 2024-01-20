/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class MaxHpMpCommand extends Command {
    {
        setDescription("Set base HP/MP of a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        Character victim = player;

        int statUpdate = 1;
        if (params.length >= 2) {
            victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
            statUpdate = Integer.parseInt(params[1]);
        } else if (params.length == 1) {
            statUpdate = Integer.parseInt(params[0]);
        } else {
            player.yellowMessage("Syntax: !maxhpmp [<playername>] <value>");
        }

        if (victim != null) {
            int extraHp = victim.getCurrentMaxHp() - victim.getClientMaxHp();
            int extraMp = victim.getCurrentMaxMp() - victim.getClientMaxMp();
            statUpdate = Math.max(1 + Math.max(extraHp, extraMp), statUpdate);

            int maxhpUpdate = statUpdate - extraHp;
            int maxmpUpdate = statUpdate - extraMp;
            victim.updateMaxHpMaxMp(maxhpUpdate, maxmpUpdate);
        } else {
            player.message("Player '" + params[0] + "' could not be found on this world.");
        }
    }
}
