/*
   @Author: MedicOP - Add warpmap command
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;
import server.maps.MapleMap;

import java.util.Collection;

public class WarpMapCommand extends Command {
    {
        setDescription("Warp all characters on current map to a new map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !warpmap <mapid>");
            return;
        }

        try {
            MapleMap target = c.getChannelServer().getMapFactory().getMap(Integer.parseInt(params[0]));
            if (target == null) {
                player.yellowMessage("Map ID " + params[0] + " is invalid.");
                return;
            }

            Collection<Character> characters = player.getMap().getAllPlayers();

            for (Character victim : characters) {
                victim.saveLocationOnWarp();
                victim.changeMap(target, target.getRandomPlayerSpawnpoint());
            }
        } catch (Exception ex) {
            player.yellowMessage("Map ID " + params[0] + " is invalid.");
        }
    }
}
