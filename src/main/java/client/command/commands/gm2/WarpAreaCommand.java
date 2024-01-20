/*
   @Author: MedicOP - Add warparea command
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;
import server.maps.MapleMap;

import java.awt.*;
import java.util.Collection;

public class WarpAreaCommand extends Command {
    {
        setDescription("Warp all nearby players to a new map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !warparea <mapid>");
            return;
        }

        try {
            MapleMap target = c.getChannelServer().getMapFactory().getMap(Integer.parseInt(params[0]));
            if (target == null) {
                player.yellowMessage("Map ID " + params[0] + " is invalid.");
                return;
            }

            Point pos = player.getPosition();

            Collection<Character> characters = player.getMap().getAllPlayers();

            for (Character victim : characters) {
                if (victim.getPosition().distanceSq(pos) <= 50000) {
                    victim.saveLocationOnWarp();
                    victim.changeMap(target, target.getRandomPlayerSpawnpoint());
                }
            }
        } catch (Exception ex) {
            player.yellowMessage("Map ID " + params[0] + " is invalid.");
        }
    }
}
