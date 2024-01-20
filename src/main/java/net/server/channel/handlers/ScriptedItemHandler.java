package net.server.channel.handlers;

import client.Client;
import client.inventory.Item;
import constants.inventory.ItemConstants;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import scripting.item.ItemScriptManager;
import server.ItemInformationProvider;
import server.ItemInformationProvider.ScriptedItem;
/**
 * @author Jay Estrella
 */
public final class ScriptedItemHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        p.readInt(); // trash stamp, thanks RMZero213
        short itemSlot = p.readShort(); // item slot, thanks RMZero213
        int itemId = p.readInt();

        ItemInformationProvider ii = ItemInformationProvider.getInstance();
        ScriptedItem info = ii.getScriptedItemInfo(itemId);
        if (info == null) {
            return;
        }

        Item item = c.getPlayer().getInventory(ItemConstants.getInventoryType(itemId)).getItem(itemSlot);
        if (item == null || item.getItemId() != itemId || item.getQuantity() < 1) {
            return;
        }

        ItemScriptManager ism = ItemScriptManager.getInstance();
        ism.runItemScript(c, info);
    }
}
