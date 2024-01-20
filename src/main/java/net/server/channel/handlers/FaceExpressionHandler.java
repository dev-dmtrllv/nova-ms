package net.server.channel.handlers;

import client.Character;
import client.Client;
import constants.id.ItemId;
import constants.inventory.ItemConstants;
import net.AbstractPacketHandler;
import net.packet.InPacket;

public final class FaceExpressionHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        Character chr = c.getPlayer();
        int emote = p.readInt();

        if (emote > 7) {
            int itemid = 5159992 + emote;   // thanks RajanGrewal (Darter) for reporting unchecked emote itemid
            if (!ItemId.isFaceExpression(itemid) || chr.getInventory(ItemConstants.getInventoryType(itemid)).findById(itemid) == null) {
                return;
            }
        } else if (emote < 1) {
            return;
        }

        if (c.tryacquireClient()) {
            try {   // expecting players never intends to wear the emote 0 (default face, that changes back after 5sec timeout)
                if (chr.isLoggedinWorld()) {
                    chr.changeFaceExpression(emote);
                }
            } finally {
                c.releaseClient();
            }
        }
    }
}
