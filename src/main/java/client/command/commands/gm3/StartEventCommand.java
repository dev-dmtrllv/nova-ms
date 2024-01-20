/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import server.events.gm.Event;
import tools.PacketCreator;

public class StartEventCommand extends Command {
    {
        setDescription("Start an event on current map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        int players = 50;
        if (params.length > 1) {
            players = Integer.parseInt(params[0]);
        }
        c.getChannelServer().setEvent(new Event(player.getMapId(), players));
        Server.getInstance().broadcastMessage(c.getWorld(), PacketCreator.earnTitleMessage(
                "[Event] An event has started on "
                        + player.getMap().getMapName()
                        + " and will allow "
                        + players
                        + " players to join. Type @joinevent to participate."));
        Server.getInstance().broadcastMessage(c.getWorld(),
                PacketCreator.serverNotice(6, "[Event] An event has started on "
                        + player.getMap().getMapName()
                        + " and will allow "
                        + players
                        + " players to join. Type @joinevent to participate."));
    }
}
