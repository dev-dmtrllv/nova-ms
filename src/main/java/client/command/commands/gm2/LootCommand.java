/*
   @Author: Resinate
*/
package client.command.commands.gm2;

import client.Client;
import client.command.Command;
import server.maps.MapItem;
import server.maps.MapObject;
import server.maps.MapObjectType;

import java.util.Arrays;
import java.util.List;

public class LootCommand extends Command {

    {
        setDescription("Loots all items that belong to you.");
    }

    @Override
    public void execute(Client c, String[] params) {
        List<MapObject> items = c.getPlayer().getMap().getMapObjectsInRange(c.getPlayer().getPosition(), Double.POSITIVE_INFINITY, Arrays.asList(MapObjectType.ITEM));
        for (MapObject item : items) {
            MapItem mapItem = (MapItem) item;
            if (mapItem.getOwnerId() == c.getPlayer().getId() || mapItem.getOwnerId() == c.getPlayer().getPartyId()) {
                c.getPlayer().pickupItem(mapItem);
            }
        }

    }
}
