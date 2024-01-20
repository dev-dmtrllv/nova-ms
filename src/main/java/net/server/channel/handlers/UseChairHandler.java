package net.server.channel.handlers;

import client.Client;
import client.inventory.InventoryType;
import constants.id.ItemId;
import net.AbstractPacketHandler;
import net.packet.InPacket;

public final class UseChairHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        int itemId = p.readInt();

        // thanks Darter (YungMoozi) for reporting unchecked chair item
        if (!ItemId.isChair(itemId) || c.getPlayer().getInventory(InventoryType.SETUP).findById(itemId) == null) {
            return;
        }

        if (c.tryacquireClient()) {
            try {
                c.getPlayer().sitChair(itemId);
            } finally {
                c.releaseClient();
            }
        }
    }
}
