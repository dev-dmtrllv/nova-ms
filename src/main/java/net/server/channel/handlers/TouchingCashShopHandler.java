package net.server.channel.handlers;

import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import tools.PacketCreator;
/**
 * @author Terry Han (Acrylic)
 */
public final class TouchingCashShopHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        c.sendPacket(PacketCreator.showCash(c.getPlayer()));
    }
}
