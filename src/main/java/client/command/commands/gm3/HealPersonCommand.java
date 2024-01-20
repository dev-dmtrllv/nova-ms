/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class HealPersonCommand extends Command {
    {
        setDescription("Heal all HP/MP of a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim != null) {
            victim.healHpMp();
        } else {
            player.message("Player '" + params[0] + "' could not be found.");
        }
    }
}
