/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import server.maps.MapleMap;

import java.util.Collection;

public class ReloadMapCommand extends Command {
    {
        setDescription("Reload the map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        MapleMap newMap = c.getChannelServer().getMapFactory().resetMap(player.getMapId());
        int callerid = c.getPlayer().getId();

        Collection<Character> characters = player.getMap().getAllPlayers();

        for (Character chr : characters) {
            chr.saveLocationOnWarp();
            chr.changeMap(newMap);
            if (chr.getId() != callerid) {
                chr.dropMessage("You have been relocated due to map reloading. Sorry for the inconvenience.");
            }
        }
        newMap.respawn();
    }
}
