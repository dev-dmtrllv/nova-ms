package net.server.channel.handlers;

import client.Client;
import client.autoban.AutobanFactory;
import constants.inventory.ItemConstants;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Matze
 */
public final class NPCShopHandler extends AbstractPacketHandler {
    private static final Logger log = LoggerFactory.getLogger(NPCShopHandler.class);

    @Override
    public void handlePacket(InPacket p, Client c) {
        byte bmode = p.readByte();
        switch (bmode) {
        case 0: { // mode 0 = buy :)
            short slot = p.readShort();// slot
            int itemId = p.readInt();
            short quantity = p.readShort();
            if (quantity < 1) {
                AutobanFactory.PACKET_EDIT.alert(c.getPlayer(),
                        c.getPlayer().getName() + " tried to packet edit a npc shop.");
                log.warn("Chr {} tried to buy quantity {} of itemid {}", c.getPlayer().getName(), quantity, itemId);
                c.disconnect(true, false);
                return;
            }
            c.getPlayer().getShop().buy(c, slot, itemId, quantity);
            break;
        }
        case 1: { // sell ;)
            short slot = p.readShort();
            int itemId = p.readInt();
            short quantity = p.readShort();
            c.getPlayer().getShop().sell(c, ItemConstants.getInventoryType(itemId), slot, quantity);
            break;
        }
        case 2: { // recharge ;)

            byte slot = (byte) p.readShort();
            c.getPlayer().getShop().recharge(c, slot);
            break;
        }
        case 3: // leaving :(
            c.getPlayer().setShop(null);
            break;
        }

    }
}
