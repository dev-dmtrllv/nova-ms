/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm4;

import client.Character;
import client.Client;
import client.command.Command;
import client.inventory.Pet;
import client.inventory.manipulator.InventoryManipulator;
import constants.id.ItemId;
import server.maps.MapItem;
import server.maps.MapObject;
import server.maps.MapObjectType;
import tools.PacketCreator;

import java.util.Arrays;
import java.util.List;

public class ForceVacCommand extends Command {
    {
        setDescription("Loot all drops on the map.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        List<MapObject> items = player.getMap().getMapObjectsInRange(player.getPosition(), Double.POSITIVE_INFINITY, Arrays.asList(MapObjectType.ITEM));
        for (MapObject item : items) {
            MapItem mapItem = (MapItem) item;

            mapItem.lockItem();
            try {
                if (mapItem.isPickedUp()) {
                    continue;
                }

                if (mapItem.getMeso() > 0) {
                    player.gainMeso(mapItem.getMeso(), true);
                } else if (player.applyConsumeOnPickup(mapItem.getItemId())) {    // thanks Vcoc for pointing out consumables on pickup not being processed here
                } else if (ItemId.isNxCard(mapItem.getItemId())) {
                    // Add NX to account, show effect and make item disappear
                    player.getCashShop().gainCash(1, mapItem.getItemId() == ItemId.NX_CARD_100 ? 100 : 250);
                } else if (mapItem.getItem().getItemId() >= 5000000 && mapItem.getItem().getItemId() <= 5000100) {
                    int petId = Pet.createPet(mapItem.getItem().getItemId());
                    if (petId == -1) {
                        continue;
                    }
                    InventoryManipulator.addById(c, mapItem.getItem().getItemId(), mapItem.getItem().getQuantity(), null, petId);
                } else if (InventoryManipulator.addFromDrop(c, mapItem.getItem(), true)) {
                    if (mapItem.getItemId() == ItemId.NX_CARD_100) {
                        player.updateAriantScore();
                    }
                }

                player.getMap().pickItemDrop(PacketCreator.removeItemFromMap(mapItem.getObjectId(), 2, player.getId()), mapItem);
            } finally {
                mapItem.unlockItem();
            }
        }
    }
}
