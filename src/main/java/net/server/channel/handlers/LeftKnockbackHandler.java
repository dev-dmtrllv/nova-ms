
package net.server.channel.handlers;

import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;
/**
 * @author kevintjuh93
 */
public class LeftKnockbackHandler extends AbstractPacketHandler {
    public void handlePacket(InPacket p, final Client c) {
        c.sendPacket(PacketCreator.leftKnockBack());
        c.sendPacket(PacketCreator.enableActions());
    }
}
