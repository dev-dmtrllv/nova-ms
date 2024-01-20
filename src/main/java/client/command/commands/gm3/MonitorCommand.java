/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import net.packet.logging.MonitoredChrLogger;
import net.server.Server;
import tools.PacketCreator;

public class MonitorCommand extends Command {
    {
        setDescription("Toggle monitored packet logging of a character.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !monitor <ign>");
            return;
        }
        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim == null) {
            player.message("Player '" + params[0] + "' could not be found on this world.");
            return;
        }
        boolean monitored = MonitoredChrLogger.toggleMonitored(victim.getId());
        player.yellowMessage(victim.getId() + " is " + (monitored ? "now being monitored." : "no longer being monitored."));
        String message = player.getName() + (monitored ? " has started monitoring " : " has stopped monitoring ") + victim.getId() + ".";
        Server.getInstance().broadcastGMMessage(c.getWorld(), PacketCreator.serverNotice(5, message));

    }
}
