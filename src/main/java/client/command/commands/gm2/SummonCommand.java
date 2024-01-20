/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import net.server.channel.Channel;
import server.maps.MapleMap;

public class SummonCommand extends Command {
    {
        setDescription("Move a player to your location.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !warphere <playername>");
            return;
        }

        Character victim = c.getChannelServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim == null) {
            //If victim isn't on current channel, loop all channels on current world.

            for (Channel ch : Server.getInstance().getChannelsFromWorld(c.getWorld())) {
                victim = ch.getPlayerStorage().getCharacterByName(params[0]);
                if (victim != null) {
                    break;//We found the person, no need to continue the loop.
                }
            }
        }
        if (victim != null) {
            if (!victim.isLoggedinWorld()) {
                player.dropMessage(6, "Player currently not logged in or unreachable.");
                return;
            }

            if (player.getClient().getChannel() != victim.getClient().getChannel()) {//And then change channel if needed.
                victim.dropMessage("Changing channel, please wait a moment.");
                victim.getClient().changeChannel(player.getClient().getChannel());
            }

            try {
                for (int i = 0; i < 7; i++) {   // poll for a while until the player reconnects
                    if (victim.isLoggedinWorld()) {
                        break;
                    }
                    Thread.sleep(1777);
                }
            } catch (InterruptedException e) {
            }

            MapleMap map = player.getMap();
            victim.saveLocationOnWarp();
            victim.forceChangeMap(map, map.findClosestPortal(player.getPosition()));
        } else {
            player.dropMessage(6, "Unknown player.");
        }
    }
}
