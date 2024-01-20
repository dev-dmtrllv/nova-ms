
package net.server.channel.handlers;

import client.Character;
import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;
/**
 * @author Ronan
 */
public final class TransferNameResultHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        String name = p.readString();
        c.sendPacket(PacketCreator.sendNameTransferCheck(name, Character.canCreateChar(name)));
    }
}
