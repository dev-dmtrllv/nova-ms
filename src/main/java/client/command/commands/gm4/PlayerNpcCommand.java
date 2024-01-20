/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import server.life.PlayerNPC;

public class PlayerNpcCommand extends Command {
    {
        setDescription("Spawn a player NPC of an online player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !playernpc <playername>");
            return;
        }

        if (!PlayerNPC.spawnPlayerNPC(player.getMapId(), player.getPosition(), c.getChannelServer().getPlayerStorage().getCharacterByName(params[0]))) {
            player.dropMessage(5, "Could not deploy PlayerNPC. Either there's no room available here or depleted out scriptids to use.");
        }
    }
}
