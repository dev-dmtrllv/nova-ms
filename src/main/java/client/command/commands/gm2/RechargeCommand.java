/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.command.Command;
import client.inventory.InventoryType;
import client.inventory.Item;
import constants.inventory.ItemConstants;
import server.ItemInformationProvider;

public class RechargeCommand extends Command {
    {
        setDescription("Recharge and refill all USE items.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        ItemInformationProvider ii = ItemInformationProvider.getInstance();
        for (Item torecharge : c.getPlayer().getInventory(InventoryType.USE).list()) {
            if (ItemConstants.isThrowingStar(torecharge.getItemId())) {
                torecharge.setQuantity(ii.getSlotMax(c, torecharge.getItemId()));
                c.getPlayer().forceUpdateItem(torecharge);
            } else if (ItemConstants.isArrow(torecharge.getItemId())) {
                torecharge.setQuantity(ii.getSlotMax(c, torecharge.getItemId()));
                c.getPlayer().forceUpdateItem(torecharge);
            } else if (ItemConstants.isBullet(torecharge.getItemId())) {
                torecharge.setQuantity(ii.getSlotMax(c, torecharge.getItemId()));
                c.getPlayer().forceUpdateItem(torecharge);
            } else if (ItemConstants.isConsumable(torecharge.getItemId())) {
                torecharge.setQuantity(ii.getSlotMax(c, torecharge.getItemId()));
                c.getPlayer().forceUpdateItem(torecharge);
            }
        }
        player.dropMessage(5, "USE Recharged.");
    }
}
