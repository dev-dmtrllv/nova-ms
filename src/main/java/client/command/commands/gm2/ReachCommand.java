/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;
import server.maps.MapleMap;

public class ReachCommand extends Command {
    {
        setDescription("Warp to a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !reach <playername>");
            return;
        }

        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim != null && victim.isLoggedin()) {
            if (player.getClient().getChannel() != victim.getClient().getChannel()) {
                player.dropMessage(5, "Player '" + victim.getName() + "' is at channel " + victim.getClient().getChannel() + ".");
            } else {
                MapleMap map = victim.getMap();
                player.saveLocationOnWarp();
                player.forceChangeMap(map, map.findClosestPortal(victim.getPosition()));
            }
        } else {
            player.dropMessage(6, "Unknown player.");
        }
    }
}
