
package net.server.channel.handlers;

import client.Character;
import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import server.maps.HiredMerchant;
import tools.PacketCreator;
/**
 * @author kevintjuh93 - :3
 */
public class RemoteStoreHandler extends AbstractPacketHandler {
    @Override
    public void handlePacket(InPacket p, Client c) {
        Character chr = c.getPlayer();
        HiredMerchant hm = getMerchant(c);
        if (hm != null && hm.isOwner(chr)) {
            if (hm.getChannel() == chr.getClient().getChannel()) {
                hm.visitShop(chr);
            } else {
                c.sendPacket(PacketCreator.remoteChannelChange((byte) (hm.getChannel() - 1)));
            }
            return;
        } else {
            chr.dropMessage(1, "You don't have a Merchant open.");
        }
        c.sendPacket(PacketCreator.enableActions());
    }

    private static HiredMerchant getMerchant(Client c) {
        if (c.getPlayer().hasMerchant()) {
            return c.getWorldServer().getHiredMerchant(c.getPlayer().getId());
        }
        return null;
    }
}
