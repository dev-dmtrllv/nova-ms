package net.server.channel.handlers;

import client.Client;
import client.inventory.InventoryType;
import client.inventory.Item;
import constants.id.ItemId;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;

public final class UseItemEffectHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        Item toUse;
        int itemId = p.readInt();
        if (itemId == ItemId.BUMMER_EFFECT || itemId == ItemId.GOLDEN_CHICKEN_EFFECT) {
            toUse = c.getPlayer().getInventory(InventoryType.ETC).findById(itemId);
        } else {
            toUse = c.getPlayer().getInventory(InventoryType.CASH).findById(itemId);
        }
        if (toUse == null || toUse.getQuantity() < 1) {
            if (itemId != 0) {
                return;
            }
        }
        c.getPlayer().setItemEffect(itemId);
        c.getPlayer().getMap().broadcastMessage(c.getPlayer(), PacketCreator.itemEffect(c.getPlayer().getId(), itemId), false);
    }
}
