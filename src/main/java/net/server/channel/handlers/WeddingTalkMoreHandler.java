
package net.server.channel.handlers;

import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import scripting.event.EventInstanceManager;
import tools.PacketCreator;
import tools.packets.WeddingPackets;
/**
 * @author Ronan
 */
public final class WeddingTalkMoreHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        EventInstanceManager eim = c.getPlayer().getEventInstance();
        if (eim != null && !(c.getPlayer().getId() == eim.getIntProperty("groomId") || c.getPlayer().getId() == eim.getIntProperty("brideId"))) {
            eim.gridInsert(c.getPlayer(), 1);
            c.getPlayer().dropMessage(5, "High Priest John: Your blessings have been added to their love. What a noble act for a lovely couple!");
        }

        c.sendPacket(WeddingPackets.OnWeddingProgress(true, 0, 0, (byte) 3));
        c.sendPacket(PacketCreator.enableActions());
    }
}
