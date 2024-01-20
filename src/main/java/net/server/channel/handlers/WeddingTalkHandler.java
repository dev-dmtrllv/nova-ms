
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
public final class WeddingTalkHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        byte action = p.readByte();
        if (action == 1) {
            EventInstanceManager eim = c.getPlayer().getEventInstance();

            if (eim != null && !(c.getPlayer().getId() == eim.getIntProperty("groomId") || c.getPlayer().getId() == eim.getIntProperty("brideId"))) {
                c.sendPacket(WeddingPackets.OnWeddingProgress(false, 0, 0, (byte) 2));
            } else {
                c.sendPacket(WeddingPackets.OnWeddingProgress(true, 0, 0, (byte) 3));
            }
        } else {
            c.sendPacket(WeddingPackets.OnWeddingProgress(true, 0, 0, (byte) 3));
        }

        c.sendPacket(PacketCreator.enableActions());
    }
}
