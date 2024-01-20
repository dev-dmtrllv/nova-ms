package net.server.channel.handlers;

import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;
/**
 * @author Xterminator
 */
public final class CloseChalkboardHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        c.getPlayer().setChalkboard(null);
        c.getPlayer().getMap().broadcastMessage(PacketCreator.useChalkboard(c.getPlayer(), true));
    }
}
