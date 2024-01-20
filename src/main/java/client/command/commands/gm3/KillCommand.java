/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import tools.PacketCreator;

public class KillCommand extends Command {
    {
        setDescription("Kill a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !kill <playername>");
            return;
        }

        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim != null) {
            victim.updateHpMp(0);
            Server.getInstance().broadcastGMMessage(c.getWorld(), PacketCreator.serverNotice(5, player.getName() + " used !kill on " + victim.getName()));
        } else {
            player.message("Player '" + params[0] + "' could not be found.");
        }
    }
}
