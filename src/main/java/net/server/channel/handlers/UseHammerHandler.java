package net.server.channel.handlers;

import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;
/**
 * @author Jay Estrella
 */
public final class UseHammerHandler extends AbstractPacketHandler {
    public final void handlePacket(InPacket p, Client c) {
        c.sendPacket(PacketCreator.sendHammerMessage());
    }
}
