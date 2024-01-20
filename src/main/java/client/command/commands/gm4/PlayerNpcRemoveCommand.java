/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import server.life.PlayerNPC;

public class PlayerNpcRemoveCommand extends Command {
    {
        setDescription("Remove a \"lv 200\" player NPC.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !playernpcremove <playername>");
            return;
        }
        PlayerNPC.removePlayerNPC(c.getChannelServer().getPlayerStorage().getCharacterByName(params[0]));
    }
}
