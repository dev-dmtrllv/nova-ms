/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import server.maps.MapObject;
import server.maps.MapObjectType;

import java.util.Arrays;
import java.util.List;

public class ItemVacCommand extends Command {
    {
        setDescription("Loot all drops on the map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        List<MapObject> list = player.getMap().getMapObjectsInRange(player.getPosition(), Double.POSITIVE_INFINITY, Arrays.asList(MapObjectType.ITEM));
        for (MapObject item : list) {
            player.pickupItem(item);
        }
    }
}
