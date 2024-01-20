package net.server.channel.handlers;

import client.Client;
import client.inventory.Item;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import server.CashShop;
import tools.PacketCreator;
import tools.Pair;
/**
 * @author RonanLana
 */
public class CashShopSurpriseHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        CashShop cs = c.getPlayer().getCashShop();

        if (cs.isOpened()) {
            Pair<Item, Item> cssResult = cs.openCashShopSurprise();

            if (cssResult != null) {
                Item cssItem = cssResult.getLeft(), cssBox = cssResult.getRight();
                c.sendPacket(PacketCreator.onCashGachaponOpenSuccess(c.getAccID(), cssBox.getSN(), cssBox.getQuantity(), cssItem, cssItem.getItemId(), cssItem.getQuantity(), true));
            } else {
                c.sendPacket(PacketCreator.onCashItemGachaponOpenFailed());
            }
        }
    }
}
